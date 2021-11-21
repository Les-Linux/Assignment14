package services;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import enums.Projects;
import interfaces.HyperscalerConfiguration;
import interfaces.RepositoryService;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class AwsS3Service {
    public static S3Object getAwsS3Object(String region, String bucket, String configuration){
        //Optional<S3Object> s3Object = Optional.empty();
        S3Object s3Object = null;
        try {
            /**
             *  define the AWS Instance Region
             */
            final AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
                    .withRegion(Regions.valueOf(region)) // region read from configuration yaml file
                    .build();

            /**
             *   Read HyperScaler Configuration File from AWS S3 Bucket to Hashmap
             **/
            s3Object = amazonS3.getObject(new GetObjectRequest(bucket, configuration));
        } catch(Exception e){
            System.out.println("Exception Caught: " + e.getMessage());
        }
        return s3Object;
    }
    public static Boolean updateTimestamp(String project, HyperscalerConfiguration configuration, String region){
        try {
            // zonedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss").withZone(ZoneId.of("UTC"))));
            ZonedDateTime zonedDateTime = ZonedDateTime.now();
            RepositoryService repositoryService = new RepositoryServiceImpl();
            repositoryService.save(Projects.IEL.getProjectName(),
                                  zonedDateTime.format(DateTimeFormatter
                                               .ofPattern("yyyy-MM-dd_HH:mm:ss")
                                               .withZone(ZoneId.of("UTC"))),
                                  configuration,
                                  region);
            return true;
        } catch(Exception e){
            System.out.println("Exception Caught: " + e.getMessage());
        }
        return false;
    }
}
