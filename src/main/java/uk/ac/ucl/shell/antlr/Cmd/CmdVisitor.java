
package uk.ac.ucl.shell.antlr.Cmd;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CmdParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CmdVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CmdParser#compileUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompileUnit(CmdParser.CompileUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmdParser#application}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitapplication(CmdParser.applicationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pipeBase}
	 * labeled alternative in {@link CmdParser#pipe}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPipeBase(CmdParser.PipeBaseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pipeRecursive}
	 * labeled alternative in {@link CmdParser#pipe}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPipeRecursive(CmdParser.PipeRecursiveContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SequenceRecursive}
	 * labeled alternative in {@link CmdParser#Sequence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSequenceRecursive(CmdParser.SequenceRecursiveContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SequenceBase}
	 * labeled alternative in {@link CmdParser#Sequence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSequenceBase(CmdParser.SequenceBaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmdParser#call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall(CmdParser.CallContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmdParser#single_quotes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitsingle_quotes(CmdParser.single_quotesContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmdParser#single_quote_content}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSquote_content(CmdParser.Squote_contentContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmdParser#double_quotes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitdouble_quotes(CmdParser.double_quotesContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmdParser#double_quote_content}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDquote_content(CmdParser.Dquote_contentContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmdParser#back_quotes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitback_quotes(CmdParser.back_quotesContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmdParser#back_quote_content}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBquote_content(CmdParser.Bquote_contentContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmdParser#keyword}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword(CmdParser.KeywordContext ctx);
}