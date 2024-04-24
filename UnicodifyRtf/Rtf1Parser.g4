parser grammar Rtf1Parser;

options {
	tokenVocab = Rtf1Lexer;
}

document: node* EOF;
node: escape | Open | Close | Text | NL;

escape: Escape (Hex | Asterisk | Control);
