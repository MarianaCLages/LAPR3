package lapr.project.shared;

import lapr.project.model.Facility;

import java.util.Arrays;
import java.util.Comparator;

public class MedianElement {
    private Facility[] arr;
    private Comparator<Facility> compare;

    public MedianElement(Facility[] arr, Comparator<Facility> compare) {
        this.arr = arr;
        this.compare = compare;
    }

    Facility[] swap(int i, int j) {
        Facility temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return arr;
    }

    // It searches for x in arr[l..r], and
// partitions the array around x.
    int partition(int l,
                  int r, Facility x) {
        // Search for x in arr[l..r] and move it to end

        int i;
    /*    for (i = l; i < r; i++)
            if (compare.compare(arr[i], x) == 0)
                break;
        swap(i, r);
*/

        // Standard partition algorithm
        i = l;
        for (int j = l; j <= r - 1; j++) {
            if (compare.compare(arr[j], (x)) < 0) {
                swap(i, j);
                i++;
            }
        }
        swap(i, r);
        return i;
    }

    private Facility findMedian(Facility[] arr, int i, int n) {
        Arrays.sort(arr, i, n, compare);
        return arr[i + (n - i) / 2];
    }

    public Facility median() {
        return kthSmallest(this.arr, this.arr.length - 1);
    }

    private Facility kthSmallest(Facility[] arr, int end) {

        if (arr.length / 2 > 0 && arr.length / 2 <= end + 1) {
            int n = end + 1;

            int i;

            Facility[] median = new Facility[(n + 4) / 5];
            for (i = 0; i < n / 5; i++)
                median[i] = findMedian(arr, i * 5, i * 5 + 5);

            if (i * 5 < n) {
                median[i] = findMedian(arr, i * 5, i * 5 + n % 5);
                i++;
            }

            Facility medOfMed;
            if (i == 1) {
                medOfMed = median[i - 1];
            } else {
                medOfMed = kthSmallest(median, i - 1);
            }

            partition(0, end, medOfMed);

            return medOfMed;
        }
        return null;
    }
}
