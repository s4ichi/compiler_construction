package chap8;
import java.lang.reflect.Method;
import javax.swing.JOptionPane;
import stone.StoneException;
import chap6.Environment;

public class MyNatives extends Natives {
    public void appendNatives(Environment env) {
        append(env, "nativeFib", MyNatives.class, "nativeFib", int.class);
        append(env, "charAt", MyNatives.class, "charAt", String.class, int.class);
        super.appendNatives(env);
    }

    public static int nativeFib(int n){
        if(n < 2){
            return n;
        } else {
            return nativeFib(n - 1) + nativeFib(n - 2);
        }
    }

    public static char charAt(String str, int n) {
        return str.charAt(n);
    }
}
