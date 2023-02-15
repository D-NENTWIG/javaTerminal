package uk.ac.ucl.shell.AppCalls;

import uk.ac.ucl.shell.Core.ShellException;
import uk.ac.ucl.shell.Core.SystemDetails;
import uk.ac.ucl.shell.FileUtils.FilePather;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Cd is a class that implements the cd command by extending App. 
 */ 
public class Cd extends App
{
    /**
     * Constructor for Cd 
     */
    Cd(InputStream in, OutputStream out, ArrayList<String> args){
        super(in, out, args);
    }

    /**
     * Method that performs basic checks on the app's args
     *
     * @throws  ShellException   If the args are invalid
     */
    public void checkAppArgs() throws ShellException { 
        ArrayList<String> appArgs = this.getAppArgs(); 
        if (appArgs.size() > 1) { 
            throw new ShellException("cd: too many args"); 
        }
    }

    /**
     * Method that runs the app's functionality with the given args 
     *
     * @throws  ShellException   If the path is invalid or if could not write to the output file 
     */
    public void run() throws ShellException { 
        ArrayList<String> appArgs = this.getAppArgs(); 

        // If no args are given, go to home directory  
        if (appArgs.isEmpty()) { 
            FilePather.setCurrentDirectory(SystemDetails.getHomeDirectory()); 
        } else { 
            // If there is an arg, go to the directory specified 
            String tempCurrentDirectory = FilePather.getCurrentDirectory(); 
            String dirString = appArgs.get(0); 
            File dir = new File(tempCurrentDirectory, dirString); 
            if (dirString.charAt(0) == '/'){ 
                dir = new File(dirString); 
            }
            if (!dir.exists() || !dir.isDirectory()) { 
                throw new ShellException("cd: " + dirString + " is not an existing directory"); 
            }
            try {
                tempCurrentDirectory = dir.getCanonicalPath(); 
            }catch (IOException e) { 
                throw new ShellException("cd: " + e.getMessage()); 
            }
            FilePather.setCurrentDirectory(tempCurrentDirectory); 
        }
    }

}
