package uk.ac.ucl.shell.ParseUtils;

import uk.ac.ucl.shell.Core.SystemDetails;
import uk.ac.ucl.shell.Shell;
import uk.ac.ucl.shell.antlr.Call.*;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * BuildCallApplication is a class that creates the tokens for a CallLeaf
 * using the ANTLR Generated Abstract Syntax Tree through a visitor pattern.
 */
public class BuildCallApplication extends CallBaseVisitor<ArrayList<String>> {
    @Override 
    /**
     * Method that visits the compileUnit token
     * 
     * @param ctx  ANTLR current parsing context 
     */
    public ArrayList<String> visitCompileUnit(CallParser.CompileUnitContext ctx) { 
        return visit(ctx.args()); 
    }

    @Override 
    /**
     * Method that visitsn arg token
     * 
     * @param ctx  ANTLR current parsing context 
     */
    public ArrayList<String> visitArgs(CallParser.ArgsContext ctx) {
        ArrayList<String> result = new ArrayList<>();
        if (ctx.io_operator != null) {
            result.addAll(visit(ctx.cmd));
            result.add(ctx.io_operator.getText());
            result.addAll(visit(ctx.file));
            return result;
        }
        
        result.addAll(visit(ctx.arg()));
        if (ctx.left_args != null) {
            result.addAll(visit(ctx.left_args));
        }

        return result;
    }

    @Override 
    /**
     * Method that visitsn arg token
     * 
     * @param ctx  ANTLR current parsing context 
     */
    public ArrayList<String> visitArg(CallParser.ArgContext ctx) {
        ArrayList<String> result = new ArrayList<>();

        if (ctx.quoted() != null) {
            appendArg(result, visit(ctx.quoted()));
        }
        else {
            appendArg(result, new ArrayList<>(Arrays.asList(ctx.no_quote.getText())));
        }

        if (ctx.arg() != null) {
            appendArg(result, visit(ctx.arg()));
        }

        return result;
    }

    @Override 
    /**
     * Method that visits quoted token
     * 
     * @param ctx  ANTLR current parsing context 
     */
    public ArrayList<String> visitQuoted(CallParser.QuotedContext ctx) { 
        return visitChildren(ctx); 
    }
	
    @Override 
    /**
     * Method that visits singleQuoted token
     * 
     * @param ctx  ANTLR current parsing context 
     */
    public ArrayList<String> visitsingle_quotes(CallParser.single_quotesContext ctx) {
        return new ArrayList<>(List.of(ctx.single_quote_content().getText()));
    }
	
    @Override 
    /**
     * Method that visits doubleQuoted token
     * 
     * @param ctx  ANTLR current parsing context 
     */
    public ArrayList<String> visitdouble_quotes(CallParser.double_quotesContext ctx) {
        return visit(ctx.double_quote_content());
    }

    @Override 
    /**
     * Method that visits doubleQuotedContent token
     * 
     * @param ctx  ANTLR current parsing context 
     */
    public ArrayList<String> visitDquote_content(CallParser.Dquote_contentContext ctx) { 
        ArrayList<String> result = new ArrayList<>();
        if (ctx.back_quotes() != null) {
            appendArg(result, visit(ctx.back_quotes()));
        }
        else if (ctx.content != null) {
            appendArg(result, new ArrayList<>(Arrays.asList(ctx.content.getText())));
        }

        if (ctx.double_quote_content() != null) {
            appendArg(result, visit(ctx.double_quote_content()));
        }

        return result;
    }
	
    @Override 
    /**
     * Method that visits back_quotes token
     * 
     * @param ctx  ANTLR current parsing context 
     */
    public ArrayList<String> visitback_quotes(CallParser.back_quotesContext ctx) { 
        String cmdSubstitutionString = ctx.content.getText();
        if (cmdSubstitutionString == "") {
            return new ArrayList<>(List.of(""));
        }
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Shell.eval(cmdSubstitutionString, out);

        return new ArrayList<String>(Arrays.asList(out.toString().trim().split(SystemDetails.lineSeparator)));
    }

    private void appendArg(ArrayList<String> result, ArrayList<String> arrToAppend) {
        if (result.isEmpty()) {
            result.addAll(arrToAppend);
            return;
        }

        if (!arrToAppend.isEmpty()) {
            result.set(result.size()-1, result.get(result.size()-1) + arrToAppend.get(0));
            result.addAll(arrToAppend.subList(1, arrToAppend.size()));
        }
    }
}