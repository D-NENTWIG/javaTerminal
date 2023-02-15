package uk.ac.ucl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import uk.ac.ucl.shell.Shell;


public class catTest{
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
    public void catTestNoArg() throws Exception {
        try{
            PipedInputStream in = new PipedInputStream();
            PipedOutputStream out = new PipedOutputStream(in);
            Shell.eval("cat", out);
        }
        catch(RuntimeException expected)
        {
            assertTrue(expected.getMessage().equals("cat: missing input"));
        }
    }

    @Test
    public void catTestInvalidFile() throws Exception {
        try{
            PipedInputStream in = new PipedInputStream();
            PipedOutputStream out = new PipedOutputStream(in);
            Shell.eval("cat Invalid", out);
        }
        catch(RuntimeException expected)
        {
            assertTrue(expected.getMessage().equals("cat: file does not exist"));
        }
    }

    @Test(expected = RuntimeException.class)
    public void catDirectory() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        Shell.eval("cat src", out);
    }

    @Test(expected = RuntimeException.class)
    public void catValidDirectory() throws Exception {
        String folderName = "src";
        folder.newFolder(folderName);
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        Shell.eval("cat " + folderName, out);
    }

    @Test(expected = RuntimeException.class)
    public void catSecondArgInvalid() throws Exception {
        String fileName = "testTextFile.txt";
        createTestFile(fileName, null);
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        Shell.eval("cat " + fileName + " Invalid", out);
    }

    @Test
    public void catEmptyFile() throws Exception {
        String fileName = "testTextFile.txt";
        createTestFile(fileName, null);
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        Shell.eval("cat " + fileName, out);
        out.close();
        String contents = new String(in.readAllBytes());
        assertEquals("file not empty", "", contents);
    }

    @Test
    public void catOneFile() throws Exception {
        String toWrite = "Hello World\nBye World";
        String fileName = "testTextFile.txt";
        createTestFile(fileName, toWrite); 

        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        Shell.eval("cat " + fileName, out);
        out.close();
        String contents = new String(in.readAllBytes());
        assertEquals("File contents wrong", toWrite, contents);
    }

    @Test
    public void catTwoFiles() throws Exception {
        String toWrite = "Hello World\nGoodbye World";
        String fileName = "testTextFile.txt";
        createTestFile(fileName, toWrite); 

        String toWrite2 = "Hello World\nBye World\nHello World\nGoodbye World";
        String fileName2 = "testTextFile2.txt";
        createTestFile(fileName2, toWrite2); 

        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        Shell.eval("cat " + fileName + " " + fileName2, out);
        out.close();
        String contents = new String(in.readAllBytes());
        assertEquals("File contents wrong", toWrite + " " + toWrite2, contents);
    }

    @Test
    public void catTwoFilesWithEmpty() throws Exception {
        String toWrite = "Hello World\nGoodbye World";
        String fileName = "testTextFile.txt";
        createTestFile(fileName, toWrite); 

        String fileName2 = "testTextFile2.txt";
        createTestFile(fileName2, null); 

        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        Shell.eval("cat " + fileName + " " + fileName2, out);
        out.close();
        String contents = new String(in.readAllBytes());
        assertEquals("File contents wrong", toWrite + " ", contents);
    }

    @Test (expected = RuntimeException.class)
    public void catTwoFilesWithInvalid() throws Exception {
        String toWrite = "Hello World\nGoodbye World";
        String fileName = "testTextFile.txt";
        createTestFile(fileName, toWrite); 

        String fileName2 = "testTextFile2.txt";
        createTestFile(fileName2, null); 

        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        Shell.eval("cat " + fileName + " " + fileName2 + " Invalid", out);
    }

}
