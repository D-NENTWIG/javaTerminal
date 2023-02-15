package uk.ac.ucl.shell.AppCalls;

import uk.ac.ucl.shell.Core.ShellException;
import uk.ac.ucl.shell.FileUtils.FilePather;
import uk.ac.ucl.shell.FileUtils.FileWriter;

import java.io.*;
import java.util.ArrayList;

/**
 * Ls is a class that implements the ls command by extending App. 
 */
public class Ls extends App 
{
    /** 
     * Constructor for Ls 
     */
    Ls(InputStream in, OutputStream out, ArrayList<String> args){ 
        super(in, out, args); 
    }

    /**
     * Method that performs basic checks on the app's args 
     *
     * @throws  ShellException   If the args are invalid 
     */
    public void checkAppArgs() throws ShellException { 
        if (this.getAppArgs().size() > 1) { 
            throw new ShellException("ls: too many args"); 
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

        // Get the path to the directory 
        File currDir; 
        if (appArgs.isEmpty()) { 
            String currentDirectory = FilePather.getCurrentDirectory(); 
            currDir = new File(currentDirectory); 
        } else if (appArgs.size() == 1) { 
            currDir = new File(appArgs.get(0)); 
        } else {
            throw new ShellException("ls: too many args"); 
        }
        if (!currDir.exists()){
            throw new ShellException("ls: directory does not exist"); 
        }

        // Get the list of files in the directory
        File[] listOfFiles = currDir.listFiles(); 
        boolean atLeastOnePrinted = false; 
            int index = 0; 
            for (File file : listOfFiles) { 
                if (!file.getName().startsWith(".")) { 
                    // Write the file names 
                    if(index + 1 < listOfFiles.length) { 
                        writer.write(file.getName() + "\t"); 
                    }else{
                        writer.write(file.getName()); 
                    }
                    writer.flush(); 
                    atLeastOnePrinted = true; 
                }
                index += 1; 
            }

            if (atLeastOnePrinted) { 
                writer.writeLine(""); 
            }

        }

}
