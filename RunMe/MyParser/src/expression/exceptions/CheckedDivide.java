package expression.exceptions;

import expression.CommonExpression;

public class CheckedDivide extends BinaryOperation {
    public CheckedDivide(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override 
    public int count(int a, int b) {
        if (b == 0) {
            throw new DivisionByZeroException(a);
        }
        if (a == Integer.MIN_VALUE && b == -1) {
            throw new OverflowException(a + " / " + b);
        }
        return a / b;
    }

    @Override
    public String sign() {
        return " / ";
    }

    @Override
    public boolean isAssociative() {
        return false;
    }

    @Override
    public int priority() {
        return 1;
    }
}