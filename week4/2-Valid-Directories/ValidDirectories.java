import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.HashSet;

class Graph {

    static class Node {
        public final String name;
        public final HashSet<Edge> inEdges;
        public final HashSet<Edge> outEdges;

        public Node(String name) {
            this.name = name;
            inEdges = new HashSet<Edge>();
            outEdges = new HashSet<Edge>();
        }

        public Node addEdge(Node node) {
            Edge e = new Edge(this, node);
            outEdges.add(e);
            node.inEdges.add(e);
            return this;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    static class Edge {
        public final Node from;
        public final Node to;

        public Edge(Node from, Node to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object obj) {
            Edge e = (Edge) obj;
            return e.from == from && e.to == to;
        }
    }
}

public class ValidDirectories {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        int count = s.nextInt();

        ArrayList<Graph.Node> allNodes = new ArrayList<Graph.Node>();
        for (int i = 0; i < count; i++) {
            allNodes.add(new Graph.Node(""));
        }

        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                int next = s.nextInt();
                if (next == 1) {
                    allNodes.get(i).addEdge(allNodes.get(j));
                }
            }
        }

        /**
         * Empty list that will contain the sorted elements
         */
        ArrayList<Graph.Node> sortedElements = new ArrayList<Graph.Node>();

        HashSet<Graph.Node> nodesWithNoIncomingEdges = new HashSet<Graph.Node>();
        for (Graph.Node n : allNodes) {
            if (n.inEdges.size() == 0) {
                nodesWithNoIncomingEdges.add(n);
            }
        }

        while (!nodesWithNoIncomingEdges.isEmpty()) {
            Graph.Node n = nodesWithNoIncomingEdges.iterator().next();
            nodesWithNoIncomingEdges.remove(n);

            sortedElements.add(n);

            for (Iterator<Graph.Edge> it = n.outEdges.iterator(); it.hasNext();) {
                Graph.Edge e = it.next();
                Graph.Node m = e.to;
                it.remove();
                m.inEdges.remove(e);

                if (m.inEdges.isEmpty()) {
                    nodesWithNoIncomingEdges.add(m);
                }
            }
        }
        boolean cycle = false;
        for (Graph.Node n : allNodes) {
            if (!n.inEdges.isEmpty()) {
                cycle = true;
                break;
            }
        }
        if (cycle) {
            System.out.println("false");
        } else {
            System.out.println("true");
        }
    }
}