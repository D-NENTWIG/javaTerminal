package uk.ac.ucl;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import uk.ac.ucl.shell.FileUtils.FilePather;
import uk.ac.ucl.shell.Shell;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class grepTest {
    @Rule
    public TemporaryFolder TempFolder = new TemporaryFolder();

    @Before
    public void EnterTempFolder() throws IOException{
        FilePather.setCurrentDirectory(TempFolder.getRoot().toString());
    }

    @After
    public void deleteTempFolder(){
        TempFolder.delete();
    }


    @Test
    public void invalidFileName() throws IOException{
        File tempFile = TempFolder.newFile("Test1.txt");
        FileWriter tempFileWriter = new FileWriter(tempFile, StandardCharsets.UTF_8);
        tempFileWriter.write("Hello\nhello world");
        tempFileWriter.close();

        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out;
        out = new PipedOutputStream(in);
        try{
            Shell.eval("grep 'hello world' Test.txt", out);
        }
        catch(Exception e){
            assertEquals("grep: wrong file arg", e.toString());
        }
    }

    @Test
    public void twoArgTest() throws IOException{
        File tempFile = TempFolder.newFile("Test.txt");
        FileWriter tempFileWriter = new FileWriter(tempFile, StandardCharsets.UTF_8);
        tempFileWriter.write("Hello\nhello world hello everybody\nJava");
        tempFileWriter.close();

        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out;
        out = new PipedOutputStream(in);
        Shell.eval("grep 'hello world' Test.txt", out);
        Scanner scn = new Scanner(in);
        assertEquals(scn.nextLine(), "hello world hello everybody");
        scn.close();
    }

    @Test
    public void noArgsTest() throws IOException{ //error one
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out;
        out = new PipedOutputStream(in);
        try{
            Shell.eval("grep", out);
        }
        catch(Exception e){
            assertEquals("grep: missing input", e.toString());
        }
    }

    @Test
    public void manyArgsTest() throws IOException{
        File tempFile = TempFolder.newFile("Test.txt");
        FileWriter tempFileWriter = new FileWriter(tempFile, StandardCharsets.UTF_8);
        tempFileWriter.write("Hello\nhello world hello everybody\nJava");
        tempFileWriter.close();
        
        File tempFile2 = TempFolder.newFile("Test2.txt");
        FileWriter tempFileWriter2 = new FileWriter(tempFile2, StandardCharsets.UTF_8);
        tempFileWriter2.write("Hello\nhello world tempfile2\nhello world Java");
        tempFileWriter2.close();

        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out;
        out = new PipedOutputStream(in);
        Shell.eval("grep 'hello world' Test.txt Test2.txt", out);
        Scanner scn = new Scanner(in);
        assertEquals(scn.nextLine(), "Test.txt:hello world hello everybody");
        assertEquals(scn.nextLine(), "Test2.txt:hello world tempfile2");
        assertEquals(scn.nextLine(), "Test2.txt:hello world Java");
        scn.close();
    }

}
