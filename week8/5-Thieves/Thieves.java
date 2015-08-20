import java.util.Scanner;

public class Thieves {

    private static int[][] dp;

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        int totalItems = s.nextInt();
        int bagCapacity = s.nextInt();

        dp = new int[totalItems + 1][bagCapacity + 1];

        for (int i = 1; i < dp.length; i++) {

            int itemW = s.nextInt();
            int itemV = s.nextInt();

            for (int j = 1; j < dp[0].length; j++) {

                while (j < itemW && j < dp[0].length) {
                    dp[i][j] = dp[i - 1][j];
                    j++;
                }

                if (j == dp[0].length)
                    break;

                dp[i][j] = Math.max(dp[i - 1][j], itemV + dp[i - 1][j - itemW]);
            }
        }

        System.out.println(dp[totalItems][bagCapacity]);
    }
}