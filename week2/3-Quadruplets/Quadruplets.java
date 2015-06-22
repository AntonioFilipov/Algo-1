import java.util.Arrays;
import java.util.List;

import com.sun.corba.se.spi.extension.ZeroPortPolicy;

public class Quadruplets {
    
    public static boolean binarySearch(int[] array, int key) {
        int start = 0;
        int end = array.length - 1;

        while (end >= start) {
            int middle = (start + end) / 2;
            if (array[middle] == key) {
                return true;
            }
            if (array[middle] < key) {
                start = middle + 1;
            }
            if (array[middle] > key) {
                end = middle - 1;
            }
        }
        return false;
    }
    
    // Returns the number of quadruplets that sum to zero.
    public static int zeroQuadrupletsCount(int[] a, int[] b, int[] c, int[] d) {
        int counter=0;
        int n = a.length;
        int sum=0;
        Arrays.sort(d);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int p = 0; p < n; p++) {
                    sum = a[i]+b[j]+c[p];
                    
                    if (binarySearch(d,-sum)) {
                        counter++;
                    }
                }
                
            }
        }
               
        return counter;
    }

    public static void main(String[] args) {
        int result = zeroQuadrupletsCount(new int[]{5,3,4}, new int[]{-2,-1,6}, new int[]{-1,-2,4}, new int[]{-1,-2,7});
        System.out.println(result);
    }

}
