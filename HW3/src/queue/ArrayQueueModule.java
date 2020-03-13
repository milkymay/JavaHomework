package queue;

public class ArrayQueueModule {
    private static int start;
    private static int end;
    private static Object[] elements = new Object[4];

    //I: queue != null

    //pre: element != null;
    public static void push(Object element) {
        assert element != null;
        start++;
        if (start == elements.length) { start = 0;}
        elements[start] = element;
        if (size() == 0) {
            ensureCapacity();
        }
    }
    //post: size' = size + 1 || size * 2 if size was elements.length - 1; element is added as the first

    //pre: size(queue) != 0
    public static Object peek() {
        assert size() != 0;
        return elements[(end + 1 + elements.length) % elements.length];
    }
    //post: R = the last element of queue

    //pre: start != end
    public static Object remove() {
        assert start != end;
        Object value = peek();
        elements[(end + 1 + elements.length) % elements.length] = null;
        end = (end + 1 + elements.length) % elements.length;
        return value;
    }
    //post: size' = size - 1; R = the last value ot the queue; the last value is removed from queue (null)

    //pre: element != null
    public static void enqueue(Object element) {
        assert element != null;
        elements[end--] = element;
        end = (end + elements.length) % elements.length;
        if (size() == 0) {
            ensureCapacity();
        }
    }
    //post: for i = 0..size-1 elements[i] = elements[i]' && elements[size] = element && size = min(size'+1, possible_size)
    //start and ens are valid (end--)

    //pre: size > 0
    public static Object dequeue() {
        assert start != end;
        Object value = element();
        elements[start--] = null;
        start = (start + elements.length) % elements.length;
        return value;
    }
    //post: for i = 0..size-2 elements[i] = elements[i]' && elements[size-1] = null && size = size' - 1;

    //pre: size > 0;
    public static Object element() {
        assert size() != 0;
        return elements[start];
    }
    //post: for i = 0..size-1 elements[i] = elements[i]', start = start'

    //pre: true
    public static int size() {
        return (start - end + elements.length) % elements.length;
    }
    //post: R = elements.size

    //pre: true
    public static boolean isEmpty() { return size() == 0; }
    //post: R = true if size = 0 or false otherwise

    //pre: true
    public static void clear() {
        int len = elements.length;
        elements = new Object[len];
        start = len - 1;
        end = len - 1;
    }
    //post: elements is an empty array of nulls with start = end = elements.length - 1 && size = 0

    private static void ensureCapacity() {
        Object[] queue = new Object[2 * elements.length];
        int sz = elements.length;
        System.arraycopy(elements, 0, queue,sz * 2 - 1 - start, start + 1);
        System.arraycopy(elements, start + 1, queue, sz, sz - start - 1);
        start = queue.length - 1;
        end = start - sz;
        elements = queue;
    }

    //pre: true
    public static String toStr() {
        int k = size(), i;
        StringBuilder s = new StringBuilder("[");
        for (i = start; k > 1; i = (i - 1 + elements.length) % elements.length) {
            s.append(elements[i]).append(", ");
            k--;
        }
        if (elements[i] != null) { s.append(elements[i]); }
        s.append("]");
        return s.toString();
    }
    //post: the queue is presented as string according to format "[el0, el1, ...]"

}
