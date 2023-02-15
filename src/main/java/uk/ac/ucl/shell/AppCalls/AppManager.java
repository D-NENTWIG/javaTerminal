package uk.ac.ucl.shell.AppCalls;

import uk.ac.ucl.shell.Core.ShellException;
import uk.ac.ucl.shell.TokenProcessing.Globbing;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class AppManager {

    public static AppDecorator getApp(ArrayList<String> tokens, InputStream in, OutputStream out) throws ShellException
    {
        // Get the app name ignoring case
        String appName = tokens.get(0).toLowerCase();
        boolean isSafe = false;
        ArrayList<String> args = new ArrayList<String>(tokens.subList(1, tokens.size()));

        // Check if the app is safe
        if(appName.startsWith("_")) {
            appName = appName.subSequence(1, appName.length()).toString();
            isSafe = true;
        }

        // Get the app
        App app;
        switch (appName) {
            case "pwd":
                args = Globbing.globArgs(args, -1);
                app = new Pwd(in, out, args);
                break;
            case "cd":
                args = Globbing.globArgs(args, -1);
                app = new Cd(in, out, args);
                break;
            case "ls":
                args = Globbing.globArgs(args, -1);
                app = new Ls(in, out, args);
                break;
            case "cat":
                args = Globbing.globArgs(args, -1);
                app = new Cat(in, out, args);
                break;
            case "echo":
                args = Globbing.globArgs(args,-1);
                app = new Echo(in, out, args);
                break;
            case "head":
                args = Globbing.globArgs(args, -1);
                app = new Head(in, out, args);
                break;
            case "tail":
                args = Globbing.globArgs(args, -1);
                app = new Tail(in, out, args);
                break;
            case "grep":
                args = Globbing.globArgs(args, 0);
                app = new Grep(in, out, args);
                break;
            case "cut":
                args = Globbing.globArgs(args, -1);//
                app = new Cut(in, out, args);
                break;
            case "find":
                args = Globbing.globArgs(args, args.size() - 1);
                app = new Find(in, out, args);
                break;
            case "uniq":
                args = Globbing.globArgs(args, -1);
                app = new Uniq(in, out, args);
                break;
            case "sort":
                args = Globbing.globArgs(args, -1); //
                app = new Sort(in, out, args);
                break;
            default:
                throw new ShellException(appName + ": unknown application");
        }

        // Check the app's args
        app.checkAppArgs();

        // Encapsulate the app in a decorator and return it
        return new AppDecorator(app, isSafe);
    }
}



