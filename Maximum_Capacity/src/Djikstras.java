import java.util.ArrayList;

/**
 * Created by arunk on 11/21/14.
 */
public class Djikstras {
    Vertex source;
    Vertex destination;
    Graph graph;

    public Djikstras(Vertex source, Vertex destination, Graph graph) {
        this.source = source;
        this.destination = destination;
        this.graph = graph;
    }

    public ArrayList<Edge> findMaximumCapacityPath() {
        ArrayList<Edge> path = new ArrayList<Edge>();
        
        return path;
    }
}
