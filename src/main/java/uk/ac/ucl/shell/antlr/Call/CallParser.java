
package uk.ac.ucl.shell.antlr.Call;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CallParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, WHITE_SPACE=4, NON_KEYWORD=5, SEMI=6, PIPE=7, GREATER_THAN=8, LESS_THAN=9;
	public static final int
		RULE_compileUnit = 0, RULE_args = 1, RULE_arg = 2, RULE_no_quotes = 3, 
		RULE_quoted = 4, RULE_single_quotes = 5, RULE_single_quote_content = 6, RULE_double_quotes = 7, 
		RULE_double_quote_content = 8, RULE_back_quotes = 9, RULE_back_quote_content = 10, 
		RULE_keyword = 11;
	private static String[] makeRuleNames() {
		return new String[] {
			"compileUnit", "args", "arg", "no_quotes", "quoted", "single_quotes", 
			"single_quote_content", "double_quotes", "double_quote_content", "back_quotes", "back_quote_content", 
			"keyword"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'''", "'\"'", "'`'", null, null, "';'", "'|'", "'>'", "'<'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, "WHITE_SPACE", "NON_KEYWORD", "SEMI", "PIPE", "GREATER_THAN", "LESS_THAN"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Call.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CallParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CompileUnitContext extends ParserRuleContext {
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public TerminalNode EOF() { return getToken(CallParser.EOF, 0); }
		public List<TerminalNode> WHITE_SPACE() { return getTokens(CallParser.WHITE_SPACE); }
		public TerminalNode WHITE_SPACE(int i) {
			return getToken(CallParser.WHITE_SPACE, i);
		}
		public CompileUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compileUnit; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CallVisitor ) return ((CallVisitor<? extends T>)visitor).visitCompileUnit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompileUnitContext compileUnit() throws RecognitionException {
		CompileUnitContext _localctx = new CompileUnitContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_compileUnit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(27);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITE_SPACE) {
				{
				{
				setState(24);
				match(WHITE_SPACE);
				}
				}
				setState(29);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(30);
			args(0);
			setState(34);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITE_SPACE) {
				{
				{
				setState(31);
				match(WHITE_SPACE);
				}
				}
				setState(36);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(37);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgsContext extends ParserRuleContext {
		public ArgsContext cmd;
		public ArgsContext left_args;
		public Token io_operator;
		public ArgsContext file;
		public ArgContext arg() {
			return getRuleContext(ArgContext.class,0);
		}
		public List<ArgsContext> args() {
			return getRuleContexts(ArgsContext.class);
		}
		public ArgsContext args(int i) {
			return getRuleContext(ArgsContext.class,i);
		}
		public List<TerminalNode> WHITE_SPACE() { return getTokens(CallParser.WHITE_SPACE); }
		public TerminalNode WHITE_SPACE(int i) {
			return getToken(CallParser.WHITE_SPACE, i);
		}
		public TerminalNode GREATER_THAN() { return getToken(CallParser.GREATER_THAN, 0); }
		public TerminalNode LESS_THAN() { return getToken(CallParser.LESS_THAN, 0); }
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CallVisitor ) return ((CallVisitor<? extends T>)visitor).visitArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgsContext args() throws RecognitionException {
		return args(0);
	}

	private ArgsContext args(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ArgsContext _localctx = new ArgsContext(_ctx, _parentState);
		ArgsContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_args, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(40);
				arg();
				}
				break;
			case 2:
				{
				setState(41);
				arg();
				setState(43); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(42);
					match(WHITE_SPACE);
					}
					}
					setState(45); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==WHITE_SPACE );
				setState(47);
				((ArgsContext)_localctx).left_args = args(5);
				}
				break;
			case 3:
				{
				setState(49);
				((ArgsContext)_localctx).io_operator = match(GREATER_THAN);
				setState(53);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==WHITE_SPACE) {
					{
					{
					setState(50);
					match(WHITE_SPACE);
					}
					}
					setState(55);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(56);
				((ArgsContext)_localctx).file = args(0);
				setState(58); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(57);
					match(WHITE_SPACE);
					}
					}
					setState(60); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==WHITE_SPACE );
				setState(62);
				((ArgsContext)_localctx).cmd = args(3);
				}
				break;
			case 4:
				{
				setState(64);
				((ArgsContext)_localctx).io_operator = match(LESS_THAN);
				setState(68);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==WHITE_SPACE) {
					{
					{
					setState(65);
					match(WHITE_SPACE);
					}
					}
					setState(70);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(71);
				((ArgsContext)_localctx).file = args(0);
				setState(73); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(72);
					match(WHITE_SPACE);
					}
					}
					setState(75); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==WHITE_SPACE );
				setState(77);
				((ArgsContext)_localctx).cmd = args(1);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(113);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(111);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
					case 1:
						{
						_localctx = new ArgsContext(_parentctx, _parentState);
						_localctx.cmd = _prevctx;
						_localctx.cmd = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_args);
						setState(81);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(85);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==WHITE_SPACE) {
							{
							{
							setState(82);
							match(WHITE_SPACE);
							}
							}
							setState(87);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(88);
						((ArgsContext)_localctx).io_operator = match(GREATER_THAN);
						setState(92);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==WHITE_SPACE) {
							{
							{
							setState(89);
							match(WHITE_SPACE);
							}
							}
							setState(94);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(95);
						((ArgsContext)_localctx).file = args(5);
						}
						break;
					case 2:
						{
						_localctx = new ArgsContext(_parentctx, _parentState);
						_localctx.cmd = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_args);
						setState(96);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(100);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==WHITE_SPACE) {
							{
							{
							setState(97);
							match(WHITE_SPACE);
							}
							}
							setState(102);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(103);
						((ArgsContext)_localctx).io_operator = match(LESS_THAN);
						setState(107);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==WHITE_SPACE) {
							{
							{
							setState(104);
							match(WHITE_SPACE);
							}
							}
							setState(109);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(110);
						((ArgsContext)_localctx).file = args(3);
						}
						break;
					}
					} 
				}
				setState(115);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ArgContext extends ParserRuleContext {
		public no_quotesContext no_quote;
		public no_quotesContext no_quotes() {
			return getRuleContext(no_quotesContext.class,0);
		}
		public ArgContext arg() {
			return getRuleContext(ArgContext.class,0);
		}
		public QuotedContext quoted() {
			return getRuleContext(QuotedContext.class,0);
		}
		public ArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arg; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CallVisitor ) return ((CallVisitor<? extends T>)visitor).visitArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgContext arg() throws RecognitionException {
		ArgContext _localctx = new ArgContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_arg);
		try {
			setState(124);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NON_KEYWORD:
				enterOuterAlt(_localctx, 1);
				{
				setState(116);
				((ArgContext)_localctx).no_quote = no_quotes();
				setState(118);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
				case 1:
					{
					setState(117);
					arg();
					}
					break;
				}
				}
				break;
			case T__0:
			case T__1:
			case T__2:
				enterOuterAlt(_localctx, 2);
				{
				setState(120);
				quoted();
				setState(122);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
				case 1:
					{
					setState(121);
					arg();
					}
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class no_quotesContext extends ParserRuleContext {
		public List<TerminalNode> NON_KEYWORD() { return getTokens(CallParser.NON_KEYWORD); }
		public TerminalNode NON_KEYWORD(int i) {
			return getToken(CallParser.NON_KEYWORD, i);
		}
		public no_quotesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_no_quotes; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CallVisitor ) return ((CallVisitor<? extends T>)visitor).visitno_quotes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final no_quotesContext no_quotes() throws RecognitionException {
		no_quotesContext _localctx = new no_quotesContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_no_quotes);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(127); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(126);
					match(NON_KEYWORD);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(129); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			} while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QuotedContext extends ParserRuleContext {
		public single_quotesContext single_quotes() {
			return getRuleContext(single_quotesContext.class,0);
		}
		public double_quotesContext double_quotes() {
			return getRuleContext(double_quotesContext.class,0);
		}
		public back_quotesContext back_quotes() {
			return getRuleContext(back_quotesContext.class,0);
		}
		public QuotedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quoted; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CallVisitor ) return ((CallVisitor<? extends T>)visitor).visitQuoted(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuotedContext quoted() throws RecognitionException {
		QuotedContext _localctx = new QuotedContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_quoted);
		try {
			setState(134);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(131);
				single_quotes();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(132);
				double_quotes();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 3);
				{
				setState(133);
				back_quotes();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class single_quotesContext extends ParserRuleContext {
		public Squote_contentContext single_quote_content() {
			return getRuleContext(Squote_contentContext.class,0);
		}
		public single_quotesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_single_quotes; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CallVisitor ) return ((CallVisitor<? extends T>)visitor).visitsingle_quotes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final single_quotesContext single_quotes() throws RecognitionException {
		single_quotesContext _localctx = new single_quotesContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_single_quotes);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			match(T__0);
			setState(137);
			single_quote_content();
			setState(138);
			match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Squote_contentContext extends ParserRuleContext {
		public List<TerminalNode> NON_KEYWORD() { return getTokens(CallParser.NON_KEYWORD); }
		public TerminalNode NON_KEYWORD(int i) {
			return getToken(CallParser.NON_KEYWORD, i);
		}
		public List<KeywordContext> keyword() {
			return getRuleContexts(KeywordContext.class);
		}
		public KeywordContext keyword(int i) {
			return getRuleContext(KeywordContext.class,i);
		}
		public List<TerminalNode> WHITE_SPACE() { return getTokens(CallParser.WHITE_SPACE); }
		public TerminalNode WHITE_SPACE(int i) {
			return getToken(CallParser.WHITE_SPACE, i);
		}
		public Squote_contentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_single_quote_content; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CallVisitor ) return ((CallVisitor<? extends T>)visitor).visitSquote_content(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Squote_contentContext single_quote_content() throws RecognitionException {
		Squote_contentContext _localctx = new Squote_contentContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_single_quote_content);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << WHITE_SPACE) | (1L << NON_KEYWORD) | (1L << SEMI) | (1L << PIPE) | (1L << GREATER_THAN) | (1L << LESS_THAN))) != 0)) {
				{
				setState(145);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case NON_KEYWORD:
					{
					setState(140);
					match(NON_KEYWORD);
					}
					break;
				case SEMI:
				case PIPE:
				case GREATER_THAN:
				case LESS_THAN:
					{
					setState(141);
					keyword();
					}
					break;
				case WHITE_SPACE:
					{
					setState(142);
					match(WHITE_SPACE);
					}
					break;
				case T__1:
					{
					setState(143);
					match(T__1);
					}
					break;
				case T__2:
					{
					setState(144);
					match(T__2);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(149);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class double_quotesContext extends ParserRuleContext {
		public Dquote_contentContext double_quote_content() {
			return getRuleContext(Dquote_contentContext.class,0);
		}
		public double_quotesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_double_quotes; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CallVisitor ) return ((CallVisitor<? extends T>)visitor).visitdouble_quotes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final double_quotesContext double_quotes() throws RecognitionException {
		double_quotesContext _localctx = new double_quotesContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_double_quotes);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			match(T__1);
			setState(151);
			double_quote_content();
			setState(152);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Dquote_contentContext extends ParserRuleContext {
		public Token content;
		public Dquote_contentContext double_quote_content() {
			return getRuleContext(Dquote_contentContext.class,0);
		}
		public TerminalNode NON_KEYWORD() { return getToken(CallParser.NON_KEYWORD, 0); }
		public TerminalNode SEMI() { return getToken(CallParser.SEMI, 0); }
		public TerminalNode PIPE() { return getToken(CallParser.PIPE, 0); }
		public TerminalNode GREATER_THAN() { return getToken(CallParser.GREATER_THAN, 0); }
		public TerminalNode LESS_THAN() { return getToken(CallParser.LESS_THAN, 0); }
		public TerminalNode WHITE_SPACE() { return getToken(CallParser.WHITE_SPACE, 0); }
		public back_quotesContext back_quotes() {
			return getRuleContext(back_quotesContext.class,0);
		}
		public Dquote_contentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_double_quote_content; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CallVisitor ) return ((CallVisitor<? extends T>)visitor).visitDquote_content(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Dquote_contentContext double_quote_content() throws RecognitionException {
		Dquote_contentContext _localctx = new Dquote_contentContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_double_quote_content);
		int _la;
		try {
			setState(160);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case WHITE_SPACE:
			case NON_KEYWORD:
			case SEMI:
			case PIPE:
			case GREATER_THAN:
			case LESS_THAN:
				enterOuterAlt(_localctx, 1);
				{
				setState(154);
				((Dquote_contentContext)_localctx).content = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << WHITE_SPACE) | (1L << NON_KEYWORD) | (1L << SEMI) | (1L << PIPE) | (1L << GREATER_THAN) | (1L << LESS_THAN))) != 0)) ) {
					((Dquote_contentContext)_localctx).content = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(155);
				double_quote_content();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 2);
				{
				setState(156);
				back_quotes();
				setState(157);
				double_quote_content();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 3);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class back_quotesContext extends ParserRuleContext {
		public Bquote_contentContext content;
		public Bquote_contentContext back_quote_content() {
			return getRuleContext(Bquote_contentContext.class,0);
		}
		public back_quotesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_back_quotes; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CallVisitor ) return ((CallVisitor<? extends T>)visitor).visitback_quotes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final back_quotesContext back_quotes() throws RecognitionException {
		back_quotesContext _localctx = new back_quotesContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_back_quotes);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			match(T__2);
			setState(163);
			((back_quotesContext)_localctx).content = back_quote_content();
			setState(164);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Bquote_contentContext extends ParserRuleContext {
		public List<TerminalNode> NON_KEYWORD() { return getTokens(CallParser.NON_KEYWORD); }
		public TerminalNode NON_KEYWORD(int i) {
			return getToken(CallParser.NON_KEYWORD, i);
		}
		public List<KeywordContext> keyword() {
			return getRuleContexts(KeywordContext.class);
		}
		public KeywordContext keyword(int i) {
			return getRuleContext(KeywordContext.class,i);
		}
		public List<TerminalNode> WHITE_SPACE() { return getTokens(CallParser.WHITE_SPACE); }
		public TerminalNode WHITE_SPACE(int i) {
			return getToken(CallParser.WHITE_SPACE, i);
		}
		public Bquote_contentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_back_quote_content; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CallVisitor ) return ((CallVisitor<? extends T>)visitor).visitBquote_content(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Bquote_contentContext back_quote_content() throws RecognitionException {
		Bquote_contentContext _localctx = new Bquote_contentContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_back_quote_content);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << WHITE_SPACE) | (1L << NON_KEYWORD) | (1L << SEMI) | (1L << PIPE) | (1L << GREATER_THAN) | (1L << LESS_THAN))) != 0)) {
				{
				setState(171);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case NON_KEYWORD:
					{
					setState(166);
					match(NON_KEYWORD);
					}
					break;
				case SEMI:
				case PIPE:
				case GREATER_THAN:
				case LESS_THAN:
					{
					setState(167);
					keyword();
					}
					break;
				case WHITE_SPACE:
					{
					setState(168);
					match(WHITE_SPACE);
					}
					break;
				case T__1:
					{
					setState(169);
					match(T__1);
					}
					break;
				case T__0:
					{
					setState(170);
					match(T__0);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(175);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KeywordContext extends ParserRuleContext {
		public TerminalNode SEMI() { return getToken(CallParser.SEMI, 0); }
		public TerminalNode PIPE() { return getToken(CallParser.PIPE, 0); }
		public TerminalNode GREATER_THAN() { return getToken(CallParser.GREATER_THAN, 0); }
		public TerminalNode LESS_THAN() { return getToken(CallParser.LESS_THAN, 0); }
		public KeywordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyword; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CallVisitor ) return ((CallVisitor<? extends T>)visitor).visitKeyword(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeywordContext keyword() throws RecognitionException {
		KeywordContext _localctx = new KeywordContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_keyword);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SEMI) | (1L << PIPE) | (1L << GREATER_THAN) | (1L << LESS_THAN))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1:
			return args_sempred((ArgsContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean args_sempred(ArgsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 4);
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\13\u00b5\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\3\2\7\2\34\n\2\f\2\16\2\37\13\2\3\2\3\2\7\2#\n\2"+
		"\f\2\16\2&\13\2\3\2\3\2\3\3\3\3\3\3\3\3\6\3.\n\3\r\3\16\3/\3\3\3\3\3\3"+
		"\3\3\7\3\66\n\3\f\3\16\39\13\3\3\3\3\3\6\3=\n\3\r\3\16\3>\3\3\3\3\3\3"+
		"\3\3\7\3E\n\3\f\3\16\3H\13\3\3\3\3\3\6\3L\n\3\r\3\16\3M\3\3\3\3\5\3R\n"+
		"\3\3\3\3\3\7\3V\n\3\f\3\16\3Y\13\3\3\3\3\3\7\3]\n\3\f\3\16\3`\13\3\3\3"+
		"\3\3\3\3\7\3e\n\3\f\3\16\3h\13\3\3\3\3\3\7\3l\n\3\f\3\16\3o\13\3\3\3\7"+
		"\3r\n\3\f\3\16\3u\13\3\3\4\3\4\5\4y\n\4\3\4\3\4\5\4}\n\4\5\4\177\n\4\3"+
		"\5\6\5\u0082\n\5\r\5\16\5\u0083\3\6\3\6\3\6\5\6\u0089\n\6\3\7\3\7\3\7"+
		"\3\7\3\b\3\b\3\b\3\b\3\b\7\b\u0094\n\b\f\b\16\b\u0097\13\b\3\t\3\t\3\t"+
		"\3\t\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u00a3\n\n\3\13\3\13\3\13\3\13\3\f\3\f"+
		"\3\f\3\f\3\f\7\f\u00ae\n\f\f\f\16\f\u00b1\13\f\3\r\3\r\3\r\2\3\4\16\2"+
		"\4\6\b\n\f\16\20\22\24\26\30\2\4\4\2\3\3\6\13\3\2\b\13\2\u00ca\2\35\3"+
		"\2\2\2\4Q\3\2\2\2\6~\3\2\2\2\b\u0081\3\2\2\2\n\u0088\3\2\2\2\f\u008a\3"+
		"\2\2\2\16\u0095\3\2\2\2\20\u0098\3\2\2\2\22\u00a2\3\2\2\2\24\u00a4\3\2"+
		"\2\2\26\u00af\3\2\2\2\30\u00b2\3\2\2\2\32\34\7\6\2\2\33\32\3\2\2\2\34"+
		"\37\3\2\2\2\35\33\3\2\2\2\35\36\3\2\2\2\36 \3\2\2\2\37\35\3\2\2\2 $\5"+
		"\4\3\2!#\7\6\2\2\"!\3\2\2\2#&\3\2\2\2$\"\3\2\2\2$%\3\2\2\2%\'\3\2\2\2"+
		"&$\3\2\2\2\'(\7\2\2\3(\3\3\2\2\2)*\b\3\1\2*R\5\6\4\2+-\5\6\4\2,.\7\6\2"+
		"\2-,\3\2\2\2./\3\2\2\2/-\3\2\2\2/\60\3\2\2\2\60\61\3\2\2\2\61\62\5\4\3"+
		"\7\62R\3\2\2\2\63\67\7\n\2\2\64\66\7\6\2\2\65\64\3\2\2\2\669\3\2\2\2\67"+
		"\65\3\2\2\2\678\3\2\2\28:\3\2\2\29\67\3\2\2\2:<\5\4\3\2;=\7\6\2\2<;\3"+
		"\2\2\2=>\3\2\2\2><\3\2\2\2>?\3\2\2\2?@\3\2\2\2@A\5\4\3\5AR\3\2\2\2BF\7"+
		"\13\2\2CE\7\6\2\2DC\3\2\2\2EH\3\2\2\2FD\3\2\2\2FG\3\2\2\2GI\3\2\2\2HF"+
		"\3\2\2\2IK\5\4\3\2JL\7\6\2\2KJ\3\2\2\2LM\3\2\2\2MK\3\2\2\2MN\3\2\2\2N"+
		"O\3\2\2\2OP\5\4\3\3PR\3\2\2\2Q)\3\2\2\2Q+\3\2\2\2Q\63\3\2\2\2QB\3\2\2"+
		"\2Rs\3\2\2\2SW\f\6\2\2TV\7\6\2\2UT\3\2\2\2VY\3\2\2\2WU\3\2\2\2WX\3\2\2"+
		"\2XZ\3\2\2\2YW\3\2\2\2Z^\7\n\2\2[]\7\6\2\2\\[\3\2\2\2]`\3\2\2\2^\\\3\2"+
		"\2\2^_\3\2\2\2_a\3\2\2\2`^\3\2\2\2ar\5\4\3\7bf\f\4\2\2ce\7\6\2\2dc\3\2"+
		"\2\2eh\3\2\2\2fd\3\2\2\2fg\3\2\2\2gi\3\2\2\2hf\3\2\2\2im\7\13\2\2jl\7"+
		"\6\2\2kj\3\2\2\2lo\3\2\2\2mk\3\2\2\2mn\3\2\2\2np\3\2\2\2om\3\2\2\2pr\5"+
		"\4\3\5qS\3\2\2\2qb\3\2\2\2ru\3\2\2\2sq\3\2\2\2st\3\2\2\2t\5\3\2\2\2us"+
		"\3\2\2\2vx\5\b\5\2wy\5\6\4\2xw\3\2\2\2xy\3\2\2\2y\177\3\2\2\2z|\5\n\6"+
		"\2{}\5\6\4\2|{\3\2\2\2|}\3\2\2\2}\177\3\2\2\2~v\3\2\2\2~z\3\2\2\2\177"+
		"\7\3\2\2\2\u0080\u0082\7\7\2\2\u0081\u0080\3\2\2\2\u0082\u0083\3\2\2\2"+
		"\u0083\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084\t\3\2\2\2\u0085\u0089\5"+
		"\f\7\2\u0086\u0089\5\20\t\2\u0087\u0089\5\24\13\2\u0088\u0085\3\2\2\2"+
		"\u0088\u0086\3\2\2\2\u0088\u0087\3\2\2\2\u0089\13\3\2\2\2\u008a\u008b"+
		"\7\3\2\2\u008b\u008c\5\16\b\2\u008c\u008d\7\3\2\2\u008d\r\3\2\2\2\u008e"+
		"\u0094\7\7\2\2\u008f\u0094\5\30\r\2\u0090\u0094\7\6\2\2\u0091\u0094\7"+
		"\4\2\2\u0092\u0094\7\5\2\2\u0093\u008e\3\2\2\2\u0093\u008f\3\2\2\2\u0093"+
		"\u0090\3\2\2\2\u0093\u0091\3\2\2\2\u0093\u0092\3\2\2\2\u0094\u0097\3\2"+
		"\2\2\u0095\u0093\3\2\2\2\u0095\u0096\3\2\2\2\u0096\17\3\2\2\2\u0097\u0095"+
		"\3\2\2\2\u0098\u0099\7\4\2\2\u0099\u009a\5\22\n\2\u009a\u009b\7\4\2\2"+
		"\u009b\21\3\2\2\2\u009c\u009d\t\2\2\2\u009d\u00a3\5\22\n\2\u009e\u009f"+
		"\5\24\13\2\u009f\u00a0\5\22\n\2\u00a0\u00a3\3\2\2\2\u00a1\u00a3\3\2\2"+
		"\2\u00a2\u009c\3\2\2\2\u00a2\u009e\3\2\2\2\u00a2\u00a1\3\2\2\2\u00a3\23"+
		"\3\2\2\2\u00a4\u00a5\7\5\2\2\u00a5\u00a6\5\26\f\2\u00a6\u00a7\7\5\2\2"+
		"\u00a7\25\3\2\2\2\u00a8\u00ae\7\7\2\2\u00a9\u00ae\5\30\r\2\u00aa\u00ae"+
		"\7\6\2\2\u00ab\u00ae\7\4\2\2\u00ac\u00ae\7\3\2\2\u00ad\u00a8\3\2\2\2\u00ad"+
		"\u00a9\3\2\2\2\u00ad\u00aa\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ac\3\2"+
		"\2\2\u00ae\u00b1\3\2\2\2\u00af\u00ad\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0"+
		"\27\3\2\2\2\u00b1\u00af\3\2\2\2\u00b2\u00b3\t\3\2\2\u00b3\31\3\2\2\2\32"+
		"\35$/\67>FMQW^fmqsx|~\u0083\u0088\u0093\u0095\u00a2\u00ad\u00af";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}