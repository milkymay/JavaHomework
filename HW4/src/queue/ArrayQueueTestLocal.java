package queue;

public class ArrayQueueTestLocal {
    public static void fill(ArrayQueue queue) {
        for (int i = 0; i < 16; i++) {
            queue.enqueue(i);
        }
    }

    public static void dump(ArrayQueue queue) {
        for (int i = 0; i < 8; i++) {
            System.out.println(queue.size() + " " +
                queue.element() + " " + queue.dequeue());
        }
    }

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue();
        fill(queue);
        dump(queue);
    }
}
