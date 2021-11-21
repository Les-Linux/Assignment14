package interfaces;

public interface GCPLatencyService {
    public StringBuilder getLatencyStatistics(String oauth2Base64Token,
                                     String projectId,
                                     String dataSource,
                                     String filterOn,
                                     String filterValue,
                                     String newLineDelimiter,
                                     String region,
                                     String bucket,
                                     String objectName);
}
