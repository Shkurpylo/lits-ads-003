import java.util.*;

/**
 * Created by anatolii on 07.06.16.
 */
public class TopologicalSorter
{
    private enum VisitedStatus
    {
        Visited,
        NonVisited,
        VisitedAndResolved
    }
    private ArrayList<Vertex> order;
    private Set<Vertex> unvisitedVertices;
    private Map<String, VisitedStatus> visitedStatus;

    public ArrayList<Vertex> getTopologicOrder(Graph graph)
    {
        order = new ArrayList<>(graph.getVertices().size());
        unvisitedVertices = new HashSet<>(graph.getVertices().size());
        visitedStatus = new HashMap<>(graph.getVertices().size());

        for(Vertex vertex : graph.getVertices())
        {
            visitedStatus.put(vertex.getLabel(), VisitedStatus.NonVisited);
            unvisitedVertices.add(vertex);
        }

        while (unvisitedVertices.size() > 0)
        {
            Vertex unvisitedVertex = unvisitedVertices.stream().findFirst().get();

            /*chose DFS realization - recursive of stack ?*/

            //dfsRecursive(unvisitedVertex);
            dfsStack(unvisitedVertex);
        }

        return order;
    }

    private void dfsRecursive(Vertex vertex)
    {

        if(visitedStatus.get(vertex.getLabel()) == VisitedStatus.Visited)
        {
            throw new NotDirectedAcyclicGraphExeption();
        }
        if(visitedStatus.get(vertex.getLabel()) == VisitedStatus.NonVisited) {
            visitedStatus.replace(vertex.getLabel(), VisitedStatus.Visited);
            unvisitedVertices.remove(vertex);


            List<Vertex> neighbors = new ArrayList<>();
            for (Edge edge : vertex.getOutboundEdges()) {
                neighbors.add(edge.getEndVertex());
            }
            for (Vertex neighborsVertex : neighbors) {
                dfsRecursive(neighborsVertex);
            }


            visitedStatus.replace(vertex.getLabel(), VisitedStatus.VisitedAndResolved);
            order.add(vertex);
        }
    }
    private void dfsStack(Vertex startVertex){
        Stack<Vertex> stack = new Stack<Vertex>();
        stack.push(startVertex);

        while(!stack.isEmpty()){
            Vertex vertex = stack.pop();
            visitedStatus.replace(vertex.getLabel(), VisitedStatus.Visited);
            if(unvisitedVertices.contains(vertex)){
                unvisitedVertices.remove(vertex);
            }

            List<Vertex> unvisitedNeighbors = new ArrayList<>();
            for(Edge edge : vertex.getOutboundEdges())
            {
                Vertex neighbor = edge.getEndVertex();

                if(visitedStatus.get(neighbor.getLabel()) == VisitedStatus.Visited){
                    throw new NotDirectedAcyclicGraphExeption();
                }
                if(visitedStatus.get(neighbor.getLabel()) == VisitedStatus.NonVisited) {
                    unvisitedNeighbors.add(neighbor);
                }
            }
            if(unvisitedNeighbors.isEmpty()){
                visitedStatus.replace(vertex.getLabel(), VisitedStatus.VisitedAndResolved);
                order.add(vertex);
            } else {
                stack.push(vertex);
                stack.addAll(unvisitedNeighbors);
            }
        }
    }
}
