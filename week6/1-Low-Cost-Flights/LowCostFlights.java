import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class LowCostFlights {

    private static int[][] graph;
    private static int[][] minPaths;

    private static StringBuilder output = new StringBuilder();

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        int n = s.nextInt();
        graph = new int[n][n];
        minPaths = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = s.nextInt();
                minPaths[i][j] = Integer.MAX_VALUE;
            }
        }

        int commands = s.nextInt();

        List<Integer> startAirpoints = new ArrayList<Integer>();
        List<ArrayList<Integer>> allFlights = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < commands; i++) {
            int startAirport = s.nextInt();
            int endAirport = s.nextInt();

            ArrayList<Integer> currentFlight = new ArrayList<Integer>();
            currentFlight.add(startAirport);
            currentFlight.add(endAirport);

            if (!startAirpoints.contains(startAirport)) {
                startAirpoints.add(startAirport);
            }

            allFlights.add(currentFlight);
        }

        for (int i = 0; i < startAirpoints.size(); i++) {
            createMinPaths(startAirpoints.get(i));
        }

        for (int i = 0; i < allFlights.size(); i++) {
            output.append(getMinPath(allFlights.get(i).get(0), allFlights.get(i).get(1)));
        }

        System.out.print(output.toString());
    }

    private static void createMinPaths(int startPoint) {

        PriorityQueue<Vertex> neighbours = new PriorityQueue<Vertex>(10, new Comparator<Vertex>() {
            @Override
            public int compare(Vertex arg0, Vertex arg1) {
                return Integer.compare(arg0.weight, arg1.weight);
            }
        });

        minPaths[startPoint][startPoint] = 0;
        neighbours.add(new Vertex(startPoint, 0));

        while (!neighbours.isEmpty()) {

            Vertex min = neighbours.poll();

            for (int i = 0; i < graph[min.vertex].length; i++) {
                int currentWeightToI = graph[min.vertex][i];

                if (currentWeightToI > 0 && currentWeightToI + min.weight < minPaths[startPoint][i]) {
                    minPaths[startPoint][i] = currentWeightToI + min.weight;

                    neighbours.add(new Vertex(i, minPaths[startPoint][i]));
                }
            }
        }
    }

    private static String getMinPath(int from, int to) {
        int path = minPaths[from][to];
        return path != Integer.MAX_VALUE ? String.valueOf(path) + "\n" : "NO WAY\n";
    }
}