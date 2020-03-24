package expression;

import expression.exceptions.ExpressionParser;

public class Main {
    public static void main(String[] args) {
        String expression = " 1000000*x*x*x*x*x/(x-1)";
        try {
            int ans = new ExpressionParser().parse(expression).evaluate(5, -5, 3);
            System.out.println(ans);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}