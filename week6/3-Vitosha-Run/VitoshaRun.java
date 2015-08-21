import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class VitoshaRun {

    private static int[][] matrix;
    private static int[][] minPathMatrix;

    private static int[][] directions = new int[][] { { 1, 0 }, { 0, 1 }, { 1, 1 }, { -1, 0 }, { 0, -1 }, { -1, 1 },
            { 1, -1 }, { -1, -1 } };

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        int n = s.nextInt();
        matrix = new int[n][n];
        minPathMatrix = new int[n][n];

        Vertex startingPoint = new Vertex(s.nextInt(), s.nextInt(), 0);
        Vertex endPoint = new Vertex(s.nextInt(), s.nextInt(), Integer.MAX_VALUE);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = s.nextInt();
                minPathMatrix[i][j] = Integer.MAX_VALUE;
            }
        }

        fillMinPaths(startingPoint, endPoint);

        System.out.println(minPathMatrix[endPoint.x][endPoint.y]);
    }

    private static void fillMinPaths(Vertex startingPoint, Vertex endPoint) {

        PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>(new Comparator<Vertex>() {
            @Override
            public int compare(Vertex arg0, Vertex arg1) {
                return Integer.compare(arg0.value, arg1.value);
            }
        });

        minPathMatrix[startingPoint.x][startingPoint.y] = 0;
        queue.add(startingPoint);

        while (!queue.isEmpty()) {

            Vertex currentPoint = queue.poll();

            for (int i = 0; i < directions.length; i++) {
                int nextRow = currentPoint.x + directions[i][0];
                int nextCol = currentPoint.y + directions[i][1];

                if (isValidCell(nextRow, nextCol)) {
                    int nextPointValue = Math.abs(matrix[currentPoint.x][currentPoint.y] - matrix[nextRow][nextCol])
                            + 1 + currentPoint.value;

                    if (minPathMatrix[nextRow][nextCol] > nextPointValue) {
                        minPathMatrix[nextRow][nextCol] = nextPointValue;

                        queue.add(new Vertex(nextRow, nextCol, nextPointValue));
                    }
                }
            }
        }
    }

    private static boolean isValidCell(int x, int y) {
        return (x < matrix.length && x >= 0) && (y < matrix[0].length && y >= 0);
    }
}
