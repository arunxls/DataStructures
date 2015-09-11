import java.util.ArrayList;

/**
 * Created by arunk on 11/21/14.
 */
public class Dijkstras {
    Vertex source;
    Vertex destination;
    Graph graph;

    //Constructor cleans the graph member
    public Dijkstras(Vertex source, Vertex destination, Graph graph) {
        this.source      = source;
        this.destination = destination;
        this.graph       = graph;
        graph.cleanCache();
    }

    //Call Dijkstra's using MinHeap
    public void findMaximumCapacityPathUsingHeap() {
        Heap<Vertex> vertices = new Heap<Vertex>();
        for(Vertex v : graph.vertices) v.setUnSeen();

        source.distance = 0;
        addVerticesToHeap(source, vertices);

        while(!(destination.isSeen() || vertices.heap.size() == 0)) {
            Vertex v = vertices.removeMax();
            addVerticesToHeap(v, vertices);
        }
    }

    //Add to the fringe set
    private void addVerticesToHeap(Vertex v, Heap<Vertex> vertices) {
        v.setSeen();
        for(Edge e : graph.vertices.get(v.index).edges) {
            Vertex v2 = getCorrespondingVertex(e, v);
            if(v2.isSeen()) continue;
            if(v2.isFringe() && (v2.distance < e.weight)) {
                v2.distance = e.weight;
                vertices.insert(v2);
                v2.parent = v;
            } else if(v2.isUnSeen()) {
                v2.distance = e.weight;
                vertices.insert(v2);
                v2.setFringe();
                v2.parent = v;
            }
        }
    }

    //Given an edge and a vertex, return the other end of edge
    private Vertex getCorrespondingVertex(Edge e, Vertex v1) {
        Vertex v2 = e.v1;
        if(v2.equals(v1)) v2 = e.v2;
        return v2;
    }

    //Call Dijkstra's to use arrays instead of Heaps
    public void findMaximumCapacityPathWithoutHeap() {
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        for(Vertex v : graph.vertices) v.setUnSeen();

        source.distance = 0;
        addVerticesToArray(source, vertices);

        while(!(destination.isSeen() || vertices.size() == 0)) {
            Vertex v = removeMaxFromArray(vertices);
            addVerticesToArray(v, vertices);
        }
    }

    //Add vertices to array
    private void addVerticesToArray(Vertex v, ArrayList<Vertex> vertices) {
        v.setSeen();
        for(Edge e : graph.vertices.get(v.index).edges) {
            Vertex v2 = getCorrespondingVertex(e, v);
            if(v2.isSeen()) continue;
            if(v2.isFringe() && (v2.distance < e.weight)) {
                v2.distance = e.weight;
                v2.parent = v;
            } else if(v2.isUnSeen()) {
                v2.distance = e.weight;
                vertices.add(v2);
                v2.setFringe();
                v2.parent = v;
            }
        }
    }

    //Since we can't just pop off the root
    //element as in a heap, we will have to scan the entire array
    public Vertex removeMaxFromArray(ArrayList<Vertex> vertices) {
        Integer max = -1;
        Integer i = -1;
        Integer index = -1;
        for(Vertex v: vertices) {
            i++;
            if(v.distance > max ) {
                max = v.distance;
                index = i;
            }
        }

        return vertices.remove((int) index);
    }
}
