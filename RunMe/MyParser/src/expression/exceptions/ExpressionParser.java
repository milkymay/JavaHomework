package expression.exceptions;

import expression.*;

import java.util.Set;

public class ExpressionParser implements Parser {
    private String expression;
    private Set<String> variables = Set.of(
            "x", "y", "z"
    );

    @Override
    public CommonExpression parse(String s) throws InvalidSymbolsException {
        this.expression = s;
        Result result = parseTerm(skipWhitespaces(s));
        if (result.suffix.isEmpty()) {
            return result.parsed;
        }
        throw new InvalidSymbolsException(
                expression.substring(0, s.length() - result.suffix.length()),
                expression.substring(s.length() - result.suffix.length() + 1, Math.max(result.suffix.length(), 4))
        );
    }

    private String skipWhitespaces(String s) {
        int i = 0;
        while (i < s.length() && Character.isWhitespace(s.charAt(i))) {
           i++;
        }
        return s.substring(i);
    }

    private Result parseTerm(String s) {
        Result cur = parseFactor(s);
        cur.suffix = skipWhitespaces(cur.suffix);
        CommonExpression curParsed = cur.parsed;
        while (cur.suffix.length() > 0) {
            if (!(cur.suffix.charAt(0) == '+' || cur.suffix.charAt(0) == '-')) { break; }
            char sign = cur.suffix.charAt(0);
            String next = skipWhitespaces(cur.suffix.substring(1));
            cur = parseFactor(next);
            if (sign == '+') {
                curParsed = new CheckedAdd(curParsed, cur.parsed);
            } else if (sign == '-') {
                curParsed = new CheckedSubtract(curParsed, cur.parsed);
            }
        }
        return new Result(curParsed, skipWhitespaces(cur.suffix));
    }

    private Result parseFactor(String s) {
        Result cur = parseBracket(s);
        cur.suffix = skipWhitespaces(cur.suffix);
        CommonExpression curParsed = cur.parsed;
        while (true) {
            if (cur.suffix.isEmpty() || !(cur.suffix.charAt(0) == '*' || cur.suffix.charAt(0) == '/')) {
                return cur;
            }
            String next = skipWhitespaces(cur.suffix.substring(1));
            Result right = parseBracket(next);
            char sign = cur.suffix.charAt(0);
            if (sign == '*') {
                curParsed = new CheckedMultiply(curParsed, right.parsed);
            } else if (sign == '/') {
                curParsed = new CheckedDivide(curParsed, right.parsed);
            }
            cur = new Result(curParsed, skipWhitespaces(right.suffix));
        }
    }

    private Result parseBracket(String s) {
        char firstBracket = s.charAt(0);
        if (firstBracket == '(') {
            Result result = parseTerm(skipWhitespaces(s.substring(1)));
            if (!result.suffix.isEmpty() && result.suffix.charAt(0) == ')') {
                result.suffix = skipWhitespaces(result.suffix.substring(1));
            } else {
                throw new MathExpressionException("No close bracket");
            }
            return result;
        }
        return parseFunction(s);
    }

    private Result parseFunction(String s) {
        StringBuilder func = new StringBuilder();
        int i = 0;
        if (s.charAt(0) == '-') {
            if (Character.isDigit(s.charAt(1))) {
                return parseNum(s);
            } else {
                Result result = parseBracket(skipWhitespaces(s.substring(1)));
                return processFunction("-", result);
            }
        }
        while (i < s.length() && Character.isLetter(s.charAt(i))) {
            func.append(s.charAt(i++));
        }
        if (!func.toString().isEmpty()) {
            s = skipWhitespaces(s.substring(func.length()));
            if (!s.isEmpty() && s.charAt(0) == '(') {
                Result result = parseBracket(s);
                return processFunction(func.toString(), result);
            } else if (variables.contains(func.toString())) {
                return new Result(new Variable(func.toString()), s);
            } else if (!s.isEmpty() && s.charAt(0) != '(') {
                Result result = parseFunction(s);
                return processFunction(func.toString(), result);
            } else {
                throw new MathExpressionException("Invalid letters in input");
            }
        }
        return parseNum(skipWhitespaces(s));
    }

    private Result parseNum(String s) {
        StringBuilder res = new StringBuilder();
        if (s.charAt(0) == '-') {
            s = s.substring(1);
            res.append("-");
        }
        int i = 0;
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            res.append(s.charAt(i++));
        }
        if (i == 0) {
            throw new MathExpressionException("Cannot resolve number " + s.substring(0, Math.max(s.length(), 4)));
        }
        int num = Integer.parseInt(res.toString());
        String suffix = s.substring(i);
        return new Result(new Const(num), skipWhitespaces(suffix));
    }

    private Result processFunction(String func, Result cur) {
        switch (func) {
            case "-":
                return new Result(new CheckedNegate(cur.parsed), skipWhitespaces(cur.suffix));
//            case "pow2":
//                return new Result(new CheckedPow2(cur.parsed), skipWhitespaces(cur.suffix));
//            case "log2":
//                return new Result(new CheckedLog2(cur.parsed), skipWhitespaces(cur.suffix));
            default:
                throw new MathExpressionException(func + " is not defined");
        }
    }
}