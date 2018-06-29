package app;

import java.io.File;

public class WorkDir {

    public static String getDir(String[] args, String jarName) throws WorkDirException {

        if (jarName == null || jarName.trim().isEmpty()) {
            throw new WorkDirException("Internal error. \"jarName\" is not set.");
        }
        if (args == null || args.length < 1) {
            System.out.println("Usage: \"java.exe\" -jar \"" + jarName + "\" \"C:\\Working\\Directory\"");
            throw new WorkDirException("Please specify the working directory where \"" + jarName + "\" is located.");
        }
        if (args.length != 1) {
            throw new WorkDirException("Only 1 parameter allowed. Found " + args.length);
        }

        getJar(args, jarName);
        return args[0];
    }

    private static File getJar(String[] args, String jarName) throws WorkDirException {

        File jarFile = new File(args[0] + "\\" + jarName);
        String jarPath = jarFile.getAbsolutePath();

        if (jarFile.exists()) {
            if (jarFile.isFile()) {
                System.out.println("Found JAR \"" + jarPath + "\"");
                return jarFile;
            } else {
                throw new WorkDirException("Not a file: \"" + jarPath + "\"");
            }
        } else {
            throw new WorkDirException("Cannot find: \"" + jarPath + "\"");
        }
    }
}
