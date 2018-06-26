package app;

import app.model.JsonCacheModel;
import app.model.JsonLogModel;
import app.model.JsonRunModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.time.LocalDateTime;

public class WrapJson {

    static File findLocalRunConfig(String workDir, String fileName) throws WrapJsonException {
        File file = new File(workDir + "\\" + fileName);
        String filePath = file.getAbsolutePath();
        if (file.exists()) {
            if (file.isFile()) {
                System.out.println("Found JSON \"" + filePath + "\"");
                return file;
            } else {
                throw new WrapJsonException("Not a file: \"" + filePath + "\"");
            }
        } else {
            System.out.println("Cannot find: \"" + filePath + "\"");
            createLocalRunConfig(file);
            return file;
        }
    }

    static JsonRunModel readLocalRunConfig(File file) {
        JsonRunModel localRunConfig = null;
        try {
            FileInputStream input = new FileInputStream(file);
            Reader reader = new InputStreamReader(input, "US-ASCII");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            localRunConfig = gson.fromJson(reader, JsonRunModel.class);
            reader.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (localRunConfig == null) {
            createLocalRunConfig(file.getAbsoluteFile());
        }
        return localRunConfig;
    }

    private static void createLocalRunConfig(File file) {
        try {
            System.out.println("Creating...");
            FileWriter writer = new FileWriter(file, false);
            BufferedWriter buffer = new BufferedWriter(writer);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(new JsonRunModel(true), buffer);
            buffer.flush();
            buffer.close();
            System.out.println("Please examine \"" + file + "\" and update the configuration as needed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static File findLocalLogConfig(String workDir, String fileName) throws WrapJsonException {
        File file = new File(workDir + "\\" + fileName);
        String filePath = file.getAbsolutePath();
        if (file.exists()) {
            if (file.isFile()) {
                System.out.println("Found JSON \"" + filePath + "\"");
                return file;
            } else {
                throw new WrapJsonException("Not a file: \"" + filePath + "\"");
            }
        } else {
            System.out.println("Cannot find: \"" + filePath + "\"");
            createLocalLogConfig(file);
            return file;
        }
    }

    static JsonLogModel readLocalLogConfig(File file) {
        JsonLogModel localLogConfig = null;
        try {
            FileInputStream input = new FileInputStream(file);
            Reader reader = new InputStreamReader(input, "US-ASCII");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            localLogConfig = gson.fromJson(reader, JsonLogModel.class);
            reader.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (localLogConfig == null) {
            createLocalLogConfig(file.getAbsoluteFile());
        }
        return localLogConfig;
    }

    private static void createLocalLogConfig(File file) {
        try {
            System.out.println("Creating...");
            FileWriter writer = new FileWriter(file, false);
            BufferedWriter buffer = new BufferedWriter(writer);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(new JsonLogModel(true), buffer);
            buffer.flush();
            buffer.close();
            System.out.println("Please examine \"" + file + "\" and update the configuration as needed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static File findLocalStatusCache(String workDir, String fileName) throws WrapJsonException {
        File file = new File(workDir + "\\" + fileName);
        String filePath = file.getAbsolutePath();
        if (file.exists()) {
            if (file.isFile()) {
                System.out.println("Found JSON \"" + filePath + "\"");
                return file;
            } else {
                throw new WrapJsonException("Not a file: \"" + filePath + "\"");
            }
        } else {
            System.out.println("Cannot find: \"" + filePath + "\"");
            createLocalStatusCache(file);
            return file;
        }
    }

    static JsonCacheModel readLocalStatusCache(File file) {
        JsonCacheModel localStatusCache = null;
        try {
            FileInputStream input = new FileInputStream(file);
            Reader reader = new InputStreamReader(input, "US-ASCII");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            localStatusCache = gson.fromJson(reader, JsonCacheModel.class);
            reader.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (localStatusCache == null) {
            createLocalStatusCache(file.getAbsoluteFile());
        }
        return localStatusCache;
    }

    private static void createLocalStatusCache(File file) {
        try {
            System.out.println("Creating...");
            FileWriter writer = new FileWriter(file, false);
            BufferedWriter buffer = new BufferedWriter(writer);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(new JsonCacheModel(true), buffer);
            buffer.flush();
            buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void updateLocalStatusCache(File file, String ipAddress) {
        try {
            FileWriter writer = new FileWriter(file, false);
            BufferedWriter buffer = new BufferedWriter(writer);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            JsonCacheModel updateStatusCache = new JsonCacheModel(false);
            updateStatusCache.IP_CACHE = ipAddress;
            updateStatusCache.LAST_UPDATE = LocalDateTime.now();

            gson.toJson((updateStatusCache), buffer);
            buffer.flush();
            buffer.close();
            System.out.println("Updated JSON status cache \"" + file.toString() + "\"");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
