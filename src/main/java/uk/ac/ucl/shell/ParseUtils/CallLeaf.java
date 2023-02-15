package uk.ac.ucl.shell.ParseUtils;

import uk.ac.ucl.shell.AppCalls.AppDecorator;
import uk.ac.ucl.shell.AppCalls.AppManager;
import uk.ac.ucl.shell.Core.ShellException;
import uk.ac.ucl.shell.TokenProcessing.IORedirection;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * CallLeaf is a class that represents a leaf on the parse tree that is a call.
 */
public class CallLeaf extends Leaf {
    /**
     * The string representing the app name and its args
     */
    private String applicationString;

    /**
     * Constructs a CallLeaf object
     * 
     * @param applicationString  String representation of the app and its args
     */
    public CallLeaf(String applicationString) {
        this.applicationString = applicationString;
    }

    /**
     * Implementation of the run function to run the app
     */
    public void run(InputStream inputStream, OutputStream out) throws ShellException {
        ArrayList<String> tokens = Parser.parseCallapplication(applicationString);

        inputStream = IORedirection.getInputStream(tokens, inputStream);
        out = IORedirection.getOutputStream(tokens, out);

        AppDecorator app = AppManager.getApp(tokens, inputStream, out);
        app.run();
    }
}