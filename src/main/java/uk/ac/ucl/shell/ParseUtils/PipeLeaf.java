package uk.ac.ucl.shell.ParseUtils;

import uk.ac.ucl.shell.Core.ShellException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * PipeLeaf is a class that represents a leaf on the parse tree that is a pipe.
 */
public class PipeLeaf extends Leaf {
    /**
     * Left leaf or subtree, Left of the Pipe operation
     */
    private Leaf left;
    /**
     * Right leaf or subtree, Right of the Pipe operation
     */
    private Leaf right; 

    /**
     * Constructs a new PipeLeaf object
     * 
     * @param left  Left leaf or subtree
     * @param right Right leaf or subtree
     */
    public PipeLeaf(Leaf left, Leaf right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Method that returns left leaf or subtree
     * 
     * @return Left leaf or subtree
     */
    public Leaf getLeft() {
        return left;
    }

    /**
     * Method that returns right leaf or subtree
     * 
     * @return Right leaf or subtree
     */
    public Leaf getRight() {
        return right;
    }

    /**
     * Implementation of the run function to run the app
     */
    public void run(InputStream inputStream, OutputStream out) throws ShellException {
        ByteArrayOutputStream newStream = new ByteArrayOutputStream();
        left.run(inputStream, newStream);
        ByteArrayInputStream newInputStream = new ByteArrayInputStream(newStream.toByteArray());
        right.run(newInputStream, out);
    }


}