package uk.ac.ucl;

import static org.junit.Assert.assertTrue;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import uk.ac.ucl.shell.Shell;

public class uniqTest {
    
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
    public void uniqTestNoArgs() throws Exception {
        try{
            PipedInputStream in = new PipedInputStream();
            PipedOutputStream out = new PipedOutputStream(in);
            Shell.eval("uniq", out);
        }
        catch(RuntimeException expected)
        {
            assertTrue(expected.getMessage().equals("uniq: missing input"));
        }
    }

    @Test
    public void uniqTestWrongNumOfArgs() throws Exception {
        try{
            PipedInputStream in = new PipedInputStream();
            PipedOutputStream out = new PipedOutputStream(in);
            Shell.eval("uniq arg1 arg2", out);
        }
        catch(RuntimeException expected)
        {
            assertTrue(expected.getMessage().equals("uniq: too many arguments"));
        }
    }

    @Test
    public void uniqTestFileNotExist() throws Exception {
        try{
            PipedInputStream in = new PipedInputStream();
            PipedOutputStream out = new PipedOutputStream(in);
            Shell.eval("uniq arg1", out);
        }
        catch(RuntimeException expected)
        {
            assertTrue(expected.getMessage().equals("uniq: file does not exist"));
        }
    }

    @Test
    public void uniqTestnotNeeded() throws Exception {
        createTestFile("test.txt", "a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z");
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        Shell.eval("uniq test.txt", out);
        Scanner scan = new Scanner(in);
        String result = scan.nextLine();
        assertTrue(result.equals("a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z"));
        scan.close();
    }

    @Test
    public void uniqTestNormal() throws Exception {
        createTestFile("test.txt", "a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z \r a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z");
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        Shell.eval("uniq test.txt", out);
        Scanner scan = new Scanner(in);
        String result = scan.nextLine();
        assertTrue(result.equals("a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z"));
        scan.close();
    }

    @Test
    public void uniqTestOptionI() throws Exception {
        createTestFile("test.txt", "a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z \r A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z");
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        Shell.eval("uniq -i test.txt", out);
        Scanner scan = new Scanner(in);
        String result = scan.nextLine();
        assertTrue(result.equals("a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z"));
        scan.close();
    }
    
}
