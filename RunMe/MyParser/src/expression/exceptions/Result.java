package expression.exceptions;

import expression.CommonExpression;

public class Result {
    public CommonExpression parsed;
    public String suffix;

    public Result(CommonExpression parsed, String suffix) {
        this.parsed = parsed;
        this.suffix = suffix;
    }
}