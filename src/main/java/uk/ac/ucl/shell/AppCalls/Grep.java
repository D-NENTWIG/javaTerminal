package uk.ac.ucl.shell.AppCalls;

import uk.ac.ucl.shell.Core.ShellException;
import uk.ac.ucl.shell.FileUtils.FilePather;
import uk.ac.ucl.shell.FileUtils.FileWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Grep is a class that implements the grep command by extending App.
 */
public class Grep extends App
{
    /**
     * Constructor for Grep
     */
    Grep(InputStream in, OutputStream out, ArrayList<String> args){
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
        if (appArgs.isEmpty()) {
            throw new ShellException("grep: missing input");
        }
        if (appArgs.size() == 1 && in == null) {
            throw new ShellException("grep: missing input");
        }
    }
    /**
     * Method that runs the app's functionality with the given args
     *
     * @throws  ShellException   If the path is invalid or if could not write to the output file
     */
    public void run() throws ShellException {
        ArrayList<String> args = this.getAppArgs();
        FileWriter writer = new FileWriter(this.getOut());


        int numOfFiles = args.size() - 1;
        File[] fileArray = new File[numOfFiles];

        // Compile the regex
        Pattern grepPattern = Pattern.compile(args.get(0));
        if (args.size() < 2) {
            Scanner scanner = FileWriter.getScanner(this.getIn());
            writer.transcribe(scanner, (line) -> {
                Matcher matcher = grepPattern.matcher(line);
                if (matcher.find()) {
                    return line;
                }
                return null;
            });
        }

        // Get the files
        for (int i = 0; i < numOfFiles; i++) {
            File file = FilePather.getFile(args.get(i + 1));
            fileArray[i] = file;
        }

        // Write the lines
        for (int j = 0; j < fileArray.length; j++) {
            Scanner scanner = FileWriter.getScanner(fileArray[j]);
            int finalJ = j;

            writer.transcribe(scanner, (line) -> {
                Matcher matcher = grepPattern.matcher(line);
                if (matcher.find()) {
                    if (numOfFiles > 1) {
                        return(args.get(finalJ + 1) + ":" + line);}
                    else{
                        return line;
                    }
                }
                return null;
            });
        }
    }
}
