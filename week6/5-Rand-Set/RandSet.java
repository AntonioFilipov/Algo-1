import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class RandSet {

    private static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    private static ArrayList<Integer> arr = new ArrayList<Integer>();

    private static StringBuilder result = new StringBuilder();

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        int commandsCount = s.nextInt();

        for (int i = 0; i < commandsCount; i++) {
            String command = s.next();

            switch (command) {
                case "insert":
                    int numberToAdd = s.nextInt();

                    if (map.containsKey(numberToAdd)) {
                        break;
                    }

                    map.put(numberToAdd, arr.size());
                    arr.add(numberToAdd);
                    break;
                case "remove":
                    int numberToRemove = s.nextInt();

                    int lastNumberIndex = arr.size() - 1;
                    int switchedNumberIndex = map.get(numberToRemove);

                    // switch the value with the last one
                    arr.set(switchedNumberIndex, arr.get(lastNumberIndex));
                    map.put(lastNumberIndex, switchedNumberIndex);

                    arr.remove(arr.size() - 1);
                    map.remove(numberToRemove);

                    break;
                case "contains":
                    int numberContains = s.nextInt();
                    result.append(map.containsKey(numberContains) ? "true\n" : "false\n");

                    break;
                case "random":
                    result.append(arr.get(new Random().nextInt(arr.size())) + "\n");

                    break;
            }
        }

        System.out.println(result.toString());
    }
}
