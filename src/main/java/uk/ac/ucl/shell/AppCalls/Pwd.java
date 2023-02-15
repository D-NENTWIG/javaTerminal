package uk.ac.ucl.shell.AppCalls;

import uk.ac.ucl.shell.Core.ShellException;
import uk.ac.ucl.shell.FileUtils.FilePather;
import uk.ac.ucl.shell.FileUtils.FileWriter;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Pwd is a class that implements the pwd command by extending App. 
 */
public class Pwd extends App
{
    /**
     * Constructor for Pwd
     */
    Pwd(InputStream in, OutputStream out, ArrayList<String> args){ 
        super(in, out, args); 
    }

    /**
     * Method that performs basic checks on the app's args 
     *
     * @throws  ShellException   If the args are invalid 
     */
    public void checkAppArgs() throws ShellException { 
        if (!this.getAppArgs().isEmpty()) { 
            throw new ShellException("pwd: pwd takes no args"); 
        } 
    }
    /**
     * Method that runs the app's functionality with the given args 
     *
     * @throws  ShellException   If the path is invalid or if could not write to the output file 
     */
    public void run() throws ShellException{ 
        OutputStream out = this.getOut(); 
        String currentDirectory = FilePather.getCurrentDirectory(); 

        // Write the current directory to the output stream 
        FileWriter writer = new FileWriter(out); 
        writer.writeLine(currentDirectory); 
    }    
}
