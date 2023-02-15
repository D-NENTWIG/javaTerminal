package uk.ac.ucl;

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

public class cdTest{
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
    public void cdTooManyArgs() throws Exception {
        try{
            PipedInputStream in = new PipedInputStream();
            PipedOutputStream out = new PipedOutputStream(in);
            Shell.eval("cd arg1 arg2", out);
        }
        catch(RuntimeException expected)
        {
            assertTrue(expected.getMessage().equals("cd: too many args"));
        }
    }

    @Test
    public void cdDirectoryNotExists() throws Exception {
        try{
            PipedInputStream in = new PipedInputStream();
            PipedOutputStream out = new PipedOutputStream(in);
            Shell.eval("cd nonexistent", out);
        }
        catch(RuntimeException expected)
        {
            assertTrue(expected.getMessage().equals("cd: " + "nonexistent" + " is not an existing directory"));
        }
    }

    @Test
    public void cdToFileNotDirectory() throws Exception {
        
        try{
            String fileName = "testTextFile.txt";
            createTestFile(fileName, null);
            PipedInputStream in = new PipedInputStream();
            PipedOutputStream out = new PipedOutputStream(in);
            Shell.eval("cd " + fileName, out);
        }
        catch(RuntimeException expected)
        {
            assertTrue(expected.getMessage().equals("cd: " + "testTextFile.txt" + " is not an existing directory"));
        }
    }

    @Test
    //given a valid directory cd should not produce any error
    public void cdValidInput() throws Exception {
        String folderName = "testFolder";
        folder.newFolder(folderName);
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        Shell.eval("cd " + folderName, out);
    }

    @Test
    public void cdBack() throws Exception {
        String folderName = "testFolder";
        folder.newFolder(folderName);
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        Shell.eval ("cd " + folderName, out);
        Shell.eval ("cd ..", out);
    }

    @Test
    public void cdBackTooManyTimes() throws Exception {
        String folderName = "testFolder";
        folder.newFolder(folderName);
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        Shell.eval ("cd " + folderName, out);
        Shell.eval ("cd ..", out);
        try
        {
            Shell.eval ("cd ..", out);
        }
        catch(RuntimeException expected)
        {
            assertTrue(expected.getMessage().equals("cd: " + ".." + " is not an existing directory"));
        }
    }

    @Test
    public void cdNoArgs() throws Exception {
        String folderName = "testFolder";
        folder.newFolder(folderName);
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        Shell.eval ("cd " + folderName, out);
        Shell.eval ("cd", out);
    }
    @Test
    public void cdBackNoArgs() throws Exception {
        String folderName = "testFolder";
        folder.newFolder(folderName);
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        Shell.eval ("cd " + folderName, out);
        Shell.eval ("cd ..", out);
        Shell.eval ("cd", out);
    }
}