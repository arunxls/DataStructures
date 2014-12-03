import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by arunk on 11/14/14.
 */
public class Vertex implements Comparable<Vertex>{
    ArrayList<Edge> edges;
    final Integer index;
    HashSet<Edge> edges_cache;
    Integer degree;
    Vertex parent;
    Integer rank;
    Integer status;
    Integer distance;

    public Vertex(Integer index) {
        this.index = index;
        this.degree = 0;
        this.rank = 0;
        this.distance = -1; // Dijkstra distance cannot be negative, let this equal -infinity
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
    public int compareTo(Vertex v) {
        return this.distance > v.distance ? 1 : (this.distance.equals(v.distance) ? 0 : -1);
    }

    @Override
    public int hashCode() {
        return index;
    }

    @Override
    public boolean equals(Object obj) {
        return this.hashCode() == obj.hashCode();
    }

    public Boolean isFringe() {
        return status == 1;
    }

    public Boolean isSeen() {
        return status == 2;
    }

    public Boolean isUnSeen() {
        return status == 0;
    }

    public void setFringe() {
        status = 1;
    }

    public void setSeen() {
        status = 2;
    }

    public void setUnSeen() {
        status = 0;
    }
}
