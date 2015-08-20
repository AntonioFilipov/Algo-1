import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BankRobbery {

    private static int[][] graph;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int junctions = s.nextInt();

        graph = new int[junctions + 1][junctions + 1];

        int n = s.nextInt();

        for (int i = 0; i < n; i++) {
            int from = s.nextInt();
            int to = s.nextInt();

            graph[from][to] = 1;
            graph[to][from] = 1;
        }

        int bankIndex = s.nextInt();
        int policeIndex = s.nextInt();
        int helicopterIndex = s.nextInt();

        int policeToHeli = BFS(policeIndex, helicopterIndex);
        int youToHeli = BFS(bankIndex, helicopterIndex);

        System.out.println(policeToHeli - youToHeli - 1);
    }

    private static int BFS(int fromIndex, int toIndex) {

        boolean[] visited = new boolean[graph.length];

        int currentStep = 0;
        int currentStepJuncs = 1;

        Queue<Integer> junctions = new LinkedList<Integer>();
        junctions.add(fromIndex);
        visited[fromIndex] = true;

        while (!junctions.isEmpty()) {

            currentStep++;
            int nextJunctionsCount = 0;

            for (int i = 0; i < currentStepJuncs; i++) {
                int nextJunction = junctions.poll();

                for (int j = 0; j < graph[nextJunction].length; j++) {

                    if (graph[nextJunction][j] == 1 && !visited[j]) {

                        if (j == toIndex) {
                            return currentStep;
                        }

                        visited[j] = true;
                        junctions.add(j);
                        nextJunctionsCount++;
                    }
                }
            }

            currentStepJuncs = nextJunctionsCount;
        }

        return 0;
    }
}