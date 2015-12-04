package chap8;
import javassist.gluonj.util.Loader;
import chap7.ClosureEvaluator;

public class HogeRunner {
    public static void main(String[] args) throws Throwable {
        Loader.run(MyNativeInterpreter.class, args, NativeEvaluator.class,
                   ClosureEvaluator.class);
    }
}
