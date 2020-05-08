package expression;

public final class Variable implements CommonExpression {
    private String var;
    
    public Variable (String var) {
        this.var = var;
    } 

    @Override
    public int evaluate(int x) {
        return var.charAt(0) == '-' ? -x : x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int sign = var.charAt(0) == '-' ? -1 : 1;
        return 
            var.equals("x") ? x * sign :
            var.equals("y") ? y * sign :
            z * sign;
    }

    @Override
    public String toString() {
        return var;
    }

    @Override
    public boolean equals(Object a) {
        if (a instanceof Variable) {
            Variable name = (Variable) a;
            return var.equals(name.var);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return var.hashCode() + getClass().hashCode();
    }
}