package uk.ac.ucl.shell.TokenProcessing;

import uk.ac.ucl.shell.FileUtils.FilePather;
import uk.ac.ucl.shell.Core.ShellException;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * IORedirection is a class that determines if there is any I/O redirection in the tokens and if so, returns the appropriate inputstream
 */
public class IORedirection {
    /**
     * Method that gets an inputstream depending on whether there is input redirection or not
     *
     * @param tokens  Tokens of the application, including the args and "<" or ">"
     * @param in  The current Inputstream that we will return if there is no input redirection
     */
    public static InputStream getInputStream(ArrayList<String> tokens, InputStream in) throws ShellException {
        // Check if there are multiple input redirections 
        if (Collections.frequency(tokens, "<") > 1) {
            throw new ShellException("Too many files for input redirection");
        }

        try {
            // Find the index of the input redirection symbol and check if it is not the last token
            int inIndex = tokens.indexOf("<");
            if (inIndex != -1 && inIndex + 1 < tokens.size()) {
                // Gets the file and set the input stream to the file
                in = new FileInputStream(FilePather.getFile(tokens.get(inIndex+1)));
                tokens.subList(inIndex, inIndex + 2).clear();
            }
        } catch (FileNotFoundException e) {
            throw new ShellException(e.getMessage());
        }

        // If there is no input redirection, return the same input stream
        return in;
    }

    /**
     * Method that gets an Outputstream depending on whether there is input redirection or not
     *
     * @param tokens  Tokens of the application, including the args and "<" or ">"
     * @param out  The current Outputstream that we will return if there is no input redirection
     */
    public static OutputStream getOutputStream(ArrayList<String> tokens, OutputStream out) throws ShellException {
        // Check if there are multiple output redirections
        if (Collections.frequency(tokens, ">") > 1) { 
            throw new ShellException("Too many files for output redirection"); 
        }

        try {
            // Find the index of the output redirection symbol and check if it is not the last token
            int outIndex = tokens.indexOf(">"); 
            if (outIndex != -1 && outIndex + 1 < tokens.size()) { 
                // Gets the file and set the output stream to the file
                out = new FileOutputStream(FilePather.getFile(tokens.get(outIndex + 1))); 
                tokens.subList(outIndex, outIndex + 2).clear(); 
            } 
        } catch (FileNotFoundException e) { 
            throw new ShellException(e.getMessage()); 
        }
 
        // If there is no output redirection, return the same output stream
        return out; 
    } 


} 
