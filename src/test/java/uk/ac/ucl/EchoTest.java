package uk.ac.ucl;

import org.junit.Test;
import uk.ac.ucl.shell.Shell;

import static org.junit.Assert.*;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Scanner;

public class EchoTest {
    
    @Test
    public void testEchoAppOneArg() throws Exception {

        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);        
        Shell.eval("echo foo", out);
        Scanner scn = new Scanner(in);
        assertEquals("foo", scn.next());
        scn.close();
    }

    @Test
    public void testEchoNoArgs() throws Exception {

        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        Shell.eval("echo", out);
        Scanner scan = new Scanner(in);
        assertEquals("", scan.next());
        scan.close();
    }

    @Test
    public void testEchoAppMultipleArgs() throws Exception {

        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);    
        Shell.eval("echo foo fooecho", out);
        Scanner scn = new Scanner(in);
        assertEquals("foo", scn.next());
        assertEquals("fooecho", scn.next());
        scn.close();
    }

}
