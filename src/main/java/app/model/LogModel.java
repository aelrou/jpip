package app.model;

public class LogModel {
    private String logsDir;
    private String logName;
    private String logExt;
    private String logFile;

    public LogModel(JsonLogModel jsonLogModel) {
        this.logsDir = jsonLogModel.LOGS_DIRECTORY;
        this.logName = jsonLogModel.LOG_NAME;
        this.logExt = jsonLogModel.LOG_EXTENTION;
        this.logFile = logsDir + "\\" + logName + "." + logExt;
    }

    public String getLogsDir() {
        return logsDir;
    }

    public String getLogName() {
        return logName;
    }

    public String getLogExt() {
        return logExt;
    }

    public String getLogFile() {
        return logFile;
    }
}
