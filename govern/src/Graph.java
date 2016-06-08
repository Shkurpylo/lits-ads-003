import java.util.ArrayList;
import java.util.List;

/**
 * Created by anatolii on 05.06.16.
 */
public class Graph {
    private List<Vertex> vertices;
    private List<Edge> edges;

    public void setVertices(List<Vertex> vertices) {
        this.vertices = vertices;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public List<Vertex> getVertices() {

        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }
    public Graph()
    {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }
    public Graph(List<Vertex> vertices, List<Edge> edges)
    {
        this.vertices = vertices;
        this.edges = edges;
    }



}
