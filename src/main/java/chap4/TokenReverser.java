package chap4;

import java.util.*;
import stone.*;
import stone.ast.*;

public class TokenReverser {

    public static void main(String[] args) throws ParseException {
        Lexer l = new Lexer(new CodeDialog());
        LinkedList<ASTree> stack = new LinkedList<ASTree>();
        for (Token t; (t = l.read()) != Token.EOF; ) {
            if (t.isNumber()) {
                stack.push(new NumberLiteral(t));
            }
        }
        for (ASTree tree : stack) {
            System.out.println("=> " + tree);
        }
    }

}
