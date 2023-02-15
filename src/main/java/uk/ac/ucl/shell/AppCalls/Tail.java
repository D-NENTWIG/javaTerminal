package uk.ac.ucl.shell.AppCalls;

import uk.ac.ucl.shell.Core.ShellException;
import uk.ac.ucl.shell.Core.SystemDetails;
import uk.ac.ucl.shell.FileUtils.FilePather;
import uk.ac.ucl.shell.FileUtils.FileWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Tail is a class that implements the tail command by extending App.
 */
public class Tail extends App
{
    /**
     * Constructor for Tail
     */
    Tail(InputStream in, OutputStream out, ArrayList<String> args){
        super(in, out, args);
    }

    /**
     * Method that performs basic checks on the app's args
     *
     * @throws  ShellException   If the args are invalid
     */
    public void checkAppArgs() throws ShellException {
        ArrayList<String> args = this.getAppArgs();
        InputStream input = this.getIn();
        OutputStream out = this.getOut();
        if (args.isEmpty() && input == null)  {
            throw new ShellException("tail: missing input");
        }
        if (args.size() > 3) {
            throw new ShellException("tail: too many args");
        }
        if (args.size() > 1 && !args.get(0).equals("-n")) {
            throw new ShellException("tail: wrong arg " + args.get(0));
        }
        if (args.size() == 2 && input == null) {
            throw new ShellException("tail: missing input");
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
        int tailLines = 10;
        if (appArgs.size() > 1) {
            try {
                tailLines = Integer.parseInt(appArgs.get(1));
                if (tailLines <= 0) {
                    throw new ShellException("head: illegal line count -- " + tailLines);
                }
            } catch (NumberFormatException e) {
                throw new ShellException("head: " + e.getMessage());
            }
        }
        Scanner scanner;
        if (appArgs.size() == 1 || appArgs.size() == 3) {
            String filePath = appArgs.get(appArgs.size() - 1);
            try {
                scanner = uk.ac.ucl.shell.FileUtils.FileWriter.getScanner(filePath);
            } catch (Exception e) {
                throw new ShellException("head: " + e.getMessage());
            }

        } else {
            scanner = FileWriter.getScanner(this.getIn());
        }

        // Write the lines
        int finalTailLines = tailLines;
        writer.transcribe(scanner, (line) -> null,(storage) -> {
            List<String> tail = storage.subList(Math.max(storage.size() - finalTailLines, 0), storage.size());
            return String.join(SystemDetails.lineSeparator, tail);
        });
    }
}
