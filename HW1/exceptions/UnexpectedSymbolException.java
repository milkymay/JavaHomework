package expression.exceptions;

public class UnexpectedSymbolException extends ParsingException {
    public UnexpectedSymbolException(String parsed, char c, char ch) {
        super("Parsed " + parsed + " and expected '" + c + "', found '" + ch + "'");
    }
}
