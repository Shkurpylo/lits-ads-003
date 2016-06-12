/**
 * Created by anatolii on 05.06.16.
 */
public class Edge {
    private Vertex startVertex;
    private Vertex endVertex;
    private int weight;


    public void setEndVertex(Vertex endVertex) {
        this.endVertex = endVertex;
    }

    public void setStartVertex(Vertex startVertex) {
        this.startVertex = startVertex;
    }

    public Vertex getStartVertex() { return startVertex; }

    public Vertex getEndVertex() {
        return endVertex;
    }
    public int getWeight() { return weight; }

    public void setWeight(int weight) { this.weight = weight; }

    public Edge(Vertex startVertex, Vertex endVertex, int weight) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return String.format("%d ----%d----> %d; ", startVertex.getLabel(), weight, endVertex.getLabel());
    }
}
