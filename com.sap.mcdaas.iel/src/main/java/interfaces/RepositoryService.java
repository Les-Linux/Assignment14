package interfaces;

public interface RepositoryService {
   Boolean save(String project, String dateTimeZone, HyperscalerConfiguration configuration,String region);
   Boolean save(String project, StringBuilder data, String region, String bucket, String objectName);
}
