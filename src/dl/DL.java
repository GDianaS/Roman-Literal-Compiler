package dl;

import java.io.File;
import java.io.PrintWriter;

import lexer.Lexer;
import lexer.Token;
import parser.Parser;
import lexer.Tag;

public class DL {
	public static void main(String[] args) {
		// Análise
		Lexer l = new Lexer(
				new File("prog.dl"));

		// Token t = l.nextToken();
		// while (t.tag() != Tag.EOF) {
		// System.out.println(t);
		// t = l.nextToken();
		// }

		Parser p = new Parser(l);
		p.parse();

		// Imprimindo a árvore sintática e código intermediário
		System.out.println(p.parseTree());
		System.out.println(p.code());
		System.out.println("finalizado");

		// Construindo arquivo
		try {
			PrintWriter pw = new PrintWriter("prog.ll");
			pw.write(p.code());
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
