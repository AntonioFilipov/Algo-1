import java.util.Scanner;

public class MaxBanana {

    private static int[][] jungle;
    private static int[][] maxBananas;

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        int n = s.nextInt();

        jungle = new int[n][n];
        maxBananas = new int[n][n];

        for (int i = 0; i < jungle.length; i++) {
            for (int j = 0; j < jungle[0].length; j++) {
                jungle[i][j] = s.nextInt();
            }
        }

        getMaxBananas();
        System.out.println(maxBananas[0][n - 1]);
    }

    private static void getMaxBananas() {

        for (int col = 0; col < jungle[0].length; col++) {
            for (int row = jungle.length - 1; row >= 0; row--) {

                int leftBananas = col - 1 < 0 ? 0 : maxBananas[row][col - 1];
                int downBananas = row + 1 >= jungle.length ? 0 : maxBananas[row + 1][col];

                maxBananas[row][col] = Math.max(leftBananas, downBananas) + jungle[row][col];
            }
        }
    }
}