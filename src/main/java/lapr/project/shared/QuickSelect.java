package lapr.project.shared;

import java.util.Comparator;
import java.util.Random;


public class QuickSelect {
    private static final Random random = new Random();

    public static <T> T select(T[] list, int n, Comparator<? super T> cmp) {
        return select(list, 0, list.length - 1, n, cmp);
    }

    public static <T> T select(T[] list, int left, int right, int n, Comparator<? super T> cmp) {
        for (; ; ) {
            if (left == right)
                return list[left];
            int pivot = pivotIndex(left, right);
            pivot = partition(list, left, right, pivot, cmp);
            if (n == pivot)
                return list[n];
            else if (n < pivot)
                right = pivot - 1;
            else
                left = pivot + 1;
        }
    }

    private static <T> int partition(T[] list, int left, int right, int pivot, Comparator<? super T> cmp) {
        T pivotValue = list[pivot];
        swap(list, pivot, right);
        int store = left;
        for (int i = left; i < right; ++i) {
            if (cmp.compare(list[i], pivotValue) < 0) {
                swap(list, store, i);
                ++store;
            }
        }
        swap(list, right, store);
        return store;
    }

    private static <T> void swap(T[] list, int i, int j) {
        T value = list[i];
        list[i] = list[j];
        list[j] = value;
    }

    private static <T> int pivotIndex(int i, int n) {
        return i + random.nextInt(n - i + 1);
    }

}