package services;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import enums.DataProperties;
import enums.Projects;
import interfaces.HyperscalerConfiguration;
import interfaces.RepositoryService;

import java.util.Map;

public class RepositoryServiceImpl implements RepositoryService {
    /**
     *
     * @param configuration
     * @return boolean value of yaml file timestamp property update success / failure
     */
    @Override
    public Boolean save(String project,
                        String zonedDateTime,
                        HyperscalerConfiguration configuration, //interface here
                        String region) {
        try{
            if(project.equals(Projects.IEL.getProjectName())){
                final AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
                        .withRegion(Regions.valueOf(region)) // region read from configuration yaml file - to be reviewed due to issues with read value
                        .build();
                YAMLMapper yamlObjectMapper = new YAMLMapper(new YAMLFactory());
                //ObjectMapper yamlObjectMapper = new ObjectMapper(new YAMLFactory());
                yamlObjectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
                Map<String, Map<String,String>> projects = configuration.getProjects();
                projects.get(Projects.IEL.getProjectName()).put(DataProperties.LAST_SCAN_DATE.getProperty(), zonedDateTime);

                String awsConfig = yamlObjectMapper.writeValueAsString(projects);
                amazonS3.putObject("com.sap.mcdaas.iel.dev",
                                          "configuration/awsProjectConfiguration.yaml",
                                              yamlObjectMapper.writer().withRootName("projects").writeValueAsString(projects));
                }
            return true;
        }catch(Exception e){
            System.out.println("Exception Caught: " + e.getMessage());
        }

        return false;
    }

    /**
     * @param project projectId
     * @param data    the data to be stored in s3 bucket
     * @return boolean save (fail / success)
     */

    @Override
    public Boolean save(String project, StringBuilder data, String region, String bucket,String objectName) {
        try {
            /**
             *  define the AWS Instance Region
             */
            final AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
                    .withRegion(Regions.valueOf(region)) // region read from configuration yaml file - to be reviewed due to issues with read value
                    .build();


            /**
             *   Update AWS S3 Bucket with Project Data
             **/
            if (project.equals(Projects.IEL.getProjectName())) {
                amazonS3.putObject(bucket, objectName.toLowerCase(), data.toString());
                return true;
            }
        }catch(Exception e){
            System.out.println("Exception Caught: " + e.getMessage());
            System.exit(1);
        }
        return false;
    }
}
