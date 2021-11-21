package services;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.S3Object;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import dto.HyperscalerConfigurationImpl;
import dto.HyperscalersEndpoints;
import enums.AwsResources;
import enums.DataProperties;
import enums.HyperscalerConfiguration;
import enums.Projects;
import interfaces.GCPLatencyService;
import interfaces.RepositoryService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class DataProcess {
    /**
     *  global variable declaration
     */
    InputStream inputStream;

    /**
     *
     * @throws      IOException
     * @implNote    Main method for reading configuration data and
     *              ETL process (extract, transform, load)
     */
    public Boolean processProjectData() throws IOException {
        /**
         *  Global Variable Declaration
         */
        final String ielProjectName = Projects.IEL.getProjectName();
        final String awsSourceProperty = AwsResources.BUCKET.getResourceName(); //yaml property
        final String awsRegionProperty = AwsResources.REGION.getResourceName(); //yaml property
        final String awsObjectProperty = AwsResources.OBJECT_NAME.getResourceName(); //yaml property
        AtomicReference<String> s3BucketName = new AtomicReference<>("");
        AtomicReference<String> awsS3Region = new AtomicReference<>();
        AtomicReference<String> awsObjectName = new AtomicReference<>();
        AtomicReference<StringBuilder> ielStatistics = new AtomicReference<>();


        /**
         *  Instantiate a Jackson YAML handler
         */
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        objectMapper.findAndRegisterModules(); //required for handling date fields
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);


        /**
         *  get awsS3 Object to get the last scan date
         * @params: region, bucket, configuration
         */

        try {
            /**
             *  instantiate an ApplicationPropertiesService object to get default application
             *  configuration properties
             */
            ApplicationPropertiesService applicationPropertiesService = new ApplicationPropertiesService();


            /**
             *  instantiate an AWS S3Object
             */
            S3Object s3ObjectAwsConfig = AwsS3Service.getAwsS3Object(applicationPropertiesService.getAwsRegion(),
                                                                     applicationPropertiesService.getAwsBucket(),
                                                                     applicationPropertiesService.getAwsProjectConfiguration());

            /**
             *  map the aws project(s) configuration to POJO
             */
            final HyperscalerConfigurationImpl awsConfigurationImpl =
                    objectMapper.readValue(s3ObjectAwsConfig.getObjectContent(),
                                HyperscalerConfigurationImpl.class);

            /**
             *  Process YAML - Configuration Content for AWS
             **/
            awsConfigurationImpl.getProjects().entrySet().forEach(project -> {
                if (project.getKey().equals(ielProjectName)) {
                    s3BucketName.set(project.getValue().get(awsSourceProperty)); //yaml property in awsConfiguration file
                    awsS3Region.set(project.getValue().get(awsRegionProperty).toUpperCase());
                    awsObjectName.set(project.getValue().get(awsObjectProperty).toUpperCase());
                }
            });

            /**
             *  Instantiate an AWS S3 Object for connecting and accessing Hyperscaler Resources
              */

            S3Object s3ObjectHyperscalerConfig = AwsS3Service.getAwsS3Object(awsS3Region.get(),
                                                                            s3BucketName.get(),
                                                            applicationPropertiesService.getHyperscalerConfiguration());

            HyperscalersEndpoints endpoints = objectMapper.readValue(s3ObjectHyperscalerConfig.getObjectContent(),
                                                                         HyperscalersEndpoints.class);


            /**
             *  Process YAML Content for Hyperscalers
             **/
            AtomicReference<Map<String, Map<String,String>>> hyperscalerEndpoints = new AtomicReference<>();
            hyperscalerEndpoints.set(getHyperscalerEndpoints(endpoints));

            /**
             *  Export GCP IEL Data to AWS
             *  - GCP in scope only -
             */
            Set<Map.Entry<String,Map<String,String>>> hyperscalerAPI = hyperscalerEndpoints.get().entrySet();
            hyperscalerAPI.stream().forEach(hs -> {
                switch(hs.getKey()){
                    case "gcp":
                        GCPLatencyService gcpLatencyService = new GCPLatencyServiceImpl();

                        ielStatistics.set(gcpLatencyService.getLatencyStatistics(decodeBase64(hs.getValue().get(HyperscalerConfiguration.TOKEN.getProperty())),
                                hs.getValue().get(HyperscalerConfiguration.PROJECT.getProperty()),
                                hs.getValue().get(HyperscalerConfiguration.DATA_SOURCE.getProperty()),
                                DataProperties.TIMESTAMP.getProperty(),
                                awsConfigurationImpl.getProjects().get(Projects.IEL.getProjectName()).get(DataProperties.LAST_SCAN_DATE.getProperty()),
                                DataProperties.TYPE_OF_PROTOCOL.getProperty(),
                                AwsResources.REGION.getResourceName(),
                                AwsResources.BUCKET.getResourceName(),
                                awsObjectName.get()));

                }
            });

            /**
             *  Write GCP IEL Data to AWS S3 Bucket
             */
            RepositoryService repositoryService = new RepositoryServiceImpl();
            Boolean saveState = repositoryService.save(Projects.IEL.getProjectName(),
                    ielStatistics.get(),
                    awsS3Region.get(),
                    s3BucketName.get(),
                    awsObjectName.get());

            /**
             *  handle write state of IEL data
             */
            if(saveState){ //successful Update
                //example date format - 2020-11-22_14:00:05
                Boolean timestampUpdate = AwsS3Service.updateTimestamp(Projects.IEL.getProjectName(), awsConfigurationImpl,awsS3Region.get());

                if (timestampUpdate){
                    System.out.println("Timestamp Update Completed Successfully");
                    return true;
                } else {
                    System.out.println("Timestamp Update Failed.");
                }
            }

        } catch (AmazonServiceException e) {
            System.out.println(e.getErrorMessage());
            System.exit(1);
        }
        return false;
    }

    /**
     *
     * @param hyperscalersEndpoints
     * @return
     *          A hashmap of the hyperscaler API configuration details
     */
    private Map<String,Map<String,String>> getHyperscalerEndpoints(HyperscalersEndpoints hyperscalersEndpoints) {
        Map<String, Map<String, String>> hyperscalers = new HashMap<>();
        hyperscalersEndpoints.getEndpoint().entrySet().forEach(hyperscaler -> {
            Map<String, String> configuration = new HashMap<>();
            hyperscaler.getValue().forEach((key, value) -> {
                configuration.put(key, value);
            });
            hyperscalers.put(hyperscaler.getKey(), configuration);
        });


        return hyperscalers;
    }


    /**
     *
     * @param   base64EncodedConfig
     * @return  decoded String of the hyperscaler configuration details
     */
    private static String decodeBase64(String base64EncodedConfig){
        byte[] decodedKeyBytes = Base64.getDecoder().decode(base64EncodedConfig);
        String decodedKey = new String(decodedKeyBytes);
        return decodedKey;
    }
}
