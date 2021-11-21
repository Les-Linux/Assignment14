package services;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.datastore.*;
import interfaces.GCPLatencyService;



import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class GCPLatencyServiceImpl implements GCPLatencyService {
    public StringBuilder getLatencyStatistics(String oauth2Base64Token,
                                        String projectId,
                                        String dataSource,
                                        String filterOn,
                                        String filterValue,
                                        String newLineDelimiter,
                                        String awsRegion,
                                        String s3Bucket,
                                        String objectName)
    {
        // Global Variable Declaration
        GoogleCredentials credentials;
        String lineDelimiter = newLineDelimiter;
        StringBuilder gcpLatencyReport = new StringBuilder();

        try {
            credentials = ServiceAccountCredentials.fromStream(new ByteArrayInputStream(oauth2Base64Token.getBytes(StandardCharsets.UTF_8)));

            /**
             *  Instantiation of a GCP Datastore object
             */
            final Datastore datastore = DatastoreOptions.newBuilder()
                    .setProjectId(projectId)
                    .setCredentials(credentials)
                    .build()
                    .getService();

            /**
             *  Building of the GCP Datatable Query String and call to GCP
             */
            Query<Entity> query = Query.newEntityQueryBuilder()
                    .setKind(dataSource)
                    .setFilter(StructuredQuery.PropertyFilter.ge(filterOn, filterValue)) //filter date greater or equal
                    .setLimit(11) //limit during testing purposes can be set here
                    .setOffset(0)
                    .build();

            QueryResults<Entity> results = datastore.run(query);

            /**
             *  Instantiation of a List of Key:Value pairs for the returned GCP datatable query result
             */
            List<Map<String, Value<?>>> gcpLatencyData = StreamSupport.stream(Spliterators.spliteratorUnknownSize(results, Spliterator.NONNULL), false)
                    .map(entity -> entity.getProperties())
                    .collect(Collectors.toList());

            /**
             *  Instantiation of a List of GCP Latency columns names, for optional CSV file header output
             */
            List<String> gcpLatencyKeys = gcpLatencyData.stream()
                    .flatMap(key -> key.keySet().stream())
                    .distinct()
                    .collect(Collectors.toList());

            /**
             *  Build a comma separated String of the GCP Latency statistics
             */
            StringBuilder gcpLatencyStatistics = new StringBuilder();
            gcpLatencyData.stream()
                          .flatMap(fm -> fm.entrySet().stream())
                          .forEach(f -> {
                              if (!(f.getKey().equals(lineDelimiter))){
                                  gcpLatencyStatistics.append(f.getValue().get()).append(",");
                              } else {
                                  gcpLatencyStatistics.append(f.getValue().get()).append("\n");
                              }
                            });


            /**
             *  Declaration of the aggregated GCP Latency CSV formatted String to be written to a file
             */
            //StringBuilder gcpLatencyReport = new StringBuilder();


            /**
             *  Write the Header (Key) Values to the gcpLatencyReport
             **/
            gcpLatencyReport.append(gcpLatencyKeys.stream()
                            .distinct()
                            .map(m -> m.toLowerCase())
                            .collect(Collectors.joining(",")))
                            .append("\n");

            /**
             * Write the Latency Statistics to the gcpLatencyReport
             */
              gcpLatencyReport.append(gcpLatencyStatistics);
        }
        catch(Exception e){
            System.err.println("General Exception Caught: " + e.getMessage());
        }
        return gcpLatencyReport;
    }
}
