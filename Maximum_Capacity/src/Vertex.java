import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by arunk on 11/14/14.
 */
public class Vertex implements Comparable{
    ArrayList<Edge> edges;
    final Integer index;
    HashSet<Edge> edges_cache;
    Integer degree;
    Vertex parent;
    Integer rank;

    public Vertex(Integer index) {
        this.index = index;
        this.degree = 0;
        this.rank = 0;
        this.parent = this;
        edges_cache = new HashSet<Edge>();
        edges = new ArrayList<Edge>();
    }

    public Boolean edgeInSet(Vertex v) {
        return edges_cache.contains(new Edge(this, v));
    }

    public void clearCache() {
        this.edges_cache = null;
    }

    @Override
    public int compareTo(Object o) {
        Vertex v = (Vertex) o;
        return this.index > v.index ? 1 : (this.index.equals(v.index) ? 0 : -1);
    }

    @Override
    public int hashCode() {
        return index;
    }

    @Override
    public boolean equals(Object obj) {
        return this.hashCode() == obj.hashCode();
    }
}
