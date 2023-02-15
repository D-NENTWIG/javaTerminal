
package uk.ac.ucl.shell.antlr.Call;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CallParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CallVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CallParser#compileUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompileUnit(CallParser.CompileUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link CallParser#args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgs(CallParser.ArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CallParser#arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArg(CallParser.ArgContext ctx);
	/**
	 * Visit a parse tree produced by {@link CallParser#no_quotes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitno_quotes(CallParser.no_quotesContext ctx);
	/**
	 * Visit a parse tree produced by {@link CallParser#quoted}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuoted(CallParser.QuotedContext ctx);
	/**
	 * Visit a parse tree produced by {@link CallParser#single_quotes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitsingle_quotes(CallParser.single_quotesContext ctx);
	/**
	 * Visit a parse tree produced by {@link CallParser#single_quote_content}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSquote_content(CallParser.Squote_contentContext ctx);
	/**
	 * Visit a parse tree produced by {@link CallParser#double_quotes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitdouble_quotes(CallParser.double_quotesContext ctx);
	/**
	 * Visit a parse tree produced by {@link CallParser#double_quote_content}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDquote_content(CallParser.Dquote_contentContext ctx);
	/**
	 * Visit a parse tree produced by {@link CallParser#back_quotes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitback_quotes(CallParser.back_quotesContext ctx);
	/**
	 * Visit a parse tree produced by {@link CallParser#back_quote_content}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBquote_content(CallParser.Bquote_contentContext ctx);
	/**
	 * Visit a parse tree produced by {@link CallParser#keyword}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword(CallParser.KeywordContext ctx);
}