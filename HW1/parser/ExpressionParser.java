package expression.parser;

import expression.CommonExpression;
import expression.Const;
import expression.TripleExpression;
import expression.Variable;
import expression.exceptions.*;
import expression.parser.BaseParser;
import expression.parser.ExpressionSource;
import expression.parser.StringSource;

public class ExpressionParser implements Parser {
    public TripleExpression parse(final String source) throws ParsingException {
        return parse(new StringSource(source));
    }

    public CommonExpression parse(ExpressionSource source) throws ParsingException {
        return new Expression(source).parseExpression();
    }

    private static class Expression extends BaseParser {
        public Expression(final ExpressionSource source) {
            super(source);
            nextChar();
        }

        public CommonExpression parseExpression() throws ParsingException {
            final CommonExpression result = parseTerm();
            if (test('\0')) {
                return result;
            }
            throw new InvalidInputException("Parsed " + getSource() + " and expected the end of Expression");
        }

        private CommonExpression parseTerm() throws ParsingException {
            skipWhitespace();
            CommonExpression result = parseFactor();
            skipWhitespace();
            while(ch == '+' || ch == '-') {
                if (test('+')) {
                    result = new CheckedAdd(result, parseFactor());
                } else if (test('-')) {
                    result = new CheckedSubtract(result, parseFactor());
                }
                skipWhitespace();
            }
            return result;
        }

        private CommonExpression parseFactor() throws ParsingException {
            skipWhitespace();
            CommonExpression result = parseValue();
            skipWhitespace();
            while(ch == '*' || ch == '/') {
                if (test('*')) {
                    skipWhitespace();
                    result = new CheckedMultiply(result, parseValue());
                } else if (test('/')) {
                    skipWhitespace();
                    result = new CheckedDivide(result, parseValue());
                }
                skipWhitespace();
            }
            return result;
        }

        private CommonExpression parseValue() throws ParsingException {
            if (test('(')) {
                CommonExpression cur = parseTerm();
                expect(')');
                return cur;
            } else if (test('-')) {
                return parseUnary();
            } else if (ch == 'x' || ch =='y' || ch == 'z') {
                char cur = ch;
                nextChar();
                return new Variable(String.valueOf(cur));
            } else if (test('l')) {
                expect("og2");
                if (test('-')) {
                    return new CheckedLog2(new CheckedNegate(parseValue()));
                } else if (test(' ')) {
                    return new CheckedLog2(parseValue());
                } else if (ch == '(') {
                    return new CheckedLog2(parseTerm());
                }
            } else if (test('p')) {
                expect("ow2");
                skipWhitespace();
                return new CheckedPow2(parseValue());
            } else if (Character.isDigit(ch)) {
                return parseNumber();
            }
            throw new InvalidSymbolsException(getSource(), String.valueOf(ch));
        }

        private CommonExpression parseUnary() throws ParsingException {
            skipWhitespace();
            final StringBuilder sb = new StringBuilder("-");
            copyDigits(sb);
            if (sb.length() == 1) {
                return new CheckedNegate(parseValue());
            } else {
                try {
                    return new Const(Integer.parseInt(sb.toString()));
                } catch (NumberFormatException e) {
                    throw new OverflowException(sb.toString());
                }
            }
        }

        private CommonExpression parseNumber() throws ParsingException {
            final StringBuilder sb = new StringBuilder();
            copyDigits(sb);
            try {
                return new Const(Integer.parseInt(sb.toString()));
            } catch (NumberFormatException e) {
                throw new OverflowException(sb.toString());
            }
        }

        private void copyDigits(final StringBuilder sb) {
            while (between('0', '9')) {
                sb.append(ch);                nextChar();
            }
        }

        private void skipWhitespace() {
            while (test(' ') || test('\r') || test('\n') || test('\t')) {}
        }
    }
}
