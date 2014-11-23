import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by arunk on 11/14/14.
 */
public class Main {
    public static void main(String[] args) {
        int size;
        ArrayList<Edge> path;
        DecimalFormat numberFormat = new DecimalFormat("#.00000");

        Scanner Keyboard = new Scanner(System.in);

        System.out.println("Enter the size of graph");
        size = Keyboard.nextInt();

        Graph g1 = new Graph(size); Graph g2 = new Graph(size);

        long startTime = System.nanoTime();
//        g1.generateSparse(6);
        long endTime = System.nanoTime();

        System.out.println("Took " + numberFormat.format(((double)(endTime-startTime))/1000000000) + "s to generate sparse graph");

        startTime = System.nanoTime();
//        g2.generateDense(0.2);
        endTime = System.nanoTime();

        System.out.println("Took " + numberFormat.format(((double)(endTime-startTime))/1000000000) + "s to generate dense graph");


        Integer start = randInt(0, size-1);
        Integer end = randInt(0, size-1);
        while(end.equals(start)) {
            end = randInt(0,size);
        }

//        System.out.println("Picked " + start + " as source and " + end + " as destination vertex");
//
//        startTime = System.nanoTime();
//        path = new Kruskals(g1.vertices.get(start), g1.vertices.get(end), g1).findMaximumCapacityPath();
//        endTime = System.nanoTime();
//        System.out.println("Took " + numberFormat.format(((double)(endTime-startTime))/1000000000) + "s to run Kruskal's on sparse graph");
//
//        startTime = System.nanoTime();
//        path = new Djikstras(g1.vertices.get(start), g1.vertices.get(end), g1).findMaximumCapacityPath();
//        endTime = System.nanoTime();
//        System.out.println("Took " + numberFormat.format(((double)(endTime-startTime))/1000000000) + "s to run Djikstra's on sparse graph");
//
//        startTime = System.nanoTime();
//        path = new Kruskals(g2.vertices.get(start), g2.vertices.get(end), g2).findMaximumCapacityPath();
//        endTime = System.nanoTime();
//        System.out.println("Took " + numberFormat.format(((double)(endTime-startTime))/1000000000) + "s to run Kruskal's on dense graph");
//
//        startTime = System.nanoTime();
//        path = new Djikstras(g2.vertices.get(start), g2.vertices.get(end), g2).findMaximumCapacityPath();
//        endTime = System.nanoTime();
//        System.out.println("Took " + numberFormat.format(((double)(endTime-startTime))/1000000000) + "s to run Djikstra's on dense graph");

        Graph g = fixed();
        start = 0;
        end = 2;
        startTime = System.nanoTime();
        path = new Djikstras(g.vertices.get(start), g.vertices.get(end), g).findMaximumCapacityPath();
        path = new Kruskals(g.vertices.get(start), g.vertices.get(end), g).findMaximumCapacityPath();
        endTime = System.nanoTime();
        System.out.println("Took " + numberFormat.format(((double)(endTime-startTime))/1000000000) + "s to run Djikstra's on dense graph");
    }

    private static int randInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    private static Graph fixed() {
        Graph g = new Graph(6);
        g.vertices = new ArrayList<Vertex>();
        Vertex v1 = new Vertex(0);
        Vertex v2 = new Vertex(1);
        Vertex v3 = new Vertex(2);
        Vertex v4 = new Vertex(3);
        Vertex v5 = new Vertex(4);
        Vertex v6 = new Vertex(5);
        Vertex v7 = new Vertex(6);


        Edge e1 = new Edge(v1, v3, 11);
        Edge e2 = new Edge(v1, v4, 8);
        Edge e3 = new Edge(v1, v2, 40);
        Edge e4 = new Edge(v2, v4, 29);
        Edge e5 = new Edge(v2, v6, 17);
        Edge e6 = new Edge(v6, v7, 53);
        Edge e7 = new Edge(v6, v5, 40);
        Edge e8 = new Edge(v5, v7, 15);
        Edge e9 = new Edge(v5, v3, 46);
        Edge e10 = new Edge(v3, v4, 3);
        Edge e11 = new Edge(v4, v6, 31);



        v1.edges.add(e1);
        v1.edges.add(e2);
        v1.edges.add(e3);

        v2.edges.add(e4);
        v2.edges.add(e3);
        v2.edges.add(e5);

        v3.edges.add(e1);
        v3.edges.add(e10);
        v3.edges.add(e9);

        v4.edges.add(e10);
        v4.edges.add(e11);
        v4.edges.add(e2);
        v4.edges.add(e4);


        v5.edges.add(e9);
        v5.edges.add(e8);
        v5.edges.add(e7);

        v6.edges.add(e5);
        v6.edges.add(e11);
        v6.edges.add(e7);
        v6.edges.add(e6);

        v7.edges.add(e8);
        v7.edges.add(e6);


        g.edges.add(e1);
        g.edges.add(e2);
        g.edges.add(e3);
        g.edges.add(e4);
        g.edges.add(e5);
        g.edges.add(e6);
        g.edges.add(e7);
        g.edges.add(e8);
        g.edges.add(e9);
        g.edges.add(e10);
        g.edges.add(e11);


        g.vertices.add(v1);
        g.vertices.add(v2);
        g.vertices.add(v3);
        g.vertices.add(v4);
        g.vertices.add(v5);
        g.vertices.add(v6);
        g.vertices.add(v7);

        return g;
    }
}

