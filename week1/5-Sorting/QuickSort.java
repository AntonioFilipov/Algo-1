public class QuickSort {

    public static void sort(int array[]) {
        quickSort(array, 0, array.length - 1);
    }

    public static void quickSort(int array[], int start, int end) {
        int i = start;
        int k = end;

        if (end - start >= 1) {
            int pivot = array[start];

            while (k > i) {
                while (array[i] <= pivot && i <= end && k > i)
                    i++;
                while (array[k] > pivot && k >= start && k >= i)
                    k--;
                if (k > i)
                    swap(array, i, k);
            }
            swap(array, start, k);
            quickSort(array, start, k - 1);
            quickSort(array, k + 1, end);
        } else {
            return; 
        }
    }

    public static void swap(int array[], int index1, int index2)
    {
        int temp = array[index1];
        array[index1] = array[index2]; 
        array[index2] = temp;
    }

    public static void main(String[] args) {
        int[] array = new int[] { 4, 13, 52, 7, 18, 3, 1, 6 };
        sort(array);
        for (int i : array) {
            System.out.println(i);
        }
    }

}
