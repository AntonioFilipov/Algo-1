import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class KLists {

    private static StringBuilder output = new StringBuilder();

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<Integer>();

        int n = s.nextInt();

        ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < n; i++) {

            int currentNumber = s.nextInt();
            ArrayList<Integer> currentList = new ArrayList<Integer>();

            while (currentNumber != -1) {
                currentList.add(currentNumber);
                currentNumber = s.nextInt();
            }

            lists.add(currentList);
        }

        mergeArrays(lists);

        System.out.println(output);
    }

    public static void mergeArrays(ArrayList<ArrayList<Integer>> arrays) {

        PriorityQueue<LinkedList<Integer>> heap = new PriorityQueue<LinkedList<Integer>>(1000,
                new Comparator<LinkedList<Integer>>() {

                    public int compare(LinkedList<Integer> list1, LinkedList<Integer> list2) {
                        return Integer.compare(list1.peek(), list2.peek());
                    }
                });

        for (ArrayList<Integer> a : arrays) {
            heap.add(new LinkedList<Integer>(a));
        }

        while (heap.size() > 0) {
            LinkedList<Integer> current = heap.poll();
            Integer head = current.pop();
            output.append(head + " ");
            if (current.peekFirst() != null) {
                heap.add(current);
            }
        }
    }
}