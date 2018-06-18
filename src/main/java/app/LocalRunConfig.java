package app;

import java.util.ArrayList;
import java.util.Arrays;

public class LocalRunConfig {
    public String DEVICE_NAME;
    public String IPCHECK_HOST;
    public String IPCHECK_RESOURCE;
    public String LOGS_DIRECTORY;
    public String LOG_NAME;

    public String EMAIL_SERVER;
    public int EMAIL_PORT;
    public String EMAIL_USERNAME;
    public String EMAIL_PASSWORD;
    public String EMAIL_SENDER;
    public ArrayList<String> EMAIL_RECIPIENT_LIST;
    public int EMAIL_TIMEOUT;

    public LocalRunConfig(boolean useDefault) {
        if (useDefault) {
            this.DEVICE_NAME = "My Unique Device Name";
            this.LOGS_DIRECTORY = "C:\\Users\\Public\\jpip";
            this.LOG_NAME = "jpip.log";
            this.IPCHECK_HOST = "www.google.com";
            this.IPCHECK_RESOURCE = "/search?q=what+is+my+ip";

            this.EMAIL_SERVER = "smtp.gmail.com";
            this.EMAIL_PORT = 465;
            this.EMAIL_USERNAME = "email@gmail.com";
            this.EMAIL_PASSWORD = "password";
            this.EMAIL_SENDER = "email@gmail.com";
            this.EMAIL_RECIPIENT_LIST = new ArrayList<>(Arrays.asList("email@gmail.com"));
            this.EMAIL_TIMEOUT = 30000;
        }
    }
}
