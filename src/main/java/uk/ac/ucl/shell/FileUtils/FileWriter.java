package uk.ac.ucl.shell.FileUtils;

import uk.ac.ucl.shell.Core.ShellException;
import uk.ac.ucl.shell.Core.SystemDetails;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Function;

/**
 * FileWriter is a class that helps abstract away a lot of writing to files, flushing and line endings.
 * It also uses polymorphism for further abstraction.
 */
public class FileWriter {
    OutputStreamWriter writer;

    public FileWriter(OutputStream out) {
        writer = new OutputStreamWriter(out);
    }

    public void write(String str) throws ShellException {
        try{
            writer.write(str);
            writer.flush();
        }
        catch (IOException e) {
            throw new ShellException("Error writing to file");
        }
    }

    public void writeLine(String str) throws ShellException {
        try{
            writer.write(str);
            writer.write(SystemDetails.lineSeparator);
            writer.flush();
        }
        catch (IOException e) {
            throw new ShellException("Error writing to file");
        }
    }

    public void flush() throws ShellException {
        try{
            writer.flush();
        }
        catch (IOException e) {
            throw new ShellException("Error flushing file");
        }
    }

    public static Scanner getScanner(InputStream in) throws ShellException {
        try{
            return new Scanner(in);
        }
        catch (Exception e) {
            throw new ShellException("Error getting scanner");
        }
    }

    public static Scanner getScanner(File file) throws ShellException {
        try{
            return new Scanner(file);
        }
        catch (Exception e) {
            throw new ShellException("Error getting scanner");
        }
    }
    public static Scanner getScanner(Path filePath) throws ShellException {
        try{
            return getScanner(filePath.toString());
        }
        catch (Exception e) {
            throw new ShellException("Error getting scanner");
        }
    }

    public static Scanner getScanner(String filePath) throws ShellException {
        try{
            return new Scanner(FilePather.getFile(filePath));
        }
        catch (Exception e) {
            throw new ShellException("Error getting scanner");
        }
    }

    public void transcribe(Path filePath, Function<String, String> process) throws ShellException {
        transcribe(filePath.toString(), process);
    }

    public void transcribe(Path filePath) throws ShellException {
        transcribe(filePath.toString());
    }
    public void transcribe(String filePath) throws ShellException {
        try {
            Scanner scanner = new Scanner(FilePather.getFile(filePath));
            this.transcribe(scanner);
        } catch (IOException e) {
            throw new ShellException("cannot open");
        }
    }

    public void transcribe(String filePath, Function<String, String> process) throws ShellException {
        try {
            Scanner scanner = new Scanner(FilePather.getFile(filePath));
            this.transcribe(scanner, process);
        } catch (IOException e) {
            throw new ShellException("cannot open");
        }
    }
    public void transcribe(InputStream in) throws ShellException {
        Scanner scanner = new Scanner(in);
        this.transcribe(scanner);
    }

    public void transcribe(InputStream in, Function<String, String> process) throws ShellException {
        Scanner scanner = new Scanner(in);
        this.transcribe(scanner, process);
    }

    public void transcribe(Scanner scanner) throws ShellException {
        this.transcribe(scanner, (String s)-> s);
    }

    public void transcribe(Scanner scanner, Function<String, String> process) throws ShellException {
        this.transcribe(scanner, process, (a) -> null);
    }

    public void transcribe(Scanner scanner, Function<String, String> process, Function<ArrayList<String>, String> finalProcess) throws ShellException {
        ArrayList<String> storage = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            storage.add(line);
            String returnedLine = process.apply(line);
            if (returnedLine != null){
                this.writeLine(returnedLine);
            }
        }
        String returned = finalProcess.apply(storage);
        if (returned != null){
            this.writeLine(returned);
        }
        scanner.close();
    }

}
