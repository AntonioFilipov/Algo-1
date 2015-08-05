package week3;

import java.util.Arrays;
import java.util.Scanner;

public class HeapSort {

    private static void heapify(int[] arr, int size) {

        for (int i = size / 2 - 1; i >= 0; i--) {
            siftDown(i, arr, size);
        }
    }

    private static void swap(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void siftDown(int parentIndex, int[] arr, int size) {

        for (int i = parentIndex; i <= size / 2 - 1; i++) {
            int currentParent = arr[i];

            int leftChildIndex = 2 * i + 1;
            leftChildIndex = leftChildIndex >= size ? -1 : leftChildIndex;

            int rightChildIndex = 2 * i + 2;
            rightChildIndex = rightChildIndex >= size ? -1 : rightChildIndex;

            int maxChildIndex = i;

            if (leftChildIndex != -1 && rightChildIndex != -1) {
                maxChildIndex = arr[leftChildIndex] < arr[rightChildIndex] ? rightChildIndex : leftChildIndex;
            } else if (leftChildIndex != -1) {
                maxChildIndex = leftChildIndex;
            } else if (rightChildIndex != -1) {
                maxChildIndex = rightChildIndex;
            }

            if (arr[maxChildIndex] > currentParent) {
                swap(i, maxChildIndex, arr);
                siftDown(maxChildIndex, arr, size);
            } else {
                break;
            }
        }
    }
    
    // Sorts a sequence of integers.
    public void sort(int[] sequence) {

        // Build max heap from the array
        heapify(sequence, sequence.length);

        int size = sequence.length;

        for (int i = 0; i < sequence.length; i++) {

            swap(0, size - 1, sequence);

            siftDown(0, sequence, size - 1);

            size--;
        }
    }

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        String countAsStr = scanner.next();
        int count = Integer.parseInt(countAsStr);
        //System.out.println(scanner.nextLine());
        scanner.nextLine();
        String[] numbersAsStr = scanner.nextLine().split("\\s+");
        int[] intNumbers = new int[count];
        for (int i = 0; i < count; i++) {
            intNumbers[i] = Integer.parseInt(numbersAsStr[i]);
        }      
        
        HeapSort h = new HeapSort();
        h.sort(intNumbers);
        
        //System.out.println(Arrays.toString(intNumbers));
            
        for (int i = 0; i < intNumbers.length; i++) {
            if (i == intNumbers.length-1) {
                System.out.print(intNumbers[i]);
            }else{
                System.out.print(intNumbers[i]+ " ");
            }
        }
        //System.out.println(Arrays.toString(intNumbers).replace(", ", " ").replace("[", "").replace("]", ""));

        
        scanner.close();
        
        
    }

   
}
