package expression.exceptions;

import expression.CommonExpression;

public class CheckedNegate extends UnaryOperation {
    public CheckedNegate(CommonExpression expr) {
        super(expr, "-");
    }

    @Override
    public int evaluate(int x) {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException("-" + x);
        }
        return -expression.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int res = expression.evaluate(x, y, z);
        if (res == Integer.MIN_VALUE) {
            throw new OverflowException("-(" + res + ")");
        }
        return -res;
    }
}