package uk.ac.ucl.shell.AppCalls;

import uk.ac.ucl.shell.Core.ShellException;
import uk.ac.ucl.shell.FileUtils.FilePather;
import uk.ac.ucl.shell.FileUtils.FileWriter;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Sort is a class that implements the sort command by extending App. 
 */
public class Sort extends App 
{
    /**
     * Constructor for Sort 
     */
    Sort(InputStream in, OutputStream out, ArrayList<String> args){ 
        super(in, out, args); 
    } 

    /**
     * Method that performs basic checks on the app's args 
     * 
     * @throws  ShellException   If the args are invalid
     */
    public void checkAppArgs() throws ShellException { 
        ArrayList<String> args = this.getAppArgs(); 
        InputStream in = this.getIn(); 
        if (args.isEmpty() && in == null)  { 
            throw new ShellException("sort: missing input"); 
        }
        if (args.size() > 2) {
            System.out.print("sort: too many args"); 
            throw new ShellException("sort: too many args"); 
        }

        if (args.size() == 2 && !args.get(0).equals("-r")) {
            throw new ShellException("sort: wrong arg " + args.get(0));
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
        FileWriter writer = new FileWriter(out); 
 

        Scanner scanner; 
        try{
            if (appArgs.size() == 1) { 
                if (appArgs.get(0).equals("-")) { 
                    scanner = FileWriter.getScanner(currentDirectory); 
                } else {
                    scanner = FileWriter.getScanner(Paths.get(appArgs.get(0))); 
                } 
            } else if (appArgs.isEmpty()) { 
                scanner = FileWriter.getScanner(this.getIn()); 
            } else {
                if (appArgs.get(1).equals("-")) { 
                    scanner = FileWriter.getScanner(currentDirectory); 
                } else {
                    scanner = FileWriter.getScanner(Paths.get(appArgs.get(1))); 
                }
            }
        } catch (Exception e) {
            scanner = FileWriter.getScanner(this.getIn()); 
        } 
 
        final Boolean reverse; 
        if (appArgs.size() >= 1 && appArgs.get(0).equals("-r")) { 
            reverse = true;
        }else {
            reverse = false; 
        }

        writer.transcribe(scanner, (line) -> null, (storage) -> { 
            ArrayList<String> lines = sort(storage, reverse); 
            return String.join(System.lineSeparator(), lines); 
        });

    }

    public static ArrayList<String> sort(ArrayList<String> lines, boolean reverse) { 
        for (int i = 0; i < lines.size(); i++) {  
            for (int j = i + 1; j < lines.size(); j++) { 
                if (lines.get(i).compareTo(lines.get(j)) > 0) { 
                    String temp = lines.get(i); 
                    lines.set(i, lines.get(j)); 
                    lines.set(j, temp); 
                }
            }
        }
        if (reverse) { 
            ArrayList<String> reversed = new ArrayList<>(); 
            for (int i = lines.size() - 1; i >= 0; i--) { 
                reversed.add(lines.get(i)); 
            }
            return reversed;
        }
        return lines;
    }
}
