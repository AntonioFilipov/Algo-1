
public class SelectionSort {

    public static void sort(int[] array){
        int min;
        int currentMin = 0;
        int minPosition=0;
        for (int i = 0; i < array.length-1; i++) {
            min = array[i];
            minPosition=i;
            for (int j = i+1; j < array.length; j++) {
                if (array[j] < min) {
                    min = array[j];
                    minPosition = j;
                }

            }
            int temp=array[i];
            array[i]=min;
            array[minPosition]=temp;
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
