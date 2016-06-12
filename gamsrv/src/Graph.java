import java.util.*;

/**
 * Created by anatolii on 05.06.16.
 */
public class Graph {
    private Map<Integer, Vertex> vertices;
    private List<Edge> edges;
    private List<Vertex> possibleServers;
    private Set<Vertex> clientsVertices;

    public Set<Vertex> getClients() { return clientsVertices;}
    public void setClients(Set<Vertex> clients) { this.clientsVertices = clients;}

    public List<Vertex> getPossibleServers() { return possibleServers;}
    public void setPossibleServers(List<Vertex> possibleServers) { this.possibleServers = possibleServers;}

    public void setVertices(Map<Integer, Vertex> vertices) {
        this.vertices = vertices;
    }
    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public Map<Integer, Vertex> getVertices() {
        return vertices;
    }
    public List<Edge> getEdges() {
        return edges;
    }

    public Graph() {
        vertices = new HashMap<>();
        edges = new ArrayList<>();
    }
    public Graph(Map<Integer, Vertex> vertices, List<Edge> edges, List<Vertex> possibleServers, Set<Vertex> clientsVertices) {
        this.vertices = vertices;
        this.edges = edges;
        this.possibleServers = possibleServers;
        this.clientsVertices = clientsVertices;
    }



}
