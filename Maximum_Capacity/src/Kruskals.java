import java.util.ArrayList;

/**
 * Created by arunk on 11/21/14.
 */
public class Kruskals {
    Vertex source;
    Vertex destination;
    Heap<Edge> edges;
    Graph graph;
    ArrayList<Vertex> stack;

    //Clean the graph member
    public Kruskals(Vertex source, Vertex destination, Graph graph) {
        this.source      = source;
        this.destination = destination;
        this.edges       = new Heap<Edge>(graph.edges);
        this.graph       = graph;
        this.stack       = new ArrayList<Vertex>();
        graph.cleanCache();
    }

    //Run kruskals
    public void findMaximumCapacityPath() {
        while(edges.heap.size() != 0) {
            Edge e = edges.removeMax();
            if(findParent(source).equals(findParent(destination))) break;
            if(!findParent(e.v1).equals(findParent(e.v2))) {
                union(e);
            }
        }
    }

    //Called by findParent to move up the stack
    private Vertex find(Vertex v) {
        Vertex parent = v.parent;
        if(parent.equals(v)) return parent;
        stack.add(v);
        return find(parent);
    }

    //Finds the overall parent
    public Vertex findParent(Vertex v) {
        Vertex parent = find(v);
        for(Vertex v1: stack) {
            v1.parent = parent;
        }
        stack = new ArrayList<Vertex>();
        return parent;
    }

    //Unions two parents
    public void union(Edge e) {
        Vertex v1 = findParent(e.v1);
        Vertex v2 = findParent(e.v2);

        if(v1.rank > v2.rank) {
            v2.parent = v1;
        } else if(v1.rank.equals(v2.rank)) {
            v2.parent = v1;
            v1.rank++;
        } else {
            v1.parent = v2;
        }
    }
}
