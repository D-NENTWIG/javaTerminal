package uk.ac.ucl.shell.AppCalls;


import uk.ac.ucl.shell.Core.ShellException;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;


public abstract class App {
    private InputStream in;
    private OutputStream out;
    private ArrayList<String> appArgs;
    App(InputStream in, OutputStream out, ArrayList<String> appArgs) {
        this.in = in;
        this.out = out;
        this.appArgs = appArgs;
    }
    public InputStream getIn(){return this.in;}
    public OutputStream getOut(){return this.out;}
    public ArrayList<String> getAppArgs(){return this.appArgs;}
    public abstract void checkAppArgs() throws ShellException;
    public abstract void run() throws ShellException;
}
