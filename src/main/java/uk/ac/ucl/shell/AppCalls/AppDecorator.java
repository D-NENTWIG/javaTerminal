package uk.ac.ucl.shell.AppCalls;
import uk.ac.ucl.shell.Core.ShellException;

public class AppDecorator {

    /**
     * Constructing a wrapper for the app
     * 
     * @param application The app object that the Decorator will encapsulate
     * @param isSafe Boolean, determines whether the app is safe or not and if errors are caught
     */
    private App app;
    private Boolean isSafe;

    public AppDecorator(App app, Boolean isSafe) {
        this.app = app;
        this.isSafe = isSafe;
    }

    /**
     * The run method calls run on the wrapped app object
     *
     * @throws ShellException
     */

    /**
     * Method that runs the app's functionality with the given args
     *
     * @throws  ShellException   If the path is invalid or if could not write to the output file
     */
    public void run() throws ShellException {
        if (isSafe == true) {
            try{
                this.app.run();
            }
            catch(Exception e)
            {
                System.err.println(e.getMessage());
            }
        } else {
            this.app.run();
        }
    }
}   