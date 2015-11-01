package chap7;
import javassist.gluonj.*;
import stone.ast.ASTree;
import stone.ast.VariableStmnt;
import chap6.Environment;
import chap7.FuncEvaluator.EnvEx;
import java.util.List;


@Require(FuncEvaluator.class)
@Reviser public class VariableEvaluator {
    @Reviser public static class VariableStmntEx extends VariableStmnt {
        public VariableStmntEx(List c) { super(c); }
        public Object eval(Environment env) {
            Object initialValue = 0;
            ((EnvEx)env).putNew(name(), initialValue);
            return initialValue;
        }
    }
}
