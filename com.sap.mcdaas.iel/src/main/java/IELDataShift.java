import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class IELDataShift implements RequestHandler<Object, Object> {
    @Override
    public String handleRequest(Object input, Context context) {
        try {
            getAwsKey();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void getAwsKey() throws IOException {
        final String s3Bucket = "com.sap.mcdaas.iel.dev";
        final String objectKey = "configuration/configuration.yaml";

        // GCP API Details
        AtomicReference<String> gcpToken = new AtomicReference<>();
        AtomicReference<String> gcpApi = new AtomicReference<>();
        AtomicReference<String> gcpDatasource = new AtomicReference<>();

        // AWS API Details
        AtomicReference<String> awsToken = new AtomicReference<>();
        AtomicReference<String> awsApi = new AtomicReference<>();
        AtomicReference<String> awsDatasource = new AtomicReference<>();

        // Azure API Details
        AtomicReference<String> azureToken = new AtomicReference<>();
        AtomicReference<String> azureApi = new AtomicReference<>();
        AtomicReference<String> azureDatasource = new AtomicReference<>();

        // yaml mapper
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

        // dev region
        final AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
                                                 .withRegion(Regions.EU_WEST_1)
                                                 .build();


        try {
            //AWS S3 Bucket File
            S3Object s3Object =  amazonS3.getObject(new GetObjectRequest(s3Bucket,objectKey));
            Hyperscalers hyperScalers = objectMapper.readValue(s3Object.getObjectContent(), Hyperscalers.class);


            // Process YAML Content
            hyperScalers.getHyperScalers().entrySet().forEach(hs -> {
                if (hs.getKey().equals(Company.GOOGLE.getID())) {
                    getHyperscalerAPI(gcpToken, gcpApi, gcpDatasource, hs);
                }
                else if(hs.getKey().equals(Company.AMAZON.getID())){
                    getHyperscalerAPI(awsToken, awsApi, awsDatasource, hs);
                } else {
                    getHyperscalerAPI(azureToken, azureApi, azureDatasource, hs);
                }
            });
        } catch(AmazonServiceException e){
            System.err.println(e.getErrorMessage());
            System.exit(1);
        } finally{
            System.out.println("GCP: Token=" + gcpToken.get() + " " +  "API=" + gcpApi.get() + " " + "DataSource=" + gcpDatasource.get() + "\n");
            System.out.println("AWS: Token=" + awsToken.get() + " " +  "API=" + awsApi.get() + " " + "DataSource=" + awsDatasource.get() + "\n");
            System.out.println("AZURE: Token=" + azureToken.get() + " " +  "API=" + azureApi.get() + " " + "DataSource=" + azureDatasource.get() + "\n");

        }

        //AwsKeys awsKeys = mapper.readValue(awsConfiguration, AwsKeys.class);
        //System.out.println("My-Access-Key=" + awsKeys.getAccess());
        //System.out.println("My-Secret-Key=" + awsKeys.getSecret());
    }

    private static void getHyperscalerAPI(AtomicReference<String> token,
                                          AtomicReference<String> api,
                                          AtomicReference<String> datasource,
                                          Map.Entry<String, Map<String, String>> hs) {
        hs.getValue().forEach((key, value) -> {
            switch (key) {
                case "token":
                    token.set(value);
                case "api":
                    api.set(value);
                case "data-source":
                    datasource.set(value);
            }
        });
    }
}