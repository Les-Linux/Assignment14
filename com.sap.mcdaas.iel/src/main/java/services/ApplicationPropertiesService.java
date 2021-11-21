package services;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationPropertiesService {

    private static String awsBucket;
    private static String awsRegion;
    private static String awsProjectConfiguration;
    private static String hyperscalerConfiguration;

    private static final Properties properties = new Properties();
    private static final String propertiesFileName = "application.properties";
    //private static final InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFileName);
//    private static final InputStream inputStream = ApplicationPropertiesService.class
//            .getResourceAsStream(ApplicationConfiguration.FILE_NAME
//                                                         .getAppConfigFileName());
    private static final InputStream inputStream = null;
    public ApplicationPropertiesService() {
        if (inputStream != null) {
            try {
                properties.load(inputStream);
                inputStream.close();

                setAwsBucket(properties.getProperty("aws.bucket"));
                setAwsRegion(properties.getProperty("aws.region"));
                setAwsProjectConfiguration(properties.getProperty("aws.project.configuration"));
                setHyperscalerConfiguration(properties.getProperty("hyperscaler.configuration"));
            } catch (IOException e) {
                //e.printStackTrace();
            }
        }
        /*
        else {
            try {
                //throw new FileNotFoundException("Property File '" + propertiesFileName + "' not found in the classpath");
            } catch (FileNotFoundException e) {
                //e.printStackTrace();
            }
        }*/
    }


    public String getAwsBucket() {
        return awsBucket;
    }

    private void setAwsBucket(String awsBucket) {
        this.awsBucket = awsBucket;
    }

    public String getAwsRegion() {
        return awsRegion;
    }

    private void setAwsRegion(String awsRegion) {
        this.awsRegion = awsRegion;
    }

    public String getAwsProjectConfiguration() {
        return awsProjectConfiguration;
    }

    private void setAwsProjectConfiguration(String awsProjectConfiguration) {
        this.awsProjectConfiguration = awsProjectConfiguration;
    }

    public String getHyperscalerConfiguration() {
        return hyperscalerConfiguration;
    }

    private void setHyperscalerConfiguration(String hyperscalerConfiguration) {
        this.hyperscalerConfiguration = hyperscalerConfiguration;
    }
}
