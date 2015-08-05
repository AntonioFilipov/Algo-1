package week3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class ClosestCoffeeStore {

    private List<Integer> visited = new ArrayList<Integer>();

    /**
     *  Finds the closest coffee store to a point.
     * @param graph
     * @param isCoffeStore
     * @param startingPoint
     * @return
     */
    public int closestCoffeeStore(boolean[][] graph, boolean[] isCoffeStore, int startingPoint) {

        int stepsCount = 1;

        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(startingPoint);
        visited.add(startingPoint);

        int childrensFromPreviousLevel = 1;

        while (!queue.isEmpty()) {

            int currentLevelChildrens = 0;

            for (int j = 0; j < childrensFromPreviousLevel; j++) {

                int currentIndex = queue.poll();

                for (int i = 0; i < graph[currentIndex].length; i++) {
                    if (graph[currentIndex][i] && !this.visited.contains(i)) {
                        queue.add(i);
                        visited.add(i);
                        currentLevelChildrens++;

                        if (isCoffeStore[i]) {
                            return stepsCount;
                        }
                    }
                }
            }

            childrensFromPreviousLevel = currentLevelChildrens;
            stepsCount++;
        }

        return -1;
    }

    public static void main(String[] args) {

        ClosestCoffeeStore c = new ClosestCoffeeStore();

        Scanner s = new Scanner(System.in);
        int n = s.nextInt();

        boolean[][] graph = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = s.nextInt() == 1 ? true : false;
            }
        }

        int startingPoint = s.nextInt();

        boolean[] isCoffeStore = new boolean[n];
        for (int i = 0; i < n; i++) {
            isCoffeStore[i] = s.nextInt() == 1 ? true : false;
        }

        int steps = new ClosestCoffeeStore().closestCoffeeStore(graph, isCoffeStore, startingPoint);

        System.out.println(steps);
    }
}