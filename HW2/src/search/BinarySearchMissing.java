package search;

public class BinarySearchMissing {
    // pre: ind = BinSearch.iterative(a, x) & BinSearch.recursive(a, x, -1, a.length)
    private static int test(int x, int a[], int ind) {
        return (0 <= ind && ind < a.length && a[ind] == x) ? ind : - ind - 1;
    }
    // post: R >= 0 <=> a[] consists x; otherwise R = - insertion - 1 as in Arrays.binarySearch format
    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        int[] array = new int[args.length - 1];
        for (int i = 0; i < args.length - 1; i++) {
            array[i] = Integer.parseInt(args[i + 1]);
        }
        // x - искомое число, array - исходный массив
        System.out.println(test(x, array, BinSearch.iterative(array, x) & BinSearch.recursive(array, x, -1, array.length)));
    }
    // post: выведен индекс первого вхождения х, если он есть в массиве;
    // в противном случае (- ( точка вставки ) - 1) . Точка вставки определяется как точка,
    // в которой x будет вставлен в a: индекс первого элемента, меньшего ключа, или a.length,
    // если все элементы в массиве меньше х.


}
