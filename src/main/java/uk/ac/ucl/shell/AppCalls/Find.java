package uk.ac.ucl.shell.AppCalls;

import uk.ac.ucl.shell.Core.ShellException;
import uk.ac.ucl.shell.FileUtils.FilePather;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Find is a class that implements the find command by extending App.
 */
public class Find extends App
{
    /**
     * Constructor for Find
     */
    Find(InputStream in, OutputStream out, ArrayList<String> args){
        super(in, out, args);
    }

    /**
     * Method that performs basic checks on the app's args
     *
     * @throws  ShellException   If the args are invalid
     */
    public void checkAppArgs() throws ShellException {
        InputStream in = this.getIn();
        ArrayList<String> appArgs = this.getAppArgs();
        if (appArgs.isEmpty() && in == null)  {
            throw new ShellException("Find: missing input");
        }
        if (appArgs.size() > 4) {
            throw new ShellException("find: too many args");
        }
    }
    /**
     * Method that runs the app's functionality with the given args
     *
     * @throws  ShellException   If the path is invalid or if could not write to the output file
     */
    public void run() throws ShellException {
        ArrayList<String> appArgs = this.getAppArgs();
        OutputStream out = this.getOut();
        String currentDirectory = FilePather.getCurrentDirectory();
        OutputStreamWriter NewWriter = new OutputStreamWriter(out);

        String pattern = "";
        String path = "";
        int dumb = 0;
        if (appArgs.size() == 1) {

            dumb = 0;

            pattern = appArgs.get(0);
            path = currentDirectory;
        } else if (appArgs.size() == 2) {

            dumb = 0;

            if (appArgs.get(0).equals("-name")) {
                pattern = appArgs.get(1);
                path = currentDirectory;
            } else {
                pattern = appArgs.get(0);
                path = appArgs.get(1);
            }

        } else if (appArgs.size() == 3 && appArgs.get(1).equals("-name")) {

            dumb = 1;

            //if <path> -name <pattern> search for files in <path> that match <pattern>
            if (appArgs.get(1).equals("-name")) {
                pattern = appArgs.get(2);
                path = appArgs.get(0);
            } else {
                throw new ShellException("find: Incorrect args");
            }
        } else if (appArgs.size() == 3) {

            dumb = 0;

            if (appArgs.get(0).equals("-name")) {
                pattern = appArgs.get(1);
                path = appArgs.get(2);
            } else {
                throw new ShellException("find: Incorrect args");
            }

        }

        if (!Files.exists(Paths.get(path))) {
            throw new ShellException("find: Path does not exist");
        }
        try {
            if (pattern.contains("*")) {
                final int bumb = dumb;
                String[] parts = pattern.split("\\*");
                String first = parts[0];
                String last = parts[1];
                Files.walk(Paths.get(path)).forEach(filePath -> {
                    if (Files.isRegularFile(filePath)) {
                        String name = filePath.getFileName().toString();

                        if (name.startsWith(first) && name.endsWith(last)) {

                            if (bumb == 0) {
                                String s = filePath.toString();
                                String s1 = s.substring(s.indexOf("/") + 1);
                                String s2 = "." + s1.substring(s1.indexOf("/"));

                                try {
                                    NewWriter.write(s2);
                                    NewWriter.write(System.getProperty("line.separator"));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                try {
                                    NewWriter.write(filePath.toString());
                                    NewWriter.write(System.getProperty("line.separator"));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                });
            } else {
                final int tumb = dumb;
                final Pattern p = Pattern.compile(pattern);
                Files.walk(Paths.get(path)).forEach(filePath -> {
                    if (Files.isRegularFile(filePath)) {
                        String name = filePath.getFileName().toString();

                        if (p.matcher(name).matches()) {
                            if (tumb == 0) {
                                String s = filePath.toString();
                                String s1 = s.substring(s.indexOf("/") + 1);
                                String s2 = "." + s1.substring(s1.indexOf("/"));

                                try {
                                    NewWriter.write(s2);
                                    NewWriter.write(System.getProperty("line.separator"));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                try {
                                    NewWriter.write(filePath.toString());
                                    NewWriter.write(System.getProperty("line.separator"));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                });
            }
        } catch (IOException e) {
            throw new ShellException("find: Error in reading file");
        }
        try {
            NewWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
