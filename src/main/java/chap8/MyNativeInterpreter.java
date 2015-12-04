package chap8;
import stone.ClosureParser;
import stone.ParseException;
import chap6.BasicInterpreter;
import chap7.NestedEnv;

public class MyNativeInterpreter extends BasicInterpreter {
    public static void main(String[] args) throws ParseException {
        run(new ClosureParser(),
            new MyNatives().environment(new NestedEnv()));
    }
}
