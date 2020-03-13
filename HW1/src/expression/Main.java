package expression;

import expression.parser.ExpressionParser;

public class Main {
    public static void main(String[] args) {
        String expression = "x -";
        try {
            TripleExpression ans = new ExpressionParser().parse(expression);
            System.out.println(ans.evaluate(0, -903299657, 0));
            System.out.println(ans.toString());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}