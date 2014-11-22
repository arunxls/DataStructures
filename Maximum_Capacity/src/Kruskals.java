import java.util.ArrayList;

/**
 * Created by arunk on 11/21/14.
 */
public class Kruskals {
    Vertex source;
    Vertex destination;
    Heap edges;
    Graph graph;
    ArrayList<Vertex> stack;

    public Kruskals(Vertex source, Vertex destination, Graph graph) {
        this.source = source;
        this.destination = destination;
        this.edges = new Heap(graph.edges);
        this.graph = graph;
        stack = new ArrayList<Vertex>();
    }

    public ArrayList<Edge> findMaximumCapacityPath() {
        ArrayList<Edge> path = new ArrayList<Edge>();
        while(edges.heap.size() != 0) {
            Edge e = edges.removeMax();
            if(findParent(source).equals(findParent(destination))) break;
            if(!findParent(e.v1).equals(findParent(e.v2))) {
                union(e);
                path.add(e);
            }
        }
        return path;
    }

    private Vertex find(Vertex v) {
        Vertex parent = v.parent;
        if(parent.equals(v)) return parent;
        stack.add(v);
        return findParent(parent);
    }

    public Vertex findParent(Vertex v) {
        Vertex parent = find(v);
        for(Vertex v1: stack) {
            v1.parent = parent;
        }
        return parent;
    }

    public void union(Edge e) {
        Vertex v1 = findParent(e.v1);
        Vertex v2 = findParent(e.v2);

        if(v1.rank > v2.rank) v2.parent = v1;
        if(v1.rank.equals(v2.rank)) {
            v2.parent = v1;
            v1.rank++;
        }
        if(v1.rank < v2.rank) v1.parent = v2;

    }
}
