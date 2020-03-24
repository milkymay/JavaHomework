package expression;

public class Multiply extends BinaryOperation {
    public Multiply(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override 
    public int count(int a, int b) {
        return a * b;        
    }

    @Override 
    public double count(double a, double b) {
        return a * b;        
    }

    @Override
    public String sign() {
        return " * ";
    }

    @Override
    public boolean isCommutative() {
        return true;
    }

    @Override
    public int priority() {
        return 1;
    }
}