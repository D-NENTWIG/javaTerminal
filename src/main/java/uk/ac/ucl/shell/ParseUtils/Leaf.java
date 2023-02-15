package uk.ac.ucl.shell.ParseUtils;

import uk.ac.ucl.shell.Core.ShellException;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Leaf is an abstract class that will be implemented as leaf nodes in the tree which
 * will be used to run the applications.
 */
public abstract class Leaf {
    /**
     * Method that allows the usage of the vistor pattern
     *
     * @param inputStream   Input stream which can be replaced if I/O redirection
     * @param out  Output stream which can be replaced if I/O redirection
     * @return              Generic, type of Visitor
     * @throws ShellException Exception that could be thrown by TreeVisitor's accept method
     */
    public abstract void run(InputStream inputStream, OutputStream out) throws ShellException;

}