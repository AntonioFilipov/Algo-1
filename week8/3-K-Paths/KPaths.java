import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class KPaths {

    private static final int MAX_VERTICES = 1000;

    private static int[][] dp;
    private static List<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();

    public static void main(String[] args) {

        for (int i = 0; i <= MAX_VERTICES; i++) {
            graph.add(new ArrayList<Integer>());
        }

        dp = new int[MAX_VERTICES + 1][MAX_VERTICES + 1];

        Scanner s = new Scanner(System.in);
        int edges = s.nextInt();

        for (int i = 0; i < edges; i++) {
            int from = s.nextInt() + 1;
            int to = s.nextInt() + 1;

            graph.get(from).add(to);
        }

        int startingVertex = s.nextInt() + 1;
        int endVertex = s.nextInt() + 1;
        int maxStep = s.nextInt();

        for (int i = 0; i < MAX_VERTICES; i++) {
            dp[0][i] = 1;
            dp[i][0] = 1;
        }

        HashSet<Integer> currentStepVertices = new HashSet<Integer>();
        currentStepVertices.add(startingVertex);

        for (int currentStep = 1; currentStep <= maxStep; currentStep++) {

            HashSet<Integer> nextStepVertices = new HashSet<Integer>();
            Iterator<Integer> it = currentStepVertices.iterator();

            while (it.hasNext()) {

                int currentVertex = it.next();

                for (int j = 0; j < graph.get(currentVertex).size(); j++) {
                    int nextVertex = graph.get(currentVertex).get(j);
                    nextStepVertices.add(nextVertex);

                    dp[nextVertex][currentStep] += dp[currentVertex][currentStep - 1];
                }
            }

            currentStepVertices = nextStepVertices;
        }

        System.out.println(dp[endVertex][maxStep]);
    }
}