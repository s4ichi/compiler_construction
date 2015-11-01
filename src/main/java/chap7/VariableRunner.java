package chap7;
import javassist.gluonj.util.Loader;

public class VariableRunner {
    public static void main(String[] args) throws Throwable {
        Loader.run(VariableInterpreter.class, args, VariableEvaluator.class);
    }
}
