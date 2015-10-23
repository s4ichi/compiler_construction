package chap6;
import javassist.gluonj.util.Loader;

public class MyRunner {
    public static void main(String[] args) throws Throwable {
        Loader.run(BasicInterpreter.class, args, MyBasicEvaluator.class);
    }
}
