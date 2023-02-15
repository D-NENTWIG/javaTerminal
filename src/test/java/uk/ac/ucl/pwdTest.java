package uk.ac.ucl;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import uk.ac.ucl.shell.FileUtils.FilePather;
import uk.ac.ucl.shell.Shell;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Scanner;


public class pwdTest {

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
    public void regularTest() throws IOException{
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out;
        out = new PipedOutputStream(in);

        Shell.eval("pwd", out);
        Scanner scn = new Scanner(in);
        assertEquals(TempFolder.getRoot().toString(), scn.nextLine());
        scn.close();
    }

    @Test
    public void tooManyArgsTest() throws IOException{ //error one
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out;
        out = new PipedOutputStream(in);
        
        try{
            Shell.eval("pwd src", out);
        }
        catch(Exception e){
            assertEquals("pwd: too many args", e.toString());
        }
    }

}
