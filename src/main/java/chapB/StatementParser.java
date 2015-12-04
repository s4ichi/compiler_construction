package chapB;
import java.util.Arrays;
import java.util.ArrayList;
import stone.*;
import stone.ast.*;

public class StatementParser extends ExprParser {

    public StatementParser(Lexer p) {
        super(p);
    }

    public ASTree block() throws ParseException {
        ArrayList<ASTree> statements = new ArrayList<ASTree>();

        token("{");
        do {
            if(isToken(";")) {
                token(";");
            } else if(isToken(Token.EOL)) {
                token(Token.EOL);
            } else {
                statements.add(statement());
            }
        }while(!isToken("}"));
        token("}");

        return new ASTList(statements);
    }

    public ASTree statement() throws ParseException {
        if(isToken("if")) {
            token("if");
            ASTree condition = expression();
            ASTree thenBlock = block();
            if(isToken("else")) {
                token("else");
                ASTree elseBlock = block();
                return new IfStmnt(Arrays.asList(condition, thenBlock, elseBlock));
            } else {
                return new IfStmnt(Arrays.asList(condition, thenBlock));
            }
        } else if(isToken("while")) {
            token("while");
            ASTree condition = expression();
            ASTree body = block();
            return new WhileStmnt(Arrays.asList(condition, body));
        } else {
            return expression();
        }
    }

    public static void main(String[] args) throws ParseException {
        Lexer lexer = new Lexer(new CodeDialog());
        StatementParser p = new StatementParser(lexer);
        ASTree t = p.statement();
        System.out.println("=> " + t);
    }
}
