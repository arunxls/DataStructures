import java.util.*;

/**
 * Created by arunk on 11/14/14.
 */
public class Edge implements Comparable {
    final Vertex v1;
    final Vertex v2;
    Integer weight;

    public Edge(Vertex v2, Vertex v1) {
        this.v2 = v2;
        this.v1 = v1;
        this.weight = randInt(1,1000);
    }

    /**
     * Returns a pseudo-random number between min and max, inclusive.
     * The difference between min and max can be at most
     * <code>Integer.MAX_VALUE - 1</code>.
     *
     * @param min Minimum value
     * @param max Maximum value.  Must be greater than min.
     * @return Integer between min and max, inclusive.
     * @see java.util.Random#nextInt(int)
     */
    private static int randInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    @Override
    public int compareTo(Object o) {
        Edge other = (Edge) o;
        if(((this.v1 == other.v1) && (this.v2 == other.v2)) ||
                ((this.v1 == other.v2) && (this.v2 == other.v1))) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int hashCode() {
        int a = v1.index;
        int b = v2.index;
        return (((a + b)*(a + b + 1))/2) + b;
    }

    @Override
    public boolean equals(Object obj) {
        return this.hashCode() == (((Edge) obj).hashCode());
    }

    private Integer unique_hash() {
        int a = v1.index;
        int b = v2.index;
        return (((a + b)*(a + b + 1))/2) + b;
    }
}
