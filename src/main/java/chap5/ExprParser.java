package chap5;
import static stone.Parser.rule;

import stone.*;
import stone.ast.*;

public class ExprParser {
    Parser expr0 = rule();

    Parser factor = rule().or(
                              rule().number(),
                              rule().sep("(").ast(expr0).sep(")")
                              );

    Parser term = rule().ast(factor).repeat(
                                            rule().or(
                                                      rule().token("*"),
                                                      rule().token("/")
                                                      )
                                            .ast(factor)
                                            );

    Parser expr = expr0.ast(term).repeat(
                                          rule().or(
                                                    rule().token("+"),
                                                    rule().token("-")
                                                    )
                                          .ast(term)
                                          );

    Parser top = rule().ast(expr).sep(Token.EOL);

    public ASTree parse(Lexer lexer) throws ParseException {
        return top.parse(lexer);
    }
}
