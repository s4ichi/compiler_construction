package chap7;
import stone.VariableParser;
import stone.ParseException;
import chap6.BasicInterpreter;

public class VariableInterpreter extends BasicInterpreter{
    public static void main(String[] args) throws ParseException {
        run(new VariableParser(), new NestedEnv());
    }
}
