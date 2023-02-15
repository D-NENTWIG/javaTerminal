package uk.ac.ucl.shell.ParseUtils;
import uk.ac.ucl.shell.antlr.Cmd.*;

/**
 * BuildCmdTree is a class that creates the parse tree with Leaf elements
 * using the ANTLR Generated Abstract Syntax Tree through a visitor pattern.
 */
public class BuildCmdTree extends CmdBaseVisitor<Leaf> {
    @Override
    /**
     * Method that visits compileUnit token
     * 
     * @param ctx  ANTLR current parsing context 
     */
    public Leaf visitCompileUnit(CmdParser.CompileUnitContext ctx) {
        if (ctx.Sequence() != null) {
            return visit(ctx.Sequence());
        }

        return visit(ctx.application());
    }
    
    @Override 
    /**
     * Method that visits application token
     * 
     * @param ctx  ANTLR current parsing context 
     */
    public Leaf visitapplication(CmdParser.applicationContext ctx) {
        if (ctx.pipe() != null) {
            return visit(ctx.pipe());
        }

        return visit(ctx.call());
    }

    @Override
    /**
     * Method that visits pipeBase token
     * 
     * @param ctx  ANTLR current parsing context 
     * @return     A pipe Leaf based on the current parsing context
     */
    public Leaf visitPipeBase(CmdParser.PipeBaseContext ctx) {
        return new PipeLeaf(visit(ctx.call1), visit(ctx.call2));
    }

    @Override 
    /**
     * Method that visits pipeRecursive token
     * 
     * @param ctx  ANTLR current parsing context 
     * @return     A pipe Leaf based on the current parsing context
     */
    public Leaf visitPipeRecursive(CmdParser.PipeRecursiveContext ctx) {
        return new PipeLeaf(visit(ctx.pipe()), visit(ctx.call()));
    }

    @Override 
    /**
     * Method that visits SequenceRecursive token
     * 
     * @param ctx  ANTLR current parsing context 
     * @return     A Sequence Leaf based on the current parsing context
     */
    public Leaf visitSequenceRecursive(CmdParser.SequenceRecursiveContext ctx) {
        return new SequenceLeaf(visit(ctx.Sequence()), visit(ctx.application()));
    }

    @Override 
    /**
     * Method that visits SequenceBase token
     * 
     * @param ctx  ANTLR current parsing context 
     * @return     A Sequence Leaf based on the current parsing context
     */
    public Leaf visitSequenceBase(CmdParser.SequenceBaseContext ctx) {
        return new SequenceLeaf(visit(ctx.cmd1), visit(ctx.cmd2));
    }

    @Override
    /**
     * Method that visits call token
     * 
     * @param ctx  ANTLR current parsing context 
     * @return     A call Leaf based on the current parsing context
     */
    public Leaf visitCall(CmdParser.CallContext ctx) {
        return new CallLeaf(ctx.getText());
    }
}
