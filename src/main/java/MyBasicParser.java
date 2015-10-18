package stone;
import static stone.Parser.rule;
import stone.ast.*;
import stone.Parser.Operators;

public class MyBasicParser extends BasicParser {
    public MyBasicParser() {
        operators.add("^", 3, Operators.LEFT);
        //operators.add("+=", 1, Operators.RIGHT);

        statement0 = rule();

        block = rule(BlockStmnt.class).sep("{").option(statement0)
            .repeat(rule().sep(";", Token.EOL).option(statement0)).sep("}");

        statement = statement0.or(
                                  rule(IfStmnt.class).sep("if").ast(expr).ast(block)
                                  .repeat(rule().sep("elsif").ast(expr).ast(block))
                                  .option(rule().sep("else").ast(block)),
                                  rule(WhileStmnt.class).sep("while").ast(expr).ast(block),
                                  simple
                                  );

        program = rule().or(statement, rule(NullStmnt.class)).sep(";", Token.EOL);
    }
}
