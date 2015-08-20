import java.util.ArrayList;
import java.util.Scanner;

public class LongestSubsequence {

    private static RMQBinaryIndexedTree rmq;
    private static ArrayList<Integer> prev;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();
        rmq = new RMQBinaryIndexedTree(new ArrayList<Integer>(), n);
        prev = new ArrayList<Integer>();

        ArrayList<Integer> inputNumbers = new ArrayList<Integer>();

        for (int i = 0; i < n; i++) {
            int currentNumber = s.nextInt();
            inputNumbers.add(currentNumber);

            if (currentNumber != 0) {
                int maxFromStart = rmq.max(0, currentNumber - 1);

                prev.add(inputNumbers.lastIndexOf(indexOfInRange(maxFromStart, 0, currentNumber - 1)));

                rmq.set(currentNumber, maxFromStart + 1);
            } else {
                rmq.set(0, 1);
            }
        }

        int largestSequence = rmq.max(0, n - 1);
        int indexOfLargestSequence = indexOfInRange(largestSequence, 0, n - 1);

        System.out.println(largestSequence);
        System.out.println(getLongestSequence(inputNumbers.lastIndexOf(indexOfLargestSequence), inputNumbers));
    }

    private static int indexOfInRange(int searchedValue, int startIndex, int endIndex) {

        if (searchedValue == 0) {
            return -1;
        }

        startIndex = rmq.data.length / 2 + startIndex;
        endIndex = rmq.data.length / 2 + endIndex;

        for (int i = 0; i <= endIndex - startIndex; i++) {
            if (rmq.data[i + startIndex] == searchedValue) {
                return i;
            }
        }

        return -1;
    }

    private static String getLongestSequence(int startIndex, ArrayList<Integer> inputNumbers) {

        StringBuilder output = new StringBuilder();
        int nextIndex = prev.get(startIndex);

        output.append(inputNumbers.get(startIndex));
        while (nextIndex != -1) {
            int nextNumber = inputNumbers.get(nextIndex);

            output.insert(0, nextNumber + " ");
            nextIndex = prev.get(nextIndex);
        }

        return output.toString();
    }
}