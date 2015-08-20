import java.util.Arrays;
import java.util.Scanner;

public class Quadruplets {

    // Returns the number of quadruplets that sum to zero.
    public int zeroQuadrupletsCount(int[] a, int[] b, int[] c, int[] d) {

        int[] p1 = permutationSum(a, b);
        int[] p2 = permutationSum(c, d);

        Arrays.sort(p2);

        int zeroSumCount = 0;

        for (int i = 0; i < p1.length; i++) {
            int lowerBoundIndex = binarySearch(p2, p1[i] * -1);
            int upperBoundIndex = binarySearch(p2, p1[i] * -1 + 1);

            zeroSumCount += upperBoundIndex - lowerBoundIndex;
        }

        return zeroSumCount;
    }

    public static void main(String[] args) {
        Quadruplets q = new Quadruplets();

        Scanner s = new Scanner(System.in);

        Integer n = Integer.parseInt(s.next());

        int[] a = new int[n];
        int[] b = new int[n];
        int[] c = new int[n];
        int[] d = new int[n];

        for (int j = 0; j < n; j++) {
            a[j] = Integer.parseInt(s.next());
        }
        for (int j = 0; j < n; j++) {
            b[j] = Integer.parseInt(s.next());
        }

        for (int j = 0; j < n; j++) {
            c[j] = Integer.parseInt(s.next());
        }

        for (int j = 0; j < n; j++) {
            d[j] = Integer.parseInt(s.next());
        }

        System.out.println(q.zeroQuadrupletsCount(a, b, c, d));
    }

    private int[] permutationSum(int[] a, int[] b) {

        int[] permutations = new int[a.length * a.length];
        int index = 0;

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                permutations[index] = a[i] + b[j];
                index++;
            }
        }

        return permutations;
    }

    private int binarySearch(int[] arr, int number) {

        int searchedIndex = -1;

        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {

            int middle = low + (high - low) / 2;
            int currentMiddleNumber = arr[middle];

            if (currentMiddleNumber == number) {
                high = middle - 1;
                searchedIndex = middle;
            } else if (currentMiddleNumber > number) {
                high = middle - 1;
            } else if (currentMiddleNumber < number) {
                low = middle + 1;
            }
        }

        if (searchedIndex == -1) {
            return low;
        }

        return searchedIndex;
    }
}