package expression.exceptions;

import expression.CommonExpression;
import expression.Expression;

import java.util.Objects;

public abstract class BinaryOperation implements CommonExpression {
    private CommonExpression first;
    private CommonExpression second;

    public BinaryOperation(CommonExpression first, CommonExpression second) {
        this.first = first;
        this.second = second;
    }

    public int evaluate(int x) {
        return count(first.evaluate(x), second.evaluate(x));
    }

    public int evaluate(int x, int y, int z) {
        return count(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }

    protected abstract int count(int a, int b);
    protected abstract String sign();
    protected abstract int priority();
    protected abstract boolean isAssociative();

    private String cover(Expression cur) {
        return  "(" + cur.toMiniString() + ")";
    }

    private boolean priorityBrackets(Expression cur) {
        return ((cur instanceof BinaryOperation)) && 
               ((BinaryOperation) cur).priority() > this.priority();
    }

    private boolean associativeBrackets(Expression cur) {
        return (cur instanceof BinaryOperation) && 
                ((BinaryOperation) cur).priority() == this.priority() &&
                (!((BinaryOperation) cur).isAssociative() || !this.isAssociative());
    }

    @Override
    public String toMiniString() {
        return (priorityBrackets(first) ? cover(first) : first.toMiniString()) + sign() +
               (priorityBrackets(second) || associativeBrackets(second) ? cover(second) : second.toMiniString());
    }

    @Override
    public String toString() {
        return "(" + first + sign() + second + ")";
    }

    @Override
    public boolean equals(Object a) {
        if (this == a) {
            return true;
        } 
        if (a == null || getClass() != a.getClass()) {
            return false; 
        } else {
            BinaryOperation bin = (BinaryOperation) a;
            return Objects.equals(first, bin.first) && Objects.equals(second, bin.second);
        }
    }

    @Override
	public int hashCode() {
		return (first.hashCode() * 31 + second.hashCode()) * 31 + getClass().hashCode();
    }
    
} 