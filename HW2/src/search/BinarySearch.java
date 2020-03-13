package search;

public class BinarySearch {
    // pre: аргументы - целое число (искомое) и целочисленный массив, отсортированный по неубыванию
    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        int[] array = new int[args.length - 1];
        for (int i = 0; i < args.length - 1; i++) {
            array[i] = Integer.parseInt(args[i + 1]);
        }
        System.out.println(BinSearch.iterative(array, x) & BinSearch.recursive(array, x, -1, array.length));
        // BinSearch.iterative(array, x) = BinSearch.recursive(array, x, -1, array.length), т.к. являются
        // разными реализациями одного и того же алгоритма
    }
    // post: входные данные не изменены, выведено R:
    // R - минимальное значение индекса i, при котором a[i] <= x (a.length, если все элементы меньше х).
}
