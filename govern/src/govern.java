import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by anatolii on 05.06.16.
 */
public class govern
{
    private static final String PATH = System.getProperty("user.dir");

    public static void main(String[] args) throws FileNotFoundException
    {

        Graph graph = readGraphFromFile(PATH + "/govern.in");
        List<Vertex> topologicalOrder =  getTopologicOrder(graph);
        write_to_file(topologicalOrder);

    }
    private static Graph readGraphFromFile(String fileName) throws FileNotFoundException {
        File inputFile = new File(fileName);

        try (Scanner inputFileScanner = new Scanner(inputFile)) {

            Set<String> uniqueLabels = new LinkedHashSet<>();
            List<Vertex> vertices;
            List<Edge> edges = new ArrayList<>();
            List<String[]> tmpEdgesList = new ArrayList<>();
            Map<String, Vertex> uniqueVertexMap = new LinkedHashMap<>();

            while (inputFileScanner.hasNextLine()) {
                String[] tmpEdges = inputFileScanner.nextLine().split(" ");
                uniqueLabels.add(tmpEdges[0]);
                uniqueLabels.add(tmpEdges[1]);
                tmpEdgesList.add(tmpEdges);
            }

            for (String string : uniqueLabels) {
                uniqueVertexMap.put(string, new Vertex(string));
            }

            for (String[] pair : tmpEdgesList) {
                edges.add(new Edge(uniqueVertexMap.get(pair[0]), uniqueVertexMap.get(pair[1])));
                uniqueVertexMap.get(pair[0]).getOutboundEdges().add(edges.get(edges.size() - 1));
            }


            vertices = new ArrayList<>(uniqueVertexMap.values());
            Graph graph = new Graph(vertices, edges);

            return graph;
        }
    }
    private static void write_to_file(List<Vertex> topologicalOrder)
    {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(PATH + "/govern.out", false));
            String orderFormatted = topologicalOrder.stream()
                    .map(v -> String.valueOf(v.getLabel()))
                    .collect(Collectors.joining("\n"));
            writer.write(orderFormatted);
            writer.flush();
            writer.close();
        }
        catch (Exception e) {
            System.out.print("Exception in writting file");
        }
    }
    private static List<Vertex> getTopologicOrder(Graph graph)
    {
        TopologicalSorter sorter = new TopologicalSorter();
        List<Vertex> topologicalOrder =  sorter.getTopologicOrder(graph);
        return topologicalOrder;
    }
}
