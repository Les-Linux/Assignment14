import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
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
        File awsConfiguration = new File("awsConfiguration.yaml");
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        AwsKeys awsKeys = mapper.readValue(awsConfiguration, AwsKeys.class);
        System.out.println("My-Access-Key=" + awsKeys.getAccess());
        System.out.println("My-Secret-Key=" + awsKeys.getSecret());
    }
}