package uk.ac.ucl;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import uk.ac.ucl.shell.Shell;

import static org.junit.Assert.*;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

public class CutTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setUpDummyData() throws IOException {

        File testFile = folder.newFile("test1.txt");
        FileWriter writer = new FileWriter(testFile);
        writer.write("In case you sit across from the meteorologist tonight,\n" +
                      "and in case the dim light over the booth in the bar still shines\n" +
                      "almost planetary on your large, smooth, winter-softened\n");           
        writer.close();

        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        Shell.eval("cd " + folder.getRoot().getAbsolutePath(), out);
    }

    @Test 
    public void testCutWithInvalidOption() throws IOException {

        try {
            PipedInputStream in = new PipedInputStream();
            PipedOutputStream out = new PipedOutputStream(in);
            Shell.eval("cut -f 1,2 test1.txt", out);
        } catch (RuntimeException expected) {
            assertTrue(expected.getMessage().contains("cut: incorrect option input"));
        }
    }

    @Test
    public void testCutWithTooManyArg() throws IOException {

        try {
            PipedInputStream in = new PipedInputStream();
            PipedOutputStream out = new PipedOutputStream(in);
            Shell.eval("cut -b 1 2 3 test1.txt", out);
        } catch (RuntimeException expected) {
            assertTrue(expected.getMessage().contains("cut: too many args"));
        }
    }

    @Test
    public void testCutWithTooFewArg() throws IOException {

        try {
            PipedInputStream in = new PipedInputStream();
            PipedOutputStream out = new PipedOutputStream(in);
            Shell.eval("cut -b test1.txt", out);
        } catch (RuntimeException expected) {
            assertTrue(expected.getMessage().contains("cut: too few args."));
        }
    }
    
    @Test
    public void testCutWithCharacterInputForBytes() throws IOException {

        try {
            PipedInputStream in = new PipedInputStream();
            PipedOutputStream out = new PipedOutputStream(in);
            Shell.eval("cut -b a-b test1.txt", out);
        } catch (RuntimeException expected) {
            assertTrue(expected.getMessage().contains("cut: incorrect args"));
        }
    }

    @Test
    public void testCutWithInvalidFile() throws IOException {

        try {
            PipedInputStream in = new PipedInputStream();
            PipedOutputStream out = new PipedOutputStream(in);
            Shell.eval("cut -b a-b fake.txt", out);
        } catch (RuntimeException expected) {
            assertTrue(expected.getMessage().contains("cut: file input does not exist."));
        }

    }

    @Test
    public void testCutWithoutDashInInterval() throws IOException {
            try {
                PipedInputStream in = new PipedInputStream();
                PipedOutputStream out = new PipedOutputStream(in);
                Shell.eval("cut -b 1 2 test1.txt", out);
            } catch (RuntimeException expected) {
                assertTrue(expected.getMessage().contains("cut: incorrect args"));
            }
    }

    @Test
    public void testCutWithIndividualBytes() throws IOException {

        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        Scanner scan = new Scanner(in);
        Shell.eval("cut -b 4,5 test1.txt", out); 
        assertEquals("se", scan.next());
        assertEquals("ht ", scan.next());
        assertEquals("ter", scan.next());
        scan.close();
    }

    @Test
    public void testCutWithIntervalsOfBytes() throws IOException {

        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        Scanner scan = new Scanner(in);
        Shell.eval("cut -b 1-2,7-9 test1.txt", out);    
        assertEquals("ca", scan.next());
        assertEquals("lig", scan.next());
        assertEquals("win", scan.next());
        scan.close();
    }

    @Test
    public void testCutWithIntervalsWithOnlyOneSideGiven() throws IOException {

        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        Scanner scan = new Scanner(in);
        Shell.eval("cut -b -4,7- test1.txt", out); 
        assertEquals("In case you sit across from the meteorologist tonight,", scan.next());
        assertEquals("and in case the dim light over the booth in the bar still shines\n", scan.next());
        assertEquals("almost planetary on your large, smooth, winter-softened\n", scan.next());
        scan.close();
    }
}
