import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by arunk on 11/14/14.
 */
public class Vertex {
    ArrayList<Edge> edges;
    Integer index;
    HashSet<Edge> edges_cache;
    Integer degree;

    public Vertex(Integer index) {
        this.index = index;
        this.degree = 0;
        edges_cache = new HashSet();
        edges = new ArrayList<Edge>();
    }

    public Boolean edgeInSet(Vertex v) {
        return edges_cache.contains(new Edge(this, v));
    }

    public void addEdge (Edge e) {

    }

    public void clearCache() {
        this.edges_cache = null;
    }
}
