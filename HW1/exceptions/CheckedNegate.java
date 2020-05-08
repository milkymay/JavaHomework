package expression.exceptions;

import expression.CommonExpression;

public class CheckedNegate extends UnaryOperation {
    public CheckedNegate(CommonExpression expr) {
        super(expr, "-");
    }

    @Override
    protected int count(int x) {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException("-" + x);
        }
        return -x;
    }
}