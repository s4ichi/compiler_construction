package chap6;
import javassist.gluonj.*;
import static chap6.BasicEvaluator.*;
import stone.ast.*;

import java.util.List;

import chap6.BasicEvaluator.ASTreeEx;

@Require(BasicEvaluator.class)
@Reviser public class MyBasicEvaluator {
    @Reviser public static class BlockEx extends BlockStmnt {
        public BlockEx(List c) { super(c); }
        public Object eval(Environment env) {
            Object result = "nothing";
            for (ASTree t: this) {
                result = ((ASTreeEx)t).eval(env);
            }
            return result;
        }
    }
    @Reviser public static class IfEx extends IfStmnt {
        public IfEx(List<ASTree> c) { super(c); }
        public Object eval(Environment env) {
            Object c = ((ASTreeEx)condition()).eval(env);
            if (!(c instanceof Integer) || (c instanceof Integer && ((Integer)c).intValue() != FALSE))
                return ((ASTreeEx)thenBlock()).eval(env);
            else {
                ASTree b = elseBlock();
                if (b == null)
                    return 0;
                else
                    return ((ASTreeEx)b).eval(env);
            }
        }
    }
}
