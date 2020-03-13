package queue;

import java.util.function.Function;
import java.util.function.Predicate;

public interface Queue {
    void enqueue(Object element);
    Object element();
    Object dequeue();
    Queue filter(Predicate<Object> p);
    Queue map(Function<Object, Object> f);
    int size();
    boolean isEmpty();
    void clear();
}
