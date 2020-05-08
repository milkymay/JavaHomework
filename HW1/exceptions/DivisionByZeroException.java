package expression.exceptions;

public class DivisionByZeroException extends MathExpressionException {
    public DivisionByZeroException(int value) {
        super("Tried to divide " + value + " by 0");
    }
}
