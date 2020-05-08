package expression.exceptions;

import expression.CommonExpression;

public class CheckedAdd extends BinaryOperation {
    public CheckedAdd(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override 
    public int count(int a, int b) {
        String expr = a + " + " + b;
        if (a > 0 && Integer.MAX_VALUE - a < b) {
            throw new OverflowException(expr);
        }
        if (a < 0 && Integer.MIN_VALUE - a > b) {
            throw new UnderflowException(expr);
        }
        return a + b;        
    }

    @Override
    public String sign() {
        return " + ";
    }

    public boolean isAssociative() {
        return true;
    }

    @Override
    public int priority() {
        return 2;
    }
}