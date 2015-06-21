
public class InsertionSort {

    public static void sort(int[] array){
        int j;
        int currentElement;
        int position;
        for (int i = 1; i < array.length; i++) {
            j=i-1;
            position = i;
            currentElement = array[i];

            while (j >= 0 ) {
                if (currentElement < array[j]) {
                    array[j+1] = array[j];
                    position = j;
                }
                else{
                    break;
                }
                j--;
            }
            array[position] = currentElement;

        }
    }
    public static void main(String[] args) {
        int[] array = new int[]{4, 13, 52, 7, 18, 3, 1, 6};
        sort(array);
        for (int i : array) {
            System.out.println(i);
        }

    }

}
