package expression;

import java.util.Objects;

public class Const implements CommonExpression {
    private Number value;
    
    public Const (Number value) {
        this.value = value;
    } 

    @Override
    public int evaluate(int x) {
        return value.intValue();
    }

    @Override
    public double evaluate(double x) {
        return value.doubleValue();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return value.intValue();
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object a) {
        if (this == a) {
            return true;
        } 
        if (a == null || getClass() != a.getClass()) {
            return false; 
        } else {
            Const val = (Const) a;
            return value.equals(val.value);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(value) + getClass().hashCode();
    }
}