package expression;

public class Main {
    public static void main(String args[]) {
        Variable x = new Variable("x");
        CommonExpression a = new Multiply(x, new Add(x, x)); 
            // new Add(
            //     new Subtract(
            //         new Multiply(new Variable("x"), new Variable("x")), 
            //         new Multiply(new Const(2), new Variable("x"))
            //     ), new Const(1)                           
            // );
        System.out.println(a.toMiniString());
    }
}