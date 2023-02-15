package uk.ac.ucl.shell.Core;
public class SystemDetails {
    private final static String homeDirectory = System.getProperty("user.dir");
    public final static String lineSeparator = System.getProperty("line.separator");
    public final static String fileSeparator = System.getProperty("file.separator");
    public static String getHomeDirectory() {
        return homeDirectory;
    }
    
}
