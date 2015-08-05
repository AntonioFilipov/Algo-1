import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class BirthdayRanges {

    public static int binarySearch(List<Integer> array, int key) {
        int start = 0;
        int end = array.size() - 1;

        while (end >= start) {
            int middle = (start + end) / 2;
            if (array.get(middle) == key) {
                return middle;
            }
            if (array.get(middle) < key) {
                start = middle + 1;
            }
            if (array.get(middle) > key) {
                end = middle - 1;
            }
        }
        return -1;
    }

    public static class Pair {

        public int start;
        public int end;

        public Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    // Returns a vector with the number of people born in the specific ranges.
    public static List<Integer> birthdaysCount(List<Integer> birthdays, List<Pair> ranges) {
        Collections.sort(birthdays);
        List<Integer> result = new Vector();

        int counter;
        for (int i = 0; i < ranges.size(); i++) {
            counter = 0;
            result.add(0);
            for (int j = ranges.get(i).start; j <= ranges.get(i).end; j++) {
                int index = binarySearch(birthdays, j);
                if (index > 0) {
                    counter++;
                    int first = index;
                    int last = index;

                    while (first > 0 && birthdays.get(first - 1) == j) {
                        first--;
                        counter++;
                    }
                    while (last < birthdays.size() - 1 && birthdays.get(last + 1) == j) {
                        counter++;
                        last++;
                    }
                }
            }
            result.set(i, counter);

        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int peopleCount = scanner.nextInt(), rangesCount = scanner.nextInt();
        List<Integer> birthdays = new ArrayList<Integer>();;
        List<Pair> ranges = new ArrayList<Pair>();;

        for (int i = 0; i < peopleCount; ++i) {
            birthdays.add(scanner.nextInt());
        }

        for (int i = 0; i < rangesCount; ++i) {
            ranges.add(new Pair(scanner.nextInt(), scanner.nextInt()));
        }

        scanner.close();

        List<Integer> result = birthdaysCount(birthdays, ranges);

        for (Integer integer : result) {
            System.out.println(integer);
        }
    }
}