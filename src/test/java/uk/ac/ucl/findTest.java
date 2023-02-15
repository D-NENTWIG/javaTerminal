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
import java.util.Scanner;


public class findTest {


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
    public void invalidArgsNumName() throws IOException{
        File tempFile = TempFolder.newFile("Test.txt");
        
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out;
        out = new PipedOutputStream(in);
        try{
            Shell.eval("find Test.txt -name abc.txt a.txt", out);
        }
        catch(Exception e){
            assertEquals("find: wrong number of args", e.toString());
        }
    }

    @Test
    public void noNameArgTest() throws IOException{
        File tempFile = TempFolder.newFile("Test.txt");

        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out;
        out = new PipedOutputStream(in);
        try{
            Shell.eval("find name Test.txt", out);
        }
        catch(Exception e){
            assertEquals("head: invalid arg name", e.toString());
        }
    }


    @Test
    public void twoArgsTest() throws IOException{
        File tempFile = TempFolder.newFile("Test.txt");

        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out;
        out = new PipedOutputStream(in);
        Shell.eval("find -name Test.txt", out);
        Scanner scn = new Scanner(in);
        assertEquals(tempFile.getAbsolutePath(), scn.nextLine());
        scn.close();
    }

    @Test
    public void threeArgsTest() throws IOException{
        File tempFolder2 = TempFolder.newFolder("Test");
        File tempFile = new File(tempFolder2, "Test.txt");
        FileWriter writer = new FileWriter(tempFile);
        writer.write("hello");
        writer.close();
        
        String cmdline = "find '" + tempFolder2.getAbsolutePath().toString() + "' -name Test.txt";
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out;
        out = new PipedOutputStream(in);
        Shell.eval(cmdline, out);
        Scanner scn = new Scanner(in);
        assertEquals(tempFile.getAbsolutePath(), scn.nextLine());
        scn.close();
    }

    @Test 
    public void noMatchTest() throws IOException{
        File tempFile = TempFolder.newFile("Test.txt");
        FileWriter writer1 = new FileWriter(tempFile);
        writer1.write("hello");
        writer1.close();
        File tempFolder2 = TempFolder.newFolder("Test");
        File tempFile2 = new File(tempFolder2, "Test2.txt");
        FileWriter writer2 = new FileWriter(tempFile2);
        writer2.write("world");
        writer2.close();
        
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out;
        out = new PipedOutputStream(in);
        Shell.eval("find -name 'T.txt'", out);
        Scanner scn = new Scanner(in);
        assertEquals(tempFile2.getAbsolutePath(), scn.nextLine());
        assertEquals(tempFile.getAbsolutePath(), scn.nextLine());
        scn.close();
    }

    @Test
    public void noFileTest() throws IOException{
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out;
        out = new PipedOutputStream(in);
        try{
            Shell.eval("find -name Test.txt", out);
        }
        catch(Exception e){
            assertEquals("find: Path does not exist", e.toString());
        }
    }

    @Test
    public void noFileTest2() throws IOException{
        File tempFile = TempFolder.newFile("Test.txt");
        FileWriter writer1 = new FileWriter(tempFile);
        writer1.write("hello");
        writer1.close();
        File tempFolder2 = TempFolder.newFolder("Test");
        File tempFile2 = new File(tempFolder2, "Test2.txt");
        FileWriter writer2 = new FileWriter(tempFile2);
        writer2.write("world");
        writer2.close();
        
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out;
        out = new PipedOutputStream(in);
        try{
            Shell.eval("find -name 'T.txt'", out);
        }
        catch(Exception e){
            assertEquals("find: Path does not exist", e.toString());
        }
    }
    
}
