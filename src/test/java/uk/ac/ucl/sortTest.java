package uk.ac.ucl;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import uk.ac.ucl.shell.Shell;

import static org.junit.Assert.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class sortTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @After
    public void deleteTempFolder() {
        folder.delete();
    }

    protected void createTestFile(String fileName, String toWrite) throws IOException {
        Charset encoding = StandardCharsets.UTF_8;
        File file = folder.newFile(fileName);
        if (toWrite != null) {
            FileWriter writer = new FileWriter(file, encoding);
            writer.write(toWrite);
            writer.close();
        }
    }


    @Test
    public void sortTestNoArgs() throws Exception {
        try{
            PipedInputStream in = new PipedInputStream();
            PipedOutputStream out = new PipedOutputStream(in);
            Shell.eval("sort", out);
        }
        catch(RuntimeException expected)
        {
            assertTrue(expected.getMessage().equals("sort: missing input"));
        }
    }


    @Test
    public void sortTestWrongNumOfArgs() throws Exception {
        
        try{
            PipedInputStream in = new PipedInputStream();
            PipedOutputStream out = new PipedOutputStream(in);
            Shell.eval("sort arg1 arg2", out);
        }
        catch(RuntimeException expected)
        {
            assertTrue(expected.getMessage().equals("sort: too many args"));
        }
    }

    @Test
    public void sortInvalidOption() throws Exception {
        
        try{
            PipedInputStream in = new PipedInputStream();
            PipedOutputStream out = new PipedOutputStream(in);
            Shell.eval("sort -t test.txt", out);
        }
        catch(RuntimeException expected)
        {
            assertTrue(expected.getMessage().equals("sort: wrong arg " + "-t"));
        }
    }
    @Test
    public void sortTestValidIn() throws Exception {
        createTestFile("Testsort.txt", "a s 2 b d a");
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out;
        out = new PipedOutputStream(in);
        Shell.eval("sort Testsort.txt", out);
        Scanner scn = new Scanner(in);
        assertEquals("2", scn.nextLine());
        assertEquals("a", scn.nextLine());
        assertEquals("a", scn.nextLine());
        assertEquals("b", scn.nextLine());
        assertEquals("d", scn.nextLine());
        assertEquals("s", scn.nextLine());
        scn.close();
    }

    @Test
    public void sortReverseTest() throws Exception {
        createTestFile("Testsort.txt", "a s 2 b d a");
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out;
        out = new PipedOutputStream(in);
        Shell.eval("sort -r Testsort.txt", out);
        Scanner scn = new Scanner(in);
        assertEquals("s", scn.nextLine());
        assertEquals("d", scn.nextLine());
        assertEquals("b", scn.nextLine());
        assertEquals("a", scn.nextLine());
        assertEquals("a", scn.nextLine());
        assertEquals("2", scn.nextLine());
        scn.close();
    }

    @Test
    public void sortTooManyArg() throws Exception {
        
        try{
            PipedInputStream in = new PipedInputStream();
            PipedOutputStream out = new PipedOutputStream(in);
            Shell.eval("sort -r arg1 arg2 arg3 arg4", out);
        }
        catch(RuntimeException expected)
        {
            assertTrue(expected.getMessage().equals("sort: too many args"));
        }
    }



    @Test
    public void sortTestFileDoesNotExist() throws Exception {
        
        try{
            PipedInputStream in = new PipedInputStream();
            PipedOutputStream out = new PipedOutputStream(in);
            Shell.eval("sort test123.txt", out);
        }
        catch(RuntimeException expected)
        {
            assertTrue(expected.getMessage().equals("sort: cannot open "+ "test123.txt"));
        }
    }
}
