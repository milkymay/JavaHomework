package expression.exceptions;

import expression.CommonExpression;

public class CheckedMultiply extends BinaryOperation {
    public CheckedMultiply(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override 
    public int count(int a, int b) {
        String expr = a + " * " + b;
        if (a == Integer.MIN_VALUE && b < 0 ||
            b == Integer.MIN_VALUE && a < 0 ||
            a > 0 && b > 0 && a > Integer.MAX_VALUE / b ||
            a < 0 && b < 0 && -a > Integer.MAX_VALUE / -b) {
            throw new OverflowException(expr);
        }
        if (a > 0 && b < 0 && b < Integer.MIN_VALUE / a ||
            a < 0 && b > 0 && a < Integer.MIN_VALUE / b) {
            throw new UnderflowException(expr);
        }
        return a * b;
    }

    @Override
    public String sign() {
        return " * ";
    }

    @Override
    public boolean isAssociative() {
        return true;
    }

    @Override
    public int priority() {
        return 1;
    }
}