import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by arunk on 11/14/14.
 */
public class Graph {
    ArrayList<Vertex> vertices;
    ArrayList<Vertex> vertices_cache;
    ArrayList<Edge> edges;
    Integer size;

    public Graph(Integer size) {
        this.size = size;
        vertices = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
        for(int i = 0; i < size; i++) vertices.add(i, new Vertex(i));
    }

    public void generateSparse(int degree) {
        vertices_cache = new ArrayList<Vertex>(vertices);
        for(int j=0; j< degree; j++) {
            for(Vertex v: vertices_cache) {
                if(v.degree < degree) {
                    Vertex v2;
                    while(true) {
                        v2 = getRandomVertexNotInSet(v, 0, vertices_cache.size() -1);
                        if(v2 == null || v2.edges.size() < degree) break;
                    }
                    addEdge(v,v2);
                }
            }

            ArrayList<Vertex> temp = new ArrayList<Vertex>();
            for(Vertex v: vertices_cache) if(v.degree < degree) temp.add(v);
            vertices_cache = temp;
        }

        for(Vertex v : vertices) {
//            if(v.degree != degree) System.out.println("Error! Vertex " + v.index + " has degree " + v.degree);
            if(!(v.edgeInSet(vertices.get(vertices.get((v.index+1) % size).index))) || vertices.get((v.index+1) % size).edgeInSet(vertices.get(v.index))) {
                addEdge(v, vertices.get((v.index+1) % size));
            }
            v.clearCache();
        }
    }

    public void generateDense(double percent) {
        HashSet<Edge> edges_cache = new HashSet<Edge>();
        for(int i=0; i < size; i++) {
            for(int j=0; j< size; j++) {
                if(Math.random() < (percent/2)) {
                    Edge e = new Edge(vertices.get(i), vertices.get(j));
                    if(!edges_cache.contains(e)) {
                        vertices.get(i).edges.add(e); vertices.get(j).edges.add(e);
                        vertices.get(i).degree++; vertices.get(j).degree++;
                        edges.add(e);
                        edges_cache.add(e);
                    }
                }
            }
            Edge e = new Edge(vertices.get(i), vertices.get((i+1)%size));
            if(!edges_cache.contains(e)) {
                vertices.get(i).edges.add(e); vertices.get((i+1)%size).edges.add(e);
                vertices.get(i).degree++; vertices.get((i+1)%size).degree++;
                edges.add(e);
                edges_cache.add(e);
            }
        }
//        int count = 0;
//        for (Vertex v1 : vertices)
//            count += v1.degree;
//
//        System.out.println("Degree density is " + ((double)count/(size*size)));
    }

    public void addEdge(Vertex v1, Vertex v2) {
        Edge e = new Edge(v1, v2);
        v1.edges_cache.add(e);
        v1.edges.add(e); v2.edges.add(e);
        v1.degree++; v2.degree++;
        edges.add(e);
    }

    private Vertex getRandomVertexNotInSet(Vertex v, int min, int max) {
        Integer index;
        int i = 0;
        while (true) {
            i++;
            if(i > 10000) return null; //break if we can't find random vertex after lots of tries
            index = randInt(min, max);
            Vertex v2 = vertices_cache.get(index);
            if(v2.index.equals(v.index)) continue;
            if(!(v.edgeInSet(vertices_cache.get(index)) || v2.edgeInSet(vertices_cache.get(index)))) break;
        }
        return vertices_cache.get(index);
    }

    private static int randInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    public Integer getEdgeWeight(Vertex v1, Vertex v2) {
        Edge e1 = new Edge(v1, v2);
        for(Edge e2 : edges) {
            if(e1.equals(e2)) e1=e2;
        }
        return e1.weight;
    }

    public void cleanCache() {
        for(Vertex v: vertices) {
            v.parent = v;
            v.rank = 0;
            v.distance = -1;
        }
    }
}
