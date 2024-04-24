lexer grammar Rtf1Lexer;

Escape: '\\' -> pushMode(EscapeMode);
Open: '{';
Close: '}';
NL: [\r\n]+;
Text: ~[\r\n{}\\]+;

mode EscapeMode;

Hex: '\'' [0-9a-fA-F] [0-9a-fA-F] -> popMode;
Asterisk: '*' -> popMode;
Control: Key Value? -> popMode;
Key: [a-zA-Z]+;
Value: [0-9]+;