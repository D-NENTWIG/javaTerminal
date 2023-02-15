package uk.ac.ucl.shell;

import uk.ac.ucl.shell.Core.ShellException;
import uk.ac.ucl.shell.FileUtils.FilePather;
import uk.ac.ucl.shell.ParseUtils.Leaf;
import uk.ac.ucl.shell.ParseUtils.Parser;

import java.io.*;
import java.util.Scanner;

/**
 *  Class that encapsulates the Shell Terminal
 */
public class Shell {

    /**
     * Method that evaluates the command line and calls any necessary applications
     *
     * @param cmdline   String of command line
     * @param out   OutputStream to write to
     */
    public static void eval(String cmdline, OutputStream out) {
        Leaf cmdTree = Parser.parserCmdLine(cmdline);
        try {
            cmdTree.run(null, out);
        } catch (ShellException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            if (args.length != 2) {
                System.out.println("COMP0010 shell: wrong number of args");
                return;
            }
            if (!args[0].equals("-c")) {
                System.out.println("COMP0010 shell: " + args[0] + ": unexpected arg");
            }
            try {
                eval(args[1], System.out);
            } catch (Exception e) {
                System.out.println("COMP0010 shell: " + e.getMessage());
            }
        } else {
            Scanner input = new Scanner(System.in);
            try {
                while (true) {
                    String prompt = FilePather.getCurrentDirectory() + "> ";
                    System.out.print(prompt);
                    try {
                        String cmdline = input.nextLine();
                        eval(cmdline, System.out);
                    } catch (Exception e) {
                        System.out.println("COMP0010 shell: " + e.getMessage());
                    }
                }
            } finally {
                input.close();
            }
        }
    }

}
