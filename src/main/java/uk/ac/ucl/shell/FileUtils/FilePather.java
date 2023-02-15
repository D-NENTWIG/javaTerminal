package uk.ac.ucl.shell.FileUtils;

import uk.ac.ucl.shell.Core.ShellException;
import uk.ac.ucl.shell.Core.SystemDetails;

import java.io.File;

/**
 * FilePather is a class that helps abstract away lots of file and directory searching.
 */
public final class FilePather {
    /**
     * The current directory path
     */
    private static String currentDirectory = System.getProperty("user.dir");


    /**
     * Method to delete a directory and its contents
     * 
     * @param directory The directory that needs to be deleted
     */
    private static void deleteDirectory(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                deleteDirectory(file);
            }
         }
        directory.delete();
    }



    /**
     * Method that returns the current directory path
     * 
     * @return The current directory path
     */
    public static String getCurrentDirectory() {
        return currentDirectory;
    }

    /**
     * Method that sets the current directory path
     * 
     * @param directory New current directory path
     */
    public static void setCurrentDirectory(String directory) {
        currentDirectory = directory;
    }

    /**
     *
     */
    public static File getFile(String filePath) throws ShellException {
        try {
            return new File(getFilePathString(filePath));
        } catch (NullPointerException e) {
            throw new ShellException(e.getMessage());
        }
    }

    /**
     *
     */
    public static String getFilePathString(String filePath) {
        if(filePath.startsWith(SystemDetails.fileSeparator)) {
            return filePath;
        }
        
        return currentDirectory + SystemDetails.fileSeparator + filePath;
    }
}
