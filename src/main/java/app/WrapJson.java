package app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.time.LocalDateTime;

public class WrapJson {
    private String workDir;

    public WrapJson(String workDir) {
        this.workDir = workDir;
    }

    File findLocalRunConfig(String fileName) throws WrapJsonException {
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
            new WrapJson(workDir).createLocalRunConfig(file);
            return file;
        }
    }

    LocalRunConfig readLocalRunConfig(File file) {
        LocalRunConfig localRunConfig = null;
        try {
            FileInputStream input = new FileInputStream(file);
            Reader reader = new InputStreamReader(input, "US-ASCII");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            localRunConfig = gson.fromJson(reader, LocalRunConfig.class);
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

    private void createLocalRunConfig(File file) {
        try {
            System.out.println("Creating...");
            FileWriter writer = new FileWriter(file, false);
            BufferedWriter buffer = new BufferedWriter(writer);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(new LocalRunConfig(true), buffer);
            buffer.flush();
            buffer.close();
            System.out.println("Please examine \"" + file + "\" and update the configuration as needed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    File findLocalLogConfig(String fileName) throws WrapJsonException {
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
            new WrapJson(workDir).createLocalLogConfig(file);
            return file;
        }
    }

    LocalLogConfig readLocalLogConfig(File file) {
        LocalLogConfig localLogConfig = null;
        try {
            FileInputStream input = new FileInputStream(file);
            Reader reader = new InputStreamReader(input, "US-ASCII");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            localLogConfig = gson.fromJson(reader, LocalLogConfig.class);
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

    private void createLocalLogConfig(File file) {
        try {
            System.out.println("Creating...");
            FileWriter writer = new FileWriter(file, false);
            BufferedWriter buffer = new BufferedWriter(writer);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(new LocalLogConfig(true), buffer);
            buffer.flush();
            buffer.close();
            System.out.println("Please examine \"" + file + "\" and update the configuration as needed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    File findLocalStatusCache(String fileName) throws WrapJsonException {
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
            new WrapJson(workDir).createLocalStatusCache(file);
            return file;
        }
    }

    LocalStatusCache readLocalStatusCache(File file) {
        LocalStatusCache localStatusCache = null;
        try {
            FileInputStream input = new FileInputStream(file);
            Reader reader = new InputStreamReader(input, "US-ASCII");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            localStatusCache = gson.fromJson(reader, LocalStatusCache.class);
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

    private void createLocalStatusCache(File file) {
        try {
            System.out.println("Creating...");
            FileWriter writer = new FileWriter(file, false);
            BufferedWriter buffer = new BufferedWriter(writer);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(new LocalStatusCache(true), buffer);
            buffer.flush();
            buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void updateLocalStatusCache(File file, String ipAddress) {
        try {
            FileWriter writer = new FileWriter(file, false);
            BufferedWriter buffer = new BufferedWriter(writer);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            LocalStatusCache updateStatusCache = new LocalStatusCache(false);
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
