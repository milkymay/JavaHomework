package expression.exceptions;

public class InvalidSymbolsException extends ParsingException {
    public InvalidSymbolsException(String prefix, String suffix) {
        super("Managed to parse \"" + prefix + "\" but faced unknown \"" + suffix + "...\"");
    }
}
