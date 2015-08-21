public class Vertex {
    public int vertex;
    public int weight;

    public Vertex(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("Index " + this.vertex + " weight " + this.weight);
        return output.toString();
    }
}