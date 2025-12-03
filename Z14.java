import java.util.Arrays;
import java.util.Comparator;

public class Z14 {
    public static <T extends Comparable<T>> T findMax(T[] array) {
        if (array == null || array.length == 0) return null;
        return Arrays.stream(array).max(Comparator.naturalOrder()).get();
    }

    public static void main(String[] args) {
        Integer[] intArr = {3, 1, 4, 1, 5};
        String[] strArr = {"apple", "banana", "cherry"};
        System.out.println(findMax(intArr));
        System.out.println(findMax(strArr));
    }
}