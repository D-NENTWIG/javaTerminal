package uk.ac.ucl;

import org.junit.Test;

import uk.ac.ucl.shell.Shell;

import static org.junit.Assert.*;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Scanner;

public class ShellTest {
    public ShellTest() {
    }

    @Test
    public void testShell() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out;
        out = new PipedOutputStream(in);
        Shell.eval("echo foo", out);
        Scanner scanning = new Scanner(in);
        assertEquals(scanning.next(),"foo");
    }

}
