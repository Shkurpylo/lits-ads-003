import java.util.ArrayList;
import java.util.List;

/**
 * Created by anatolii on 05.06.16.
 */
public class Vertex {


    private int label;
    private List<Edge> outboundEdges;

    public int getLabel() {
        return label;
    }
    public void setLabel(int label){
        this.label = label;
    }
    public List<Edge> getOutboundEdges()
    {
        return outboundEdges;
    }
    public Vertex(int label) {
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
