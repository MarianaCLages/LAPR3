package lapr.project.shared;

import lapr.project.model.Facility;

import java.util.Arrays;

public class MedianElement {
    Facility[] arr;

    public MedianElement(Facility[] arr) {
        this.arr = arr;
    }

    private Facility findMedian(Facility[] arr, int i, int n) {
        Arrays.sort(arr, i, n);
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

            return medOfMed;
        }
        return null;
    }
}
