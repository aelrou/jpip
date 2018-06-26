package app.model;

public class JsonLogModel {
    public String LOGS_DIRECTORY;
    public String LOG_NAME;
    public String LOG_EXTENTION;

    public JsonLogModel(boolean useDefault) {
        if (useDefault) {
            this.LOGS_DIRECTORY = "C:\\Users\\Public\\jpip";
            this.LOG_NAME = "jpip";
            this.LOG_EXTENTION = "log";
        } else {
            try { LOGS_DIRECTORY.trim(); } catch (Exception e) { System.out.println("LOGS_DIRECTORY "+ e.getMessage()); }
            try { LOG_NAME.trim(); } catch (Exception e) { System.out.println("LOG_NAME "+ e.getMessage()); }
            try { LOG_EXTENTION.trim(); } catch (Exception e) { System.out.println("LOG_EXTENTION "+ e.getMessage()); }
        }
    }
}
