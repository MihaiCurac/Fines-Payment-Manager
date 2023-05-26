package fines_payment_manager.Notifications;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Observable;
import java.util.Observer;

public class LogObserver implements Observer {

    private String logFineDetails;
    private String logFile;

    public LogObserver(String logFile) {
        this.logFile = logFile;
    }

    @Override
    public void update(Observable o, Object fine) {
        this.setLogFineDetails((String) fine);
        try {
            writeToFile(logFile, logFineDetails);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeToFile(String logFile, String logFineDetails) throws IOException {
        FileWriter fileWriter = new FileWriter(logFile, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.append(logFineDetails).append("\n");
        printWriter.close();
    }

    public String getLogFineDetails() {
        return logFineDetails;
    }

    public void setLogFineDetails(String logFineDetails) {
        this.logFineDetails = logFineDetails;
    }
}
