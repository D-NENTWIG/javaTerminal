package uk.ac.ucl.shell.Core;

/**
 * Implemented our own Exception Class that all Applications throw to help ease exception typing
 */
public class ShellException extends Exception {
    /**
     * Constructs a ShellException object using a string
     * 
     * @param message Error message that the exception will contain and return
     */
    public ShellException(String message) {
        super(message);
    }
}