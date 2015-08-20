import java.util.Scanner;

public class LongestCommonSubstring {

    private static int[][] dp;

    private static int longestLenght = 0;
    private static int endOfLongestSubstring = -1;

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        String firstText = s.nextLine();
        String secondText = s.nextLine();

        dp = new int[secondText.length() + 1][firstText.length() + 1];

        for (int i = 1; i < dp.length; i++) {

            char secondTextChar = secondText.charAt(i - 1);

            for (int j = 1; j < dp[i].length; j++) {

                char firstTextChar = firstText.charAt(j - 1);

                if (firstTextChar == secondTextChar) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;

                    if (dp[i][j] > longestLenght) {
                        longestLenght = dp[i][j];
                        endOfLongestSubstring = j;
                    }
                }
            }
        }

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < longestLenght; i++) {
            output.insert(0, firstText.charAt(endOfLongestSubstring - i - 1));
        }

        System.out.println(output.toString());
    }
}