package expression.parser;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface ExpressionSource {
    boolean hasNext();
    char next();
    String getData();
    Exception error(final String message);
}
