package app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Log {
    private String logsDir;
    private String logName;
    private String logExt;
    private String logFile;

    public Log(LocalLogConfig localLogConfig) {
        this.logsDir = localLogConfig.LOGS_DIRECTORY;
        this.logName = localLogConfig.LOG_NAME;
        this.logExt = localLogConfig.LOG_EXTENTION;
        this.logFile = logsDir + "\\" + logName + "." + logExt;
    }

    void find() {
        try {
            File file = new File(logFile).getCanonicalFile();
            if (file != null || file.exists()) {
                if (file.length() > (1024 * 1024)) {
                    String logArchive = logsDir + "\\" + logName + "_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "." + logExt;
                    File fileOld = new File(logArchive);
                    file.renameTo(fileOld);
                    System.out.println("Archived LOG \"" + logArchive + "\"");
                    create();
                } else {
                    System.out.println("Found LOG \"" + logFile + "\"");
                }
            } else {
                create();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void create() {
        try {
            FileWriter writer = new FileWriter(logFile, false);
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.write("");
            buffer.flush();
            buffer.close();
            System.out.println("Created LOG \"" + logFile + "\"");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void save(String statement) {
        try {
            FileWriter writer = new FileWriter(logFile, true);
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.write(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " " + statement);
            buffer.newLine();
            buffer.flush();
            buffer.close();
            System.out.println("Wrote LOG: " + statement);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void saveList(ArrayList<String> statementList) {
        try {
            FileWriter writer = new FileWriter(logFile, true);
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.write(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            buffer.newLine();
            for (int i = 0; i < statementList.size(); i += 1) {
                buffer.write("    " + statementList.get(i));
                buffer.newLine();
            }
            buffer.flush();
            buffer.close();
            System.out.println("Wrote LOG:");
            for (int i = 0; i < statementList.size(); i += 1) {
                System.out.println(statementList.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
