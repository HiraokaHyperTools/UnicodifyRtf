// Generated from x:/Proj/UnicodifyRtf/UnicodifyRtf/Rtf1Lexer.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class Rtf1Lexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		Escape=1, Open=2, Close=3, NL=4, Text=5, Hex=6, Asterisk=7, Escaped=8, 
		Control=9, Key=10, Value=11;
	public static final int
		EscapeMode=1;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE", "EscapeMode"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"Escape", "Open", "Close", "NL", "Text", "Hex", "Asterisk", "Escaped", 
			"Control", "Key", "Value"
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
			null, "Escape", "Open", "Close", "NL", "Text", "Hex", "Asterisk", "Escaped", 
			"Control", "Key", "Value"
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


	public Rtf1Lexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Rtf1Lexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u000bH\u0006\uffff\uffff\u0006\uffff\uffff\u0002\u0000\u0007"+
		"\u0000\u0002\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007"+
		"\u0003\u0002\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007"+
		"\u0006\u0002\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n"+
		"\u0007\n\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0004\u0003\"\b\u0003\u000b"+
		"\u0003\f\u0003#\u0001\u0004\u0004\u0004\'\b\u0004\u000b\u0004\f\u0004"+
		"(\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0003\b;\b\b\u0001\b\u0001\b"+
		"\u0001\t\u0004\t@\b\t\u000b\t\f\tA\u0001\n\u0004\nE\b\n\u000b\n\f\nF\u0000"+
		"\u0000\u000b\u0002\u0001\u0004\u0002\u0006\u0003\b\u0004\n\u0005\f\u0006"+
		"\u000e\u0007\u0010\b\u0012\t\u0014\n\u0016\u000b\u0002\u0000\u0001\u0006"+
		"\u0002\u0000\n\n\r\r\u0005\u0000\n\n\r\r\\\\{{}}\u0003\u000009AFaf\u0003"+
		"\u0000\\\\{{}}\u0002\u0000AZaz\u0001\u000009K\u0000\u0002\u0001\u0000"+
		"\u0000\u0000\u0000\u0004\u0001\u0000\u0000\u0000\u0000\u0006\u0001\u0000"+
		"\u0000\u0000\u0000\b\u0001\u0000\u0000\u0000\u0000\n\u0001\u0000\u0000"+
		"\u0000\u0001\f\u0001\u0000\u0000\u0000\u0001\u000e\u0001\u0000\u0000\u0000"+
		"\u0001\u0010\u0001\u0000\u0000\u0000\u0001\u0012\u0001\u0000\u0000\u0000"+
		"\u0001\u0014\u0001\u0000\u0000\u0000\u0001\u0016\u0001\u0000\u0000\u0000"+
		"\u0002\u0018\u0001\u0000\u0000\u0000\u0004\u001c\u0001\u0000\u0000\u0000"+
		"\u0006\u001e\u0001\u0000\u0000\u0000\b!\u0001\u0000\u0000\u0000\n&\u0001"+
		"\u0000\u0000\u0000\f*\u0001\u0000\u0000\u0000\u000e0\u0001\u0000\u0000"+
		"\u0000\u00104\u0001\u0000\u0000\u0000\u00128\u0001\u0000\u0000\u0000\u0014"+
		"?\u0001\u0000\u0000\u0000\u0016D\u0001\u0000\u0000\u0000\u0018\u0019\u0005"+
		"\\\u0000\u0000\u0019\u001a\u0001\u0000\u0000\u0000\u001a\u001b\u0006\u0000"+
		"\u0000\u0000\u001b\u0003\u0001\u0000\u0000\u0000\u001c\u001d\u0005{\u0000"+
		"\u0000\u001d\u0005\u0001\u0000\u0000\u0000\u001e\u001f\u0005}\u0000\u0000"+
		"\u001f\u0007\u0001\u0000\u0000\u0000 \"\u0007\u0000\u0000\u0000! \u0001"+
		"\u0000\u0000\u0000\"#\u0001\u0000\u0000\u0000#!\u0001\u0000\u0000\u0000"+
		"#$\u0001\u0000\u0000\u0000$\t\u0001\u0000\u0000\u0000%\'\b\u0001\u0000"+
		"\u0000&%\u0001\u0000\u0000\u0000\'(\u0001\u0000\u0000\u0000(&\u0001\u0000"+
		"\u0000\u0000()\u0001\u0000\u0000\u0000)\u000b\u0001\u0000\u0000\u0000"+
		"*+\u0005\'\u0000\u0000+,\u0007\u0002\u0000\u0000,-\u0007\u0002\u0000\u0000"+
		"-.\u0001\u0000\u0000\u0000./\u0006\u0005\u0001\u0000/\r\u0001\u0000\u0000"+
		"\u000001\u0005*\u0000\u000012\u0001\u0000\u0000\u000023\u0006\u0006\u0001"+
		"\u00003\u000f\u0001\u0000\u0000\u000045\u0007\u0003\u0000\u000056\u0001"+
		"\u0000\u0000\u000067\u0006\u0007\u0001\u00007\u0011\u0001\u0000\u0000"+
		"\u00008:\u0003\u0014\t\u00009;\u0003\u0016\n\u0000:9\u0001\u0000\u0000"+
		"\u0000:;\u0001\u0000\u0000\u0000;<\u0001\u0000\u0000\u0000<=\u0006\b\u0001"+
		"\u0000=\u0013\u0001\u0000\u0000\u0000>@\u0007\u0004\u0000\u0000?>\u0001"+
		"\u0000\u0000\u0000@A\u0001\u0000\u0000\u0000A?\u0001\u0000\u0000\u0000"+
		"AB\u0001\u0000\u0000\u0000B\u0015\u0001\u0000\u0000\u0000CE\u0007\u0005"+
		"\u0000\u0000DC\u0001\u0000\u0000\u0000EF\u0001\u0000\u0000\u0000FD\u0001"+
		"\u0000\u0000\u0000FG\u0001\u0000\u0000\u0000G\u0017\u0001\u0000\u0000"+
		"\u0000\u0007\u0000\u0001#(:AF\u0002\u0005\u0001\u0000\u0004\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}