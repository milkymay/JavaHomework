package expression;

public final class Variable implements CommonExpression {
    private String var;
    
    public Variable (String var) {
        this.var = var;
    } 

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public double evaluate(double x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return 
            var.equals("x") ? x : 
            var.equals("y") ? y : 
            z;
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