import java.util.ArrayList;
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
                        if(v2.edges.size() < degree) break;
                    }
                    addEdge(v,v2);
                }
            }

            ArrayList<Vertex> temp = new ArrayList<Vertex>();
            for(Vertex v: vertices_cache) if(v.degree < degree) temp.add(v);
            vertices_cache = temp;
        }

        for(Vertex v : vertices) {
            v.clearCache();
            if(v.degree != degree) System.out.println("Error! Vertex " + v.index + " has degree " + v.degree);
        }
    }

    public void generateDense(double percent) {
        for(int i = 0; i < size; i++) {
            ArrayList<Integer> cache = new ArrayList<Integer>();
            for(int j = 0; j < size; j++) cache.add(j);

            Vertex v1 = vertices.get(i);
            for (int j = 0; j < size; j++)
                if(Math.random() < (percent/2))
                    addEdge(v1,getRandomVertexNotInSetFromRange(v1,cache));
            v1.clearCache();
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
        while (true) {
            index = randInt(min, max);
            Vertex v2 = vertices_cache.get(index);
            if(v2.index.equals(v.index)) continue;
            if(!(v.edgeInSet(vertices_cache.get(index)) || v2.edgeInSet(vertices_cache.get(index)))) break;
        }
        return vertices_cache.get(index);
    }

    private Vertex getRandomVertexNotInSetFromRange(Vertex v, ArrayList<Integer> range) {
        Integer index;
        while (true) {
            index = range.remove(randInt(0, range.size() - 1));
            Vertex v2 = vertices.get(index);
            if (v2.index.equals(v.index)) continue;
            if (!v.edgeInSet(v2)) break;
        }
        return vertices.get(index);
    }

    private static int randInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }
}
