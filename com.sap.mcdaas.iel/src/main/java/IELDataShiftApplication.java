import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import services.DataProcess;

import java.io.IOException;

public class IELDataShiftApplication implements RequestHandler<Object, Object> {
    /**
     *  the main entry point of the AWS Lambda
     */
    @Override
    public String handleRequest(Object input, Context context) {
        Boolean processStatus = false;
        try {
            //processProjectData();
            DataProcess gcpDataProcess = new DataProcess();
            processStatus = gcpDataProcess.processProjectData();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        if (processStatus){
            return "Data ProcessingCompleted. Status: Successful";
        }
        return "Data Processing Completed. Status: Failed";
    }





}