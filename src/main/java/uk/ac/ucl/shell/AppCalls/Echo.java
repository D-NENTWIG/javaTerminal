package uk.ac.ucl.shell.AppCalls;

import uk.ac.ucl.shell.Core.ShellException;
import uk.ac.ucl.shell.Core.SystemDetails;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Echo is a class that implements the echo command by extending App. 
 */
public class Echo extends App
{
    /**
     * Constructor for Echo 
     */
    Echo(InputStream in, OutputStream out, ArrayList<String> args){
        super(in, out, args);
    }
    /**
     * Method that performs basic checks on the app's args 
     *
     * @throws  ShellException   If the args are invalid 
     */
    public void checkAppArgs() throws ShellException {

    }
    /**
     * Method that runs the app's functionality with the given args 
     *
     * @throws  ShellException   If the path is invalid or if could not write to the output file 
     */ 
    public void run() throws ShellException {
        ArrayList<String> args = this.getAppArgs(); 
        OutputStream out = this.getOut(); 
        OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8); 

        // If there are no args, just print a new line
        int index = 0;
        try { 
            for (String arg : args) { 
                writer.write(arg); 
                if(index != args.size() - 1) { 
                    writer.write(" "); 
                } 
                writer.flush();
                index += 1; 
            }

            writer.write(SystemDetails.lineSeparator); 
            writer.flush(); 
        } catch (IOException e) { 
            throw new ShellException(e.getMessage()); 
        }
    }
}
