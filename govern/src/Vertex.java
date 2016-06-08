import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by anatolii on 05.06.16.
 */
public class Vertex {


    private String label;
    private List<Edge> outboundEdges;

    public String getLabel() {
        return label;
    }
    public void setLabel(String label){
        this.label = label;
    }
    public List<Edge> getOutboundEdges()
    {
        return outboundEdges;
    }
    public Vertex(String label)
    {
        this.label = label;
        outboundEdges = new ArrayList<>();
    }
    public void addEdgesToOutboungEdges(Edge edge)
    {
        outboundEdges.add(edge);
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Vertex: ");
        stringBuilder.append(this.getLabel());
        stringBuilder.append("; Edges: ");
        for(Edge edge: this.getOutboundEdges())
        {
            stringBuilder.append(edge.toString());
        }
        return stringBuilder.toString();
    }

}
