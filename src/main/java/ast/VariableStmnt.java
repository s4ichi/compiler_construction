package stone.ast;
import java.util.List;

public class VariableStmnt extends ASTList {
    public VariableStmnt(List c) { super(c); }
    public String name() { return ((ASTLeaf)child(0)).token().getText(); }
}
