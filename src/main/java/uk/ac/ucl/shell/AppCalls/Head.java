package uk.ac.ucl.shell.AppCalls;

import uk.ac.ucl.shell.Core.ShellException;
import uk.ac.ucl.shell.FileUtils.FileWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Head is a class that implements the head command by extending App.
 */
public class Head extends App
{
    /**
     * Constructor for Head
     */
    Head(InputStream in, OutputStream out, ArrayList<String> args){
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
            throw new ShellException("head: missing input");
        }
        if (appArgs.size() == 2 && in == null) {
            throw new ShellException("head: missing input");
        }
        if (appArgs.size() > 1 && !appArgs.get(0).equals("-n")) {
            throw new ShellException("head: wrong arg " + appArgs.get(0));
        }
        if (appArgs.size() > 3) {
            throw new ShellException("head: too many args");
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
        int headLines = 10;
        if (appArgs.size() > 1) {
            try {
                headLines = Integer.parseInt(appArgs.get(1));
                if (headLines <= 0) {
                    throw new ShellException("head: illegal line count -- " + headLines);
                }
            } catch (NumberFormatException e) {
                throw new ShellException("head: " + e.getMessage());
            }
        }

        Scanner scanner;
        if (appArgs.size() == 1 || appArgs.size() == 3) {
            String filePath = appArgs.get(appArgs.size() - 1);
            try {
                scanner = FileWriter.getScanner(filePath);
            } catch (Exception e) {
                throw new ShellException("head: " + e.getMessage());
            }
        } else {
            scanner = FileWriter.getScanner(this.getIn());
        }

        // Write the lines
        AtomicInteger current = new AtomicInteger();
        int finalHeadLines = headLines;
        writer.transcribe(scanner, (line) -> {
            if (current.get() < finalHeadLines) {
                current.addAndGet(1);
                return line;
            }
            return null;
        });
    }
}
