package search;

public class BinarySearchSpan {
    // pre: аргументы - число (искомое) и массив строчек, отсортированный по невозрастанию
    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        int[] array = new int[args.length - 1];
        for (int i = 0; i < args.length - 1; i++) {
            array[i] = Integer.parseInt(args[i + 1]);
        }
        int begin = (BinSearch.iterative(array, x));
        /*int end = BinSearch.recursive(array, x - 1, -1, array.length); // чит для целочисленных аргументов массива
        System.out.println(begin + " " + Math.max(end - begin, 0));*/
        int end = BinSearch.recursive_upper(array, x, -1, array.length);
        System.out.println(begin + " " + (end - begin));
        // x - искомое число, array - исходный массив, begin - первое вхождение х или символа, меньшего его;
        // end - индекс первого вхождения символа, меньшего х
    }
    // post: входные данные не изменены, выведено R:
    // R - индекс x и длина диапазона элементов, равных х, если он содержится в массиве;
    // в противном случае - индекс первого меньшего (a.length, если все элементы меньше) и 0.
}
