package expression.exceptions;

import expression.CommonExpression;

public class CheckedPow2 extends UnaryOperation {
    public CheckedPow2(CommonExpression expr) {
        super(expr, "pow2");
    }

    @Override
    public int count(int x) {
        if (x < 0) {
            throw new MathExpressionException("Tried to count pow2(" + x + ")");
        } else if (x >= 31) {
            throw new OverflowException("pow2(" + x + ")");
        }
        return (int) (Math.pow(2, x));
    }
}
