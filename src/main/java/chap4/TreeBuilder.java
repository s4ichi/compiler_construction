package chap4;

import java.util.*;
import stone.*;
import stone.ast.*;

public class TreeBuilder {

    public static void main(String[] args) throws ParseException {
        Lexer l = new Lexer(new CodeDialog());
        LinkedList<ASTree> stack = new LinkedList<ASTree>();
        for (Token t; (t = l.read()) != Token.EOF; ) {
            if(t.getText() == Token.EOL) { continue; }

            if (t.isNumber()) {
                stack.push(new NumberLiteral(t));
            } else if(t.isIdentifier()) {
                ASTree right = stack.pop();
                ASTree left = stack.pop();

                ArrayList<ASTree> binary_children = new ArrayList<ASTree>();
                binary_children.add(left);
                binary_children.add(new Name(t));
                binary_children.add(right);

                BinaryExpr expr = new BinaryExpr(binary_children);
                stack.push(expr);
            }
        }
        for (ASTree tree : stack) {
            System.out.println("=> " + tree);
        }
    }

}
