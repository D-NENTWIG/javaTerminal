package uk.ac.ucl.shell.AppCalls;

import uk.ac.ucl.shell.Core.ShellException;
import uk.ac.ucl.shell.Core.SystemDetails;
import uk.ac.ucl.shell.FileUtils.FileWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Uniq is a class that implements the uniq command by extending App. 
 */
public class Uniq extends App
{ 
    /**
     * Constructor for Uniq 
     */
    Uniq(InputStream in, OutputStream out, ArrayList<String> args){ 
        super(in, out, args); 
    }

    /**
     * Method that performs basic checks on the app's args 
     * 
     * @throws  ShellException   If the args are invalid 
     */
    public void checkAppArgs() throws ShellException { 
        ArrayList<String> appArgs = this.getAppArgs();
        InputStream in = this.getIn(); 
        if (appArgs.isEmpty() && in == null) { 
            throw new ShellException("grep: missing input"); 
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
        FileWriter writer = new FileWriter(out); 

        // Get the scanner
        Scanner scanner; 
        if (appArgs.size() >= 1) {
            String filePath = appArgs.get(appArgs.size() - 1); 
            try { 
                scanner = uk.ac.ucl.shell.FileUtils.FileWriter.getScanner(filePath);
            } catch (Exception e) { 
                scanner = FileWriter.getScanner(this.getIn());
            }

        } else {
            scanner = FileWriter.getScanner(this.getIn());
        }

        // Write the lines
        if (appArgs.size() >= 1 && appArgs.get(0).equals("-i")) {
            // Ignore case 
            writer.transcribe(scanner, (line) -> null, (storage) -> { 
                ArrayList<String> newLines = new ArrayList<>(); 
                final String[] prevLine = {null}; 
 
                storage.forEach((s) -> {
                    if (prevLine[0] == null || !s.equalsIgnoreCase(prevLine[0])) { 
                        newLines.add(s);
                    }
                    prevLine[0] = s;
                });
                return String.join(SystemDetails.lineSeparator, newLines); 
            });
        } else {
            // Don't ignore case
            writer.transcribe(scanner, (line) -> null, (storage) -> { 
                ArrayList<String> newLines = new ArrayList<>(); 
                final String[] prevLine = {null}; 

                storage.forEach((s) -> { 
                    if (prevLine[0] == null || !s.equals(prevLine[0])) { 
                        newLines.add(s); 
                    }
                    prevLine[0] = s; 
                });
                return String.join(SystemDetails.lineSeparator, newLines); 
            });
        }

    }
}
