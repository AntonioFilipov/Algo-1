public class Vertex {

    public int vertexIndex;
    public int weight;

    public Vertex(int vertexIndex, int weight) {
        this.weight = weight;
        this.vertexIndex = vertexIndex;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        output.append("To -> " + (vertexIndex + 1) + " with " + weight);

        return output.toString();
    }
}