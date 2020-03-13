package expression.exceptions;

import expression.CommonExpression;
import expression.exceptions.BinaryOperation;

public abstract class UnaryOperation implements CommonExpression {
    protected CommonExpression expression;
    protected String sign;

    public UnaryOperation(CommonExpression expr, String sign) {
        this.expression = expr;
        this.sign = sign;
    }

    public int evaluate(int x, int y, int z) {
        return count(expression.evaluate(x, y, z));
    }

    public int evaluate(int x) {
        return count(expression.evaluate(x));
    }

    protected abstract int count(int x);

    public int priority() {
        return 3;
    }

    public void toMiniString(StringBuilder s) {
        boolean isBinary = (expression instanceof BinaryOperation);
        s.append(sign).append(isBinary).append(expression.toMiniString()).append(isBinary);
    }

    public void toString(StringBuilder s) {
        s.append(sign).append("(").append(expression.toString()).append(")");
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        toString(res);
        return res.toString();
    }

    public String sign() {
        return sign;
    }

    @Override
    public String toMiniString() {
        StringBuilder stringBuilder = new StringBuilder();
        toMiniString(stringBuilder);
        return stringBuilder.toString();
    }
}