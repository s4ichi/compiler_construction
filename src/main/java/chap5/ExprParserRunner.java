package chap5;
import stone.ast.ASTree;
import stone.*;

public class ExprParserRunner {
    public static void main(String[] args) throws ParseException {
        Lexer l = new Lexer(new CodeDialog());
        ExprParser p = new ExprParser();
        while (l.peek(0) != Token.EOF) {
            ASTree ast = p.parse(l);
            System.out.println("=> " + ast.toString());
        }
    }
}
