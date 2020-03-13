package expression.exceptions;

import expression.CommonExpression;

public class CheckedSubtract extends BinaryOperation {
    public CheckedSubtract(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    public int count(int a, int b) {
        String expr = a + " - " + b;
        if (b < 0 && Integer.MAX_VALUE + b < a) {
            throw new OverflowException(expr);
        }
        if (b > 0 && Integer.MIN_VALUE + b > a) {
            throw new UnderflowException(expr);
        }
        return a - b;
    }

    @Override
    public String sign() {
        return " - ";
    }

    @Override
    public boolean isAssociative() {
        return false;
    }

    @Override
    public int priority() {
        return 2;
    }
}