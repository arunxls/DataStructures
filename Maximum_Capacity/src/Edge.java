import java.util.*;

/**
 * Created by arunk on 11/14/14.
 */
public class Edge implements Comparable {
    final Vertex v1;
    final Vertex v2;
    final Integer weight;
    Integer status;

    public Edge(Vertex v2, Vertex v1) {
        this.v2 = v2; this.v1 = v1;
        this.weight = randInt(1,1000);
        status = 0;
    }

    private static int randInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    @Override
    public int compareTo(Object o) {
        Edge edge = (Edge) o;
        return this.weight > edge.weight ? 1 : (this.weight.equals(edge.weight) ? 0 : -1);
    }

    @Override
    public int hashCode() {
        int a = v1.index;
        int b = v2.index;
        //Cantor pairing function
        return (((a + b)*(a + b + 1))/2) + b;
    }

    @Override
    public boolean equals(Object obj) {
        return this.hashCode() == obj.hashCode();
    }

    public Boolean isFringe() {
        return status == 1;
    }

    public Boolean isTaken() {
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
}
