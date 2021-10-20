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
        //final String objectKey = "configuration/configuration.yaml";
        final String objectKey = "configuration/test.yaml";

        // Local Test File
        //File awsConfiguration = new File("awsConfiguration.yaml");

        // yaml mapper
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

        // dev region
        final AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
                                                 .withRegion(Regions.EU_WEST_1)
                                                 .build();

        //AWS S3 Bucket File
        try {
            S3Object s3Object =  amazonS3.getObject(new GetObjectRequest(s3Bucket,objectKey));

            Hyperscalers hyperScalers = objectMapper.readValue(s3Object.getObjectContent(), Hyperscalers.class);

            System.out.println("Hyperscalers=" + hyperScalers.getApi());
        } catch(AmazonServiceException e){
            System.err.println(e.getErrorMessage());
            System.exit(1);
        }






        //AwsKeys awsKeys = mapper.readValue(awsConfiguration, AwsKeys.class);
        //System.out.println("My-Access-Key=" + awsKeys.getAccess());
        //System.out.println("My-Secret-Key=" + awsKeys.getSecret());
    }
}