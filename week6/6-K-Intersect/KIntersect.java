import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class KIntersect {

    private static HashMap<Integer, Integer> intersect = new HashMap<Integer, Integer>();
    private static StringBuilder result = new StringBuilder();

    private static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {

        int k = s.nextInt();

        int listNumbers = s.nextInt();

        // Initialize the intersect with the first list
        addToHashMap(intersect, listNumbers);

        // Process all other lists
        for (int i = 0; i < k - 1; i++) {
            // Hold the numbers from the current list
            HashMap<Integer, Integer> currentListNumbers = new HashMap<Integer, Integer>();

            // Hold the numbers which are in intersect with the global hashmap
            HashMap<Integer, Integer> intersectWithGlobal = new HashMap<Integer, Integer>();

            listNumbers = s.nextInt();

            addToHashMap(currentListNumbers, listNumbers);

            for (Map.Entry<Integer, Integer> pair : currentListNumbers.entrySet()) {

                if (intersect.containsKey(pair.getKey())) {
                    Integer intersectNumberCount = intersect.get(pair.getKey());
                    Integer currentListNumberCount = pair.getValue();

                    if (intersectNumberCount <= currentListNumberCount) {
                        intersectWithGlobal.put(pair.getKey(), intersect.get(pair.getKey()));
                    } else if (intersectNumberCount > currentListNumberCount) {
                        intersectWithGlobal.put(pair.getKey(), currentListNumberCount);
                    }
                }
            }

            intersect = intersectWithGlobal;
        }

        for (Map.Entry<Integer, Integer> pair : intersect.entrySet()) {

            for (int i = 0; i < pair.getValue(); i++) {
                result.append(pair.getKey() + "\n");
            }
        }

        System.out.print(result.toString());
    }

    private static void addToHashMap(HashMap<Integer, Integer> map, int count) {

        for (int j = 0; j < count; j++) {
            int currentNumber = s.nextInt();

            if (map.containsKey(currentNumber)) {
                map.put(currentNumber, map.get(currentNumber) + 1);
            } else {
                map.put(currentNumber, 1);
            }
        }
    }
}