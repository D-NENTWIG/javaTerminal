// Generated from uk\ac\u005Cucl\shell\antlr\CmdLineParser\StdIn.g4 by ANTLR 4.7
package uk.ac.ucl.shell.antlr.CmdLineParser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link StdInParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface StdInVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link StdInParser#compileUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompileUnit(StdInParser.CompileUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link StdInParser#application}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitApplication(StdInParser.ApplicationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pipeBase}
	 * labeled alternative in {@link StdInParser#pipe}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPipeBase(StdInParser.PipeBaseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pipeRecursive}
	 * labeled alternative in {@link StdInParser#pipe}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPipeRecursive(StdInParser.PipeRecursiveContext ctx);
	/**
	 * Visit a parse tree produced by the {@code seqRecursive}
	 * labeled alternative in {@link StdInParser#seq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSeqRecursive(StdInParser.SeqRecursiveContext ctx);
	/**
	 * Visit a parse tree produced by the {@code seqBase}
	 * labeled alternative in {@link StdInParser#seq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSeqBase(StdInParser.SeqBaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link StdInParser#call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall(StdInParser.CallContext ctx);
	/**
	 * Visit a parse tree produced by {@link StdInParser#single_quotes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingle_quotes(StdInParser.Single_quotesContext ctx);
	/**
	 * Visit a parse tree produced by {@link StdInParser#single_quote_content}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSquote_content(StdInParser.Squote_contentContext ctx);
	/**
	 * Visit a parse tree produced by {@link StdInParser#double_quotes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDouble_quotes(StdInParser.Double_quotesContext ctx);
	/**
	 * Visit a parse tree produced by {@link StdInParser#double_quote_content}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDquote_content(StdInParser.Dquote_contentContext ctx);
	/**
	 * Visit a parse tree produced by {@link StdInParser#back_quotes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBack_quotes(StdInParser.Back_quotesContext ctx);
	/**
	 * Visit a parse tree produced by {@link StdInParser#back_quote_content}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBquote_content(StdInParser.Bquote_contentContext ctx);
	/**
	 * Visit a parse tree produced by {@link StdInParser#keyword}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword(StdInParser.KeywordContext ctx);
}