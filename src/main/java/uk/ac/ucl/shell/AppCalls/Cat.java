package uk.ac.ucl.shell.AppCalls;

import uk.ac.ucl.shell.Core.ShellException;
import uk.ac.ucl.shell.FileUtils.FilePather;
import uk.ac.ucl.shell.FileUtils.FileWriter;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Cat is a class that implements the cat command by extending App. 
 */
public class Cat extends App
{
    /**
     * Constructor for Cat 
     */ 
    Cat(InputStream in, OutputStream out, ArrayList<String> args){ 
        super(in, out, args); 
    }

    /**
     * Method that performs basic checks on the app's args 
     * 
     * @throws  ShellException   If the args are invalid 
     */ 
    public void checkAppArgs() throws ShellException { 
        ArrayList<String> appArgs = this.getAppArgs(); 
 
        if(appArgs.isEmpty() && this.getIn() == null) { 
            throw new ShellException("cat: missing input"); 
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

        if(appArgs.size() == 0) {
            // If there are no args, read from the input stream
            writer.transcribe(this.getIn());
        } else 
        {
 // Read from the files given as args
            for (String arg : appArgs) {
                File currFile = new File(currentDirectory + File.separator + arg);
                if (currFile.exists()) {
                    Path filePath = Paths.get(currentDirectory + File.separator + arg);
                    writer.transcribe(filePath);
                } else {
                    throw new ShellException("cat: file does not exist");
                }
            }
        }

    }
}
