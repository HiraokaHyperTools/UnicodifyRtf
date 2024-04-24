// Generated from x:/Proj/UnicodifyRtf/UnicodifyRtf/Rtf1Parser.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class Rtf1Parser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		Escape=1, Open=2, Close=3, NL=4, Text=5, Hex=6, Asterisk=7, Control=8, 
		Key=9, Value=10;
	public static final int
		RULE_document = 0, RULE_node = 1, RULE_escape = 2;
	private static String[] makeRuleNames() {
		return new String[] {
			"document", "node", "escape"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'\\'", "'{'", "'}'", null, null, null, "'*'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "Escape", "Open", "Close", "NL", "Text", "Hex", "Asterisk", "Control", 
			"Key", "Value"
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
	public String getGrammarFileName() { return "Rtf1Parser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public Rtf1Parser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DocumentContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(Rtf1Parser.EOF, 0); }
		public List<NodeContext> node() {
			return getRuleContexts(NodeContext.class);
		}
		public NodeContext node(int i) {
			return getRuleContext(NodeContext.class,i);
		}
		public DocumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_document; }
	}

	public final DocumentContext document() throws RecognitionException {
		DocumentContext _localctx = new DocumentContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_document);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(9);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 62L) != 0)) {
				{
				{
				setState(6);
				node();
				}
				}
				setState(11);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(12);
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

	@SuppressWarnings("CheckReturnValue")
	public static class NodeContext extends ParserRuleContext {
		public EscapeContext escape() {
			return getRuleContext(EscapeContext.class,0);
		}
		public TerminalNode Open() { return getToken(Rtf1Parser.Open, 0); }
		public TerminalNode Close() { return getToken(Rtf1Parser.Close, 0); }
		public TerminalNode Text() { return getToken(Rtf1Parser.Text, 0); }
		public TerminalNode NL() { return getToken(Rtf1Parser.NL, 0); }
		public NodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_node; }
	}

	public final NodeContext node() throws RecognitionException {
		NodeContext _localctx = new NodeContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_node);
		try {
			setState(19);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Escape:
				enterOuterAlt(_localctx, 1);
				{
				setState(14);
				escape();
				}
				break;
			case Open:
				enterOuterAlt(_localctx, 2);
				{
				setState(15);
				match(Open);
				}
				break;
			case Close:
				enterOuterAlt(_localctx, 3);
				{
				setState(16);
				match(Close);
				}
				break;
			case Text:
				enterOuterAlt(_localctx, 4);
				{
				setState(17);
				match(Text);
				}
				break;
			case NL:
				enterOuterAlt(_localctx, 5);
				{
				setState(18);
				match(NL);
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

	@SuppressWarnings("CheckReturnValue")
	public static class EscapeContext extends ParserRuleContext {
		public TerminalNode Escape() { return getToken(Rtf1Parser.Escape, 0); }
		public TerminalNode Hex() { return getToken(Rtf1Parser.Hex, 0); }
		public TerminalNode Asterisk() { return getToken(Rtf1Parser.Asterisk, 0); }
		public TerminalNode Control() { return getToken(Rtf1Parser.Control, 0); }
		public EscapeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_escape; }
	}

	public final EscapeContext escape() throws RecognitionException {
		EscapeContext _localctx = new EscapeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_escape);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(21);
			match(Escape);
			setState(22);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 448L) != 0)) ) {
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

	public static final String _serializedATN =
		"\u0004\u0001\n\u0019\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0001\u0000\u0005\u0000\b\b\u0000\n\u0000\f\u0000\u000b"+
		"\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0003\u0001\u0014\b\u0001\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0000\u0000\u0003\u0000\u0002\u0004\u0000\u0001\u0001"+
		"\u0000\u0006\b\u001a\u0000\t\u0001\u0000\u0000\u0000\u0002\u0013\u0001"+
		"\u0000\u0000\u0000\u0004\u0015\u0001\u0000\u0000\u0000\u0006\b\u0003\u0002"+
		"\u0001\u0000\u0007\u0006\u0001\u0000\u0000\u0000\b\u000b\u0001\u0000\u0000"+
		"\u0000\t\u0007\u0001\u0000\u0000\u0000\t\n\u0001\u0000\u0000\u0000\n\f"+
		"\u0001\u0000\u0000\u0000\u000b\t\u0001\u0000\u0000\u0000\f\r\u0005\u0000"+
		"\u0000\u0001\r\u0001\u0001\u0000\u0000\u0000\u000e\u0014\u0003\u0004\u0002"+
		"\u0000\u000f\u0014\u0005\u0002\u0000\u0000\u0010\u0014\u0005\u0003\u0000"+
		"\u0000\u0011\u0014\u0005\u0005\u0000\u0000\u0012\u0014\u0005\u0004\u0000"+
		"\u0000\u0013\u000e\u0001\u0000\u0000\u0000\u0013\u000f\u0001\u0000\u0000"+
		"\u0000\u0013\u0010\u0001\u0000\u0000\u0000\u0013\u0011\u0001\u0000\u0000"+
		"\u0000\u0013\u0012\u0001\u0000\u0000\u0000\u0014\u0003\u0001\u0000\u0000"+
		"\u0000\u0015\u0016\u0005\u0001\u0000\u0000\u0016\u0017\u0007\u0000\u0000"+
		"\u0000\u0017\u0005\u0001\u0000\u0000\u0000\u0002\t\u0013";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}