import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by anatolii on 09.06.16.
 */
public class gamsrv {
    private static final String PATH = System.getProperty("user.dir");

    public static void main(String[] args) throws FileNotFoundException {
        Graph graph = readGraphFromFile(PATH + "/gamsrv.in");
        long shortestPath = getShortestPathToClient(graph);
        writeToFile(shortestPath);
        System.out.println(shortestPath);
    }

    private static Graph readGraphFromFile(String fileName) throws FileNotFoundException {
        File inputFile = new File(fileName);

        try (Scanner inputFileScanner = new Scanner(inputFile)) {

            int vertexCount = inputFileScanner.nextInt();
            int edgeCount = inputFileScanner.nextInt();

            //skip "\n" symbol
            inputFileScanner.nextLine();

            //create an array of clients
            String[] tmp = inputFileScanner.nextLine().split(" ");
            Set<Integer> clientsLabels = new HashSet<>(tmp.length);
            for (int i = 0; i < tmp.length; i++) {
                clientsLabels.add(Integer.parseInt(tmp[i]));
            }

            Set<Vertex> clientsVertices = new HashSet<>(clientsLabels.size());
            List<Vertex> possibleServers = new ArrayList(clientsLabels.size());
            Map<Integer, Vertex> vertices = new HashMap<>(vertexCount);
            List<Edge> edges = new ArrayList<>(vertexCount);

            //create Lists of Vertices and possibleServers
            for (int v = 1; v <= vertexCount; v++) {
                vertices.put(v, new Vertex(v));
                if (!clientsLabels.contains(v)) {
                    possibleServers.add(vertices.get(v));
                }
                else {
                    clientsVertices.add(vertices.get(v));
                }
            }

            for (int e = 0; e < edgeCount; e++) {
                int startVertexLabel = inputFileScanner.nextInt();
                int endVertexLabel = inputFileScanner.nextInt();
                int weight = inputFileScanner.nextInt();

                Edge edge = new Edge(vertices.get(startVertexLabel), vertices.get(endVertexLabel), weight);
                edges.add(edge);
                vertices.get(startVertexLabel).getOutboundEdges().add(edge);

                //reversing edge for improve non-directed graph
                Edge reverseEdge = new Edge(vertices.get(endVertexLabel), vertices.get(startVertexLabel), weight);
                edges.add(reverseEdge);
                vertices.get(endVertexLabel).getOutboundEdges().add(reverseEdge);
            }


            Graph graph = new Graph(vertices, edges, possibleServers, clientsVertices);

            return graph;
        }
    }


    public static long getShortestPathToClient(Graph graph){
        
        Map<Vertex, Long> distances = new HashMap<>(graph.getVertices().size());
        //Priority Queue for result
        PriorityQueue<Long> allPossibleResults = new PriorityQueue<>(graph.getPossibleServers().size());
        for(Vertex startVertex : graph.getPossibleServers()) {

            long maxDistance = 0;
            final long INFINITY = Long.MAX_VALUE;
            for (Vertex vertex : graph.getVertices().values()) {
                distances.put(vertex, INFINITY);
            }
            distances.put(startVertex, 0L);

            //Priority Queue for distances
            Comparator<Vertex> vertexDistanceComparator = (v1, v2) -> distances.get(v1).compareTo(distances.get(v2));
            PriorityQueue<Vertex> heap = new PriorityQueue<>(vertexDistanceComparator);
            heap.add(startVertex);

            while (heap.size() > 0) {
                Vertex shortDistantVertex = heap.remove();

                for (Edge edge : shortDistantVertex.getOutboundEdges()) {
                    Vertex neighbor = edge.getEndVertex();
                    long alternativeDistance = distances.get(shortDistantVertex) + edge.getWeight();

                    if (alternativeDistance < distances.get(neighbor)) {
                        distances.put(neighbor, alternativeDistance);

                        maxDistance = alternativeDistance > maxDistance ? alternativeDistance : maxDistance;

                        // Pushing the new vertex to the heap.
                        heap.add(neighbor);
                    }
                }
            }
            //find the biggest distance for each version of startVertex (server position)
            long result = 0L;
            for(Vertex clientsLabel : graph.getClients())
            {
                long maxDistanceInLoop = distances.get(clientsLabel);
                result = result > maxDistanceInLoop? result : maxDistanceInLoop;
            }
            allPossibleResults.add(result);
        }

        //get the smallest possible distance
        return allPossibleResults.peek();
    }

    static void writeToFile(long solution)
    {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(PATH + "/gamsrv.out", false));
            DecimalFormat df = new DecimalFormat("#0");
            String dx = df.format(solution);
            writer.write(dx);
            writer.flush();
            writer.close();
        }
        catch (Exception e) {
            System.out.print("Exception in writting file");
        }
    }
}
