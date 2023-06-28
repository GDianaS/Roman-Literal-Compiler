package lexer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class Lexer {
	private static final char EOF_CHAR = (char) -1;
	private static int line = 1;
	private BufferedReader reader;
	private char peek;
	private Hashtable<String, Tag> keywords;

	public Lexer(File file) {
		try {
			this.reader = new BufferedReader(
					new FileReader(file));
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.peek = ' ';
		keywords = new Hashtable<String, Tag>();
		keywords.put("programa", Tag.PROGRAM);
		keywords.put("inicio", Tag.BEGIN);
		keywords.put("fim", Tag.END);
		keywords.put("escreva", Tag.WRITE);
		keywords.put("se", Tag.IF);
		keywords.put("verdadeiro", Tag.TRUE);
		keywords.put("falso", Tag.FALSE);
		keywords.put("inteiro", Tag.INT);
		keywords.put("real", Tag.REAL);
		keywords.put("booleano", Tag.BOOL);
	}

	public static int line() {
		return line;
	}

	private char nextChar() {
		if (peek == '\n')
			line++;
		try {
			peek = (char) reader.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return peek;
	}

	private static boolean isWhitespace(int c) {
		switch (c) {
			case ' ':
			case '\t':
			case '\n':
			case '\r':
				return true;
			default:
				return false;
		}
	}

	private static boolean isIdStart(int c) {
		return (Character.isAlphabetic(c) || c == '_');
	}

	private static boolean isIdPart(int c) {
		return (isIdStart(c) || Character.isDigit(c));
	}

	private static boolean isRoman(int c) {
		switch (c) {
			case 'i':
			case 'v':
			case 'x':
			case 'l':
			case 'c':
			case 'd':
			case 'm':
				return true;
			default:
				return false;
		}
	}

	public Token nextToken() {
		while (isWhitespace(peek))
			nextChar();
		switch (peek) {
			case '=':
				nextChar();
				return new Token(Tag.ASSIGN, "=");
			case '+':
				nextChar();
				return new Token(Tag.SUM, "+");
			case '-':
				nextChar();
				return new Token(Tag.SUB, "-");
			case '*':
				nextChar();
				return new Token(Tag.MUL, "*");
			case '|':
				nextChar();
				return new Token(Tag.OR, "|");
			case '<':
				nextChar();
				if (peek == '=') {
					nextChar();
					return new Token(Tag.LE, "<=");
				}
				return new Token(Tag.LT, "<");
			case '>':
				nextChar();
				return new Token(Tag.GT, ">");
			case ';':
				nextChar();
				return new Token(Tag.SEMI, ";");
			case '.':
				nextChar();
				return new Token(Tag.DOT, ".");
			case '(':
				nextChar();
				return new Token(Tag.LPAREN, "(");
			case ')':
				nextChar();
				return new Token(Tag.RPAREN, ")");
			case EOF_CHAR:
				return new Token(Tag.EOF, "");

			default:
				if (Character.isDigit(peek)) {
					String num = "";
					do {
						num += peek;
						if (peek == '0') {
							nextChar();
							if (peek == 'r') {
								String num_rom = "0r";
								nextChar();
								do {
									num_rom += peek;
									nextChar();
								} while (isRoman(peek));
								return new Token(Tag.LIT_ROM, num_rom);
							}
						} else {
							nextChar();
						}

					} while (Character.isDigit(peek));

					if (peek != '.')
						return new Token(Tag.LIT_INT, num);
					do {
						num += peek;
						nextChar();
					} while (Character.isDigit(peek));
					return new Token(Tag.LIT_REAL, num);
				} else if (isIdStart(peek)) {
					String id = "";
					do {
						id += peek;
						nextChar();
					} while (isIdPart(peek));
					if (keywords.containsKey(id))
						return new Token(keywords.get(id), id);
					return new Token(Tag.ID, id);
				}
		}
		String unk = String.valueOf(peek);
		nextChar();
		return new Token(Tag.UNK, unk);
	}
}