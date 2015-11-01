package stone;
import static stone.Parser.rule;
import stone.ast.*;

public class VariableParser extends FuncParser {
    Parser local = rule(VariableStmnt.class).sep("variable").identifier(Name.class, reserved);

    public VariableParser() {
        statement.insertChoice(local);
    }
}
