import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Navigation {

    private static List<ArrayList<Vertex>> graph = new ArrayList<ArrayList<Vertex>>();

    private static int[] shortestPath;
    private static int[] prevPath;

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        int n = s.nextInt();

        shortestPath = new int[n];
        prevPath = new int[n];

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<Vertex>());
            shortestPath[i] = Integer.MAX_VALUE;
            prevPath[i] = -1;
        }

        int m = s.nextInt();

        int startingIndex = s.nextInt();
        int endIndex = s.nextInt();

        for (int i = 0; i < m; i++) {
            int fromIndex = s.nextInt();
            int toIndex = s.nextInt();
            int weight = s.nextInt();

            graph.get(fromIndex - 1).add(new Vertex(toIndex - 1, weight));
            graph.get(toIndex - 1).add(new Vertex(fromIndex - 1, weight));
        }

        getMinDistance(startingIndex - 1);

        System.out.println(shortestPath[endIndex - 1]);
        System.out.println(getPrevPath(endIndex - 1));
    }

    private static void getMinDistance(int startingIndex) {

        PriorityQueue<Vertex> neighbours = new PriorityQueue<Vertex>(10, new Comparator<Vertex>() {

            @Override
            public int compare(Vertex arg0, Vertex arg1) {
                return Integer.compare(arg0.weight, arg1.weight);
            }
        });

        neighbours.add(new Vertex(startingIndex, 0));
        shortestPath[startingIndex] = 0;

        while (!neighbours.isEmpty()) {

            Vertex head = neighbours.poll();
            int headIndex = head.vertexIndex;

            for (int i = 0; i < graph.get(headIndex).size(); i++) {
                Vertex currentVertex = graph.get(headIndex).get(i);
                int currentWeightToI = currentVertex.weight;

                if (currentWeightToI + head.weight < shortestPath[currentVertex.vertexIndex] && currentWeightToI > 0) {
                    shortestPath[currentVertex.vertexIndex] = currentWeightToI + head.weight;
                    prevPath[currentVertex.vertexIndex] = head.vertexIndex;
                    neighbours.add(new Vertex(currentVertex.vertexIndex, shortestPath[currentVertex.vertexIndex]));
                }
            }
        }
    }

    private static String getPrevPath(int index) {
        StringBuilder path = new StringBuilder();

        while (index != -1) {
            path.insert(0, (index + 1) + " ");
            index = prevPath[index];
        }

        return path.toString().trim();
    }
}