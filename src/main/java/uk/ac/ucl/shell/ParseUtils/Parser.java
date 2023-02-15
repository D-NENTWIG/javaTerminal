package uk.ac.ucl.shell.ParseUtils;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import uk.ac.ucl.shell.antlr.Call.CallLexer;
import uk.ac.ucl.shell.antlr.Call.CallParser;
import uk.ac.ucl.shell.antlr.Cmd.CmdLexer;
import uk.ac.ucl.shell.antlr.Cmd.CmdParser;

import java.util.ArrayList;

/**
 * Parser is a class that creates the parse tree from the user's input
 * using the ANTLR Generated Abstract Syntax Tree through a visitor pattern.
 */
public class Parser {
    /**
     * Method that given an input line returns a parse tree
     * 
     * @param cmdLine Input line
     * @return        Root leaf of parse tree
     */
    public static Leaf parserCmdLine(String cmdLine) {
        CmdLexer parserLexer = new CmdLexer(CharStreams.fromString(cmdLine));
        CmdParser parserParser = new CmdParser(new CommonTokenStream(parserLexer));
        CmdParser.CompileUnitContext compileUnit = parserParser.compileUnit();
        return new BuildCmdTree().visitCompileUnit(compileUnit); 
    }

    /**
     * Method that returns the app name and its args
     * 
     * @param callapplication The call to the app
     * @return            An ArrayList representing the app name and its args
     */
    public static ArrayList<String> parseCallapplication(String callapplication) {
        CallLexer parserLexer = new CallLexer(CharStreams.fromString(callapplication));
        CallParser parserParser = new CallParser(new CommonTokenStream(parserLexer));
        CallParser.CompileUnitContext compileUnit = parserParser.compileUnit();
        ArrayList<String> tokens = new BuildCallApplication().visitCompileUnit(compileUnit);
        return tokens;
    }
}