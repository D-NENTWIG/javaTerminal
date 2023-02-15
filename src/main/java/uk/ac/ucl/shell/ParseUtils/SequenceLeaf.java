package uk.ac.ucl.shell.ParseUtils;

import uk.ac.ucl.shell.Core.ShellException;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * PipeLeaf is a class that represents a leaf on the parse tree that is a sequence.
 */
public class SequenceLeaf extends Leaf {
    /**
     * Left leaf or subtree, left of the semicolon
     */
    private Leaf left;
    /**
     * Right leaf or subtree, right of the semicolon
     */
    private Leaf right;

     /**
     * Constructs a new SequenceLeaf object
     * 
     * @param left  Left leaf or subtree
     * @param right Right leaf or subtree
     */
    public SequenceLeaf(Leaf left, Leaf right) {
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
        left.run(inputStream, out);
        right.run(inputStream, out);
    }
}