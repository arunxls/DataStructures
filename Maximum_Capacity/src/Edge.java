import java.util.*;

/**
 * Created by arunk on 11/14/14.
 */
public class Edge implements Comparable<Edge> {
    final Vertex v1;
    final Vertex v2;
    final Integer weight;

    public Edge(Vertex v2, Vertex v1) {
        this.v2 = v2; this.v1 = v1;
        this.weight = randInt(1,1000);
    }

    public Edge(Vertex v2, Vertex v1, int weight) {
        this.v2 = v2; this.v1 = v1;
        this.weight = weight;
    }

    private static int randInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    @Override
    public int compareTo(Edge edge) {
        return this.weight > edge.weight ? 1 : (this.weight.equals(edge.weight) ? 0 : -1);
    }

    @Override
    public int hashCode() {
        int a = v1.index;
        int b = v2.index;
        int tmp = b;
        if(a > b) {
            b = a;
            a = tmp;
        }
        //Cantor pairing function
        return (((a + b)*(a + b + 1))/2) + b;
    }

    @Override
    public boolean equals(Object obj) {
        return this.hashCode() == obj.hashCode();
    }
}
