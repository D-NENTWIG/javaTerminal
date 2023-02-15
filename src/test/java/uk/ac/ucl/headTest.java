package uk.ac.ucl;

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


public class headTest {

    @Rule
    public TemporaryFolder TempFolder = new TemporaryFolder();

    @Before
    public void EnterTempFolder() throws IOException{
        FilePather.setCurrentDirectory(TempFolder.getRoot().toString());
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
            Shell.eval("head Test.txt", out);
        }
        catch(Exception e){
            assertEquals("head: Test.txt is nonexistent", e.toString());
        }
    }

    @Test
    public void tooManyArgsTest() throws IOException{ //error one
        File tempFile = TempFolder.newFile("Test.txt");
        FileWriter tempFileWriter = new FileWriter(tempFile, StandardCharsets.UTF_8);
        tempFileWriter.write("Hello\nhello world");
        tempFileWriter.close();
        
        File tempFile2 = TempFolder.newFile("Test2.txt");
        FileWriter tempFileWriter2 = new FileWriter(tempFile2, StandardCharsets.UTF_8);
        tempFileWriter2.write("Hello\nhello");
        tempFileWriter2.close();

        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out;
        out = new PipedOutputStream(in);
        try{
            Shell.eval("head -n 15 Test.txt Test2.txt", out);
        }
        catch(Exception e){
            assertEquals("head: too many args", e.toString());
        }
    }

    @Test
    public void noNThreeArgsTest() throws IOException{ //error one
        File tempFile = TempFolder.newFile("Test.txt");
        FileWriter tempFileWriter = new FileWriter(tempFile, StandardCharsets.UTF_8);
        tempFileWriter.write("Hello\nhello world");
        tempFileWriter.close();

        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out;
        out = new PipedOutputStream(in);
        try{
            Shell.eval("head n 15 Test.txt", out);
        }
        catch(Exception e){
            assertEquals("head: wrong arg n", e.toString());
        }
    }

    @Test
    public void nonIntegerTest() throws IOException{ //error one
        File tempFile = TempFolder.newFile("Test.txt");
        FileWriter tempFileWriter = new FileWriter(tempFile, StandardCharsets.UTF_8);
        tempFileWriter.write("Hello\nhello world");
        tempFileWriter.close();
        
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out;
        out = new PipedOutputStream(in);
        try{
            Shell.eval("head -n ab Test.txt", out);
        }
        catch(Exception e){
            assertEquals("head: wrong arg ab", e.toString());
        }
    }

    @Test
    public void negativeTest() throws IOException{ //error one
        File tempFile = TempFolder.newFile("Test.txt");
        FileWriter tempFileWriter = new FileWriter(tempFile, StandardCharsets.UTF_8);
        tempFileWriter.write("Hello\nhello world");
        tempFileWriter.close();

        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out;
        out = new PipedOutputStream(in);
        try{
            Shell.eval("head -n -10 Test.txt", out);
        }
        catch(Exception e){
            assertEquals("head: wrong arg -10", e.toString());
        }
    }

    @Test
    public void threeArgsTest() throws IOException{
        File tempFile = TempFolder.newFile("Test.txt");
        FileWriter tempFileWriter = new FileWriter(tempFile, StandardCharsets.UTF_8);
        tempFileWriter.write("Hello\nhello world\nJava\nTests\nExample\nTesting Head");
        tempFileWriter.close();

        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out;
        out = new PipedOutputStream(in);
        Shell.eval("head -n 3 Test.txt", out);
        Scanner scn = new Scanner(in);
        System.out.println(scn.hasNextLine());
        assertEquals("Hello", scn.nextLine());
        assertEquals("hello world", scn.nextLine());
        assertEquals("Java", scn.nextLine());
        scn.close();
    }

    @Test
    public void oneArgTest() throws IOException{
        File tempFile = TempFolder.newFile("Test.txt");
        FileWriter tempFileWriter = new FileWriter(tempFile, StandardCharsets.UTF_8);
        tempFileWriter.write("Hello\nhello world\nJava\nTests\nExample\nTesting Head\nIn\ncase\nyou\nsit\nacross\nfrom\nthe\nemeterologist");
        tempFileWriter.close();

        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out;
        out = new PipedOutputStream(in);
        Shell.eval("head Test.txt", out);
        Scanner scn = new Scanner(in);
        assertEquals("Hello", scn.nextLine());
        assertEquals("hello world", scn.nextLine());
        assertEquals("Java", scn.nextLine());
        assertEquals("Tests", scn.nextLine());
        assertEquals("Example", scn.nextLine());
        assertEquals("Testing Head", scn.nextLine());
        assertEquals("In", scn.nextLine());
        assertEquals("case", scn.nextLine());
        assertEquals("you", scn.nextLine());
        assertEquals("sit", scn.nextLine());
        scn.close();
    }


}
