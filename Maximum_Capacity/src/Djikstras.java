import java.util.ArrayList;

/**
 * Created by arunk on 11/21/14.
 */
public class Djikstras {
    Vertex source;
    Vertex destination;
    Graph graph;
    Heap<Vertex> vertices;

    public Djikstras(Vertex source, Vertex destination, Graph graph) {
        this.source = source;
        this.destination = destination;
        this.graph = graph;
        graph.cleanCache();
        vertices = new Heap<Vertex>();
    }

    public ArrayList<Edge> findMaximumCapacityPath() {
        ArrayList<Edge> path = new ArrayList<Edge>();

        for(Vertex v : graph.vertices) {
            v.setUnSeen();
        }

        source.distance = 0;
        addVerticesToHeap(source);

        while(!(destination.isSeen() || vertices.heap.size() == 0)) {
            Vertex v = vertices.removeMax();
            addVerticesToHeap(v);
        }
        return path;
    }

    private void addVerticesToHeap(Vertex v) {
        v.setSeen();
        for(Edge e : graph.vertices.get(v.index).edges) {
            Vertex v2 = getCorrespondingVertex(e, v);
            if(v2.isSeen()) continue;
            if(v2.isFringe() && (v2.distance < e.weight)) {
                vertices.delete(v2);
                updateDistance(v, v2, e.weight);
                vertices.insert(v2);
                v2.parent = v;
            } else if(v2.isUnSeen()) {
                updateDistance(v, v2, e.weight);
                vertices.insert(v2);
                v2.setFringe();
                v2.parent = v;
            }
        }
    }

    private Vertex getCorrespondingVertex(Edge e, Vertex v1) {
        Vertex v2 = e.v1;
        if(v2.equals(v1)) v2 = e.v2;
        return v2;
    }

    private void updateDistance(Vertex v1, Vertex v2, Integer weight) {
        v2.distance = weight;
    }
}
