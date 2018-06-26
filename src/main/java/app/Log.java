package app;

import app.model.LogModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Log {

    static void find(LogModel logModel) {
        try {
            File file = new File(logModel.getLogFile()).getCanonicalFile();
            if (file != null || file.exists()) {
                if (file.length() > (1024 * 1024)) {
                    String logArchive = logModel.getLogsDir() + "\\" + logModel.getLogName() + "_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "." + logModel.getLogExt();
                    File fileOld = new File(logArchive);
                    file.renameTo(fileOld);
                    System.out.println("Archived LOG \"" + logArchive + "\"");
                    create(logModel);
                } else {
                    System.out.println("Found LOG \"" + logModel.getLogFile() + "\"");
                }
            } else {
                create(logModel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void create(LogModel logModel) {
        try {
            FileWriter writer = new FileWriter(logModel.getLogFile(), false);
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.write("");
            buffer.flush();
            buffer.close();
            System.out.println("Created LOG \"" + logModel.getLogFile() + "\"");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void save(LogModel logModel, String statement) {
        try {
            FileWriter writer = new FileWriter(logModel.getLogFile(), true);
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

    static void saveList(LogModel logModel, ArrayList<String> statementList) {
        try {
            FileWriter writer = new FileWriter(logModel.getLogFile(), true);
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
