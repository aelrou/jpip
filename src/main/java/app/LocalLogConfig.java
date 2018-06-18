package app;

public class LocalLogConfig {
    public String LOGS_DIRECTORY;
    public String LOG_NAME;
    public String LOG_EXTENTION;

    public LocalLogConfig(boolean useDefault) {
        if (useDefault) {
            this.LOGS_DIRECTORY = "C:\\Users\\Public\\jpip";
            this.LOG_NAME = "jpip";
            this.LOG_EXTENTION = "log";
        }
    }
}
