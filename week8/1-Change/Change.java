import java.util.Scanner;

public class Change {

    private static int[] coins = new int[] { 1, 2, 5, 10, 20, 50, 100 };

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int requiredSum = s.nextInt();
        System.out.println(totalTimes(requiredSum));
    }

    private static int totalTimes(int requiredSum) {

        int[] sums = new int[requiredSum + 1];
        sums[0] = 1;

        for (int i = 0; i < coins.length; i++) {
            int currentCoin = coins[i];

            for (int j = 0; j < sums.length - 1; j++) {
                int nextIndex = currentCoin + j;

                if (nextIndex < sums.length) {
                    sums[nextIndex] += sums[j];
                } else {
                    break;
                }
            }
        }

        return sums[requiredSum];
    }
}