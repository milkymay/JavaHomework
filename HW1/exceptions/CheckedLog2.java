package expression.exceptions;

import expression.CommonExpression;

public class CheckedLog2 extends UnaryOperation {
    public CheckedLog2(CommonExpression expr) {
        super(expr, "log2");
    }

    @Override
    public int count(int x) {
        if (x <= 0) {
            throw new MathExpressionException("tried to count LOGx but x < 0");
        }
        return (int) (Math.log(x)/Math.log(2));
    }

}
