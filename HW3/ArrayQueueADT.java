package queue;

import java.util.Arrays;

public class ArrayQueueADT {
    private int start;
    private int end;
    private Object[] elements = new Object[4];

    //I: queue != null

    //typedef: queue.elements = elements; queue.start = start; queue.end = end;

    //pre: element != null;
    public static void push(ArrayQueueADT queue, Object element) {
        assert element != null;
        queue.start++;
        if (queue.start == queue.elements.length) { queue.start = 0;}
        queue.elements[queue.start] = element;
        if (size(queue) == 0) {
            ensureCapacity(queue);
        }
    }
    //post: size' = size + 1 || size * 2 if size was elements.length - 1; element is added as the first

    //pre: size(queue) != 0
    public static Object peek(ArrayQueueADT queue) {
        assert size(queue) != 0;
        return queue.elements[(queue.end + 1 + queue.elements.length) % queue.elements.length];
    }
    //post: R = the last element of queue

    //pre: start != end
    public static Object remove(ArrayQueueADT queue) {
        assert queue.start != queue.end;
        Object value = peek(queue);
        queue.elements[(queue.end + 1 + queue.elements.length) % queue.elements.length] = null;
        queue.end = (queue.end + 1 + queue.elements.length) % queue.elements.length;
        return value;
    }
    //post: size' = size - 1; R = the last value ot the queue; the last value is removed from queue (null)

    //pre: element != null
    public static void enqueue(ArrayQueueADT queue, Object element) {
        assert element != null;
        queue.elements[queue.end--] = element;
        queue.end = (queue.end + queue.elements.length) % queue.elements.length;
        if (size(queue) == 0) {
            ensureCapacity(queue);
        }
    }
    //post: for i = 0..size-1 elements[i] = elements[i]' && elements[size] = element && size = min(size'+1, possible_size)
    //start and ens are valid (end--)

    //pre: size > 0
    public static Object dequeue(ArrayQueueADT queue) {
        assert queue.start != queue.end;
        Object value = element(queue);
        queue.elements[queue.start--] = null;
        queue.start = (queue.start + queue.elements.length) % queue.elements.length;
        return value;
    }
    //post: for i = 0..size-2 elements[i] = elements[i]' && elements[size-1] = null && size = size' - 1;

    //pre: size > 0;
    public static Object element(ArrayQueueADT queue) {
        assert queue.start != queue.end;
        return queue.elements[queue.start];
    }
    //post: for i = 0..size-1 elements[i] = elements[i]', start = start'

    //pre: true
    public static int size(ArrayQueueADT queue) {
        return (queue.start - queue.end + queue.elements.length) % queue.elements.length;
    }
    //post: R = elements.size

    //pre: true
    public static boolean isEmpty(ArrayQueueADT queue) { return queue.start == queue.end; }
    //post: R = true if size = 0 or false otherwise

    // pre: true
    public static void clear(ArrayQueueADT queue) {
        int len = queue.elements.length;
        queue.elements = new Object[len];
        queue.start = len - 1;
        queue.end = len - 1;
    }
    //post: elements is an empty array of nulls with start = end = elements.length - 1 && size = 0

    //pre: true
    public static String toStr(ArrayQueueADT queue) {
        int k = size(queue), i;
        StringBuilder s = new StringBuilder("[");
        for (i = queue.start; k > 1; i = (i - 1 + queue.elements.length) % queue.elements.length) {
            s.append(queue.elements[i]).append(", ");
            k--;
        }
        if (queue.elements[i] != null) { s.append(queue.elements[i]); }
        s.append("]");
        return s.toString();
    }
    //post: the queue is presented as string according to format "[el0, el1, ...]"

    private static void ensureCapacity(ArrayQueueADT q) {
        Object[] queue = new Object[2 * q.elements.length];
        int sz = q.elements.length;
        System.arraycopy(q.elements, 0, queue,sz * 2 - 1 - q.start, q.start + 1);
        System.arraycopy(q.elements, q.start + 1, queue, sz, sz - q.start - 1);
        q.start = queue.length - 1;
        q.end = q.start - sz;
        q.elements = queue;
    }
}
