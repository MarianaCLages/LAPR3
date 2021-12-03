package lapr.project.shared;

import java.lang.Comparable;

import java.util.Arrays;

public class MedianElement {
    Comparable[] arr;

    public MedianElement(Comparable[] arr) {
        this.arr = arr;
    }

    private Comparable findMedian(Comparable[] arr, int i, int n) {
        Arrays.sort(arr, i, n);
        return arr[i + (n - i) / 2];
    }

    public Comparable median() {
        return kthSmallest(this.arr, this.arr.length - 1);
    }


    private Comparable kthSmallest(Comparable[] arr, int end) {

        if (arr.length / 2 > 0 && arr.length / 2 <= end + 1) {
            int n = end + 1;

            int i;

            Comparable[] median = new Comparable[(n + 4) / 5];
            for (i = 0; i < n / 5; i++)
                median[i] = findMedian(arr, i * 5, i * 5 + 5);

            if (i * 5 < n) {
                median[i] = findMedian(arr, i * 5, i * 5 + n % 5);
                i++;
            }

            Comparable medOfMed;
            if (i == 1) {
                medOfMed = median[i - 1];
            } else {
                medOfMed = kthSmallest(median, i - 1);
            }

            return medOfMed;
        }
        return null;
    }
}
