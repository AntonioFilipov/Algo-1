import java.util.Scanner;

public class Dictionary {

    private static Trie trie = new Trie();

    private static StringBuilder output = new StringBuilder();

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        int n = s.nextInt();

        for (int i = 0; i < n; i++) {

            String command = s.next();
            String word = s.next();

            switch (command) {
                case "insert":
                    trie.insert(word);
                    break;
                case "contains":
                    output.append(trie.contains(word) + "\n");
                    break;

                default:
                    throw new IllegalArgumentException("Invalid command");
            }
        }

        System.out.print(output.toString());
    }
}