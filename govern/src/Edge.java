/**
 * Created by anatolii on 05.06.16.
 */
public class Edge
{
    private Vertex startVertex;
    private Vertex endVertex;


    public void setEndVertex(Vertex endVertex) {
        this.endVertex = endVertex;
    }

    public void setStartVertex(Vertex startVertex) {
        this.startVertex = startVertex;
    }

    public Vertex getStartVertex() {

        return startVertex;
    }

    public Vertex getEndVertex() {
        return endVertex;
    }

    public Edge(Vertex startVertex, Vertex endVertex)
    {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
    }

    @Override
    public String toString()
    {
        return String.format(startVertex.getLabel() + " -> " + endVertex.getLabel() + "\n");
    }
}
