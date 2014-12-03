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

        System.out.println("------------------------");
        System.out.println("Enter the size of graph");
        size = Keyboard.nextInt();

        int iteration = 0;
        for(int i = 0; i < 5; i++ ) {
            for(int j = 0; j < 5; j++) {
                System.out.println("\n-------------- Starting Iteration " + iteration++ + " --------------");
                Graph g1 = new Graph(size); Graph g2 = new Graph(size);

                long startTime = System.nanoTime();
                g1.generateSparse(6);
                long endTime = System.nanoTime();

                System.out.println("\tTook " + numberFormat.format(((double)(endTime-startTime))/1000000000) + "s to generate sparse graph");

                startTime = System.nanoTime();
                g2.generateDense(0.2);
                endTime = System.nanoTime();

                System.out.println("\tTook " + numberFormat.format(((double)(endTime-startTime))/1000000000) + "s to generate dense graph");


                Integer start = randInt(0, size-1);
                Integer end = randInt(0, size-1);
                while(end.equals(start)) {
                    end = randInt(0,size);
                }

                System.out.println("\tPicked " + start + " as source and " + end + " as destination vertex");

                System.out.println("\t------ Running against sparse graph ------");

                startTime = System.nanoTime();
                new Kruskals(g1.vertices.get(start), g1.vertices.get(end), g1).findMaximumCapacityPath();
                endTime = System.nanoTime();
                System.out.println("\t\tTook " + numberFormat.format(((double)(endTime-startTime))/1000000000) + "s to run Kruskal's on sparse graph");

                startTime = System.nanoTime();
                new Dijkstras(g1.vertices.get(start), g1.vertices.get(end), g1).findMaximumCapacityPathUsingHeap();
                endTime = System.nanoTime();
                System.out.println("\t\tTook " + numberFormat.format(((double)(endTime-startTime))/1000000000) + "s to run Djikstra's on sparse graph");
                getDjikstraParent(g1.vertices.get(end), g1, g1.vertices.get(start));

                startTime = System.nanoTime();
                new Dijkstras(g1.vertices.get(start), g1.vertices.get(end), g1).findMaximumCapacityPathWithoutHeap();
                endTime = System.nanoTime();
                System.out.println("\t\tTook " + numberFormat.format(((double)(endTime-startTime))/1000000000) + "s to run Djikstra's without heap on sparse graph");
                getDjikstraParent(g1.vertices.get(end), g1, g1.vertices.get(start));


                System.out.println("\t------ Running against dense graph ------");

                startTime = System.nanoTime();
                new Kruskals(g2.vertices.get(start), g2.vertices.get(end), g2).findMaximumCapacityPath();
                endTime = System.nanoTime();
                System.out.println("\t\tTook " + numberFormat.format(((double)(endTime-startTime))/1000000000) + "s to run Kruskal's on dense graph");

                startTime = System.nanoTime();
                new Dijkstras(g2.vertices.get(start), g2.vertices.get(end), g2).findMaximumCapacityPathUsingHeap();
                endTime = System.nanoTime();
                System.out.println("\t\tTook " + numberFormat.format(((double)(endTime-startTime))/1000000000) + "s to run Djikstra's on dense graph");
                getDjikstraParent(g2.vertices.get(end), g2, g2.vertices.get(start));

                startTime = System.nanoTime();
                new Dijkstras(g2.vertices.get(start), g2.vertices.get(end), g2).findMaximumCapacityPathWithoutHeap();
                endTime = System.nanoTime();
                System.out.println("\t\tTook " + numberFormat.format(((double)(endTime-startTime))/1000000000) + "s to run Djikstra's without heap on dense graph");
                getDjikstraParent(g2.vertices.get(end), g2, g2.vertices.get(start));
            }
        }



//        Graph g = fixed();
//        Integer start = 0;
//        Integer end = 2;
//        long startTime = System.nanoTime();
//        new Dijkstras(g.vertices.get(start), g.vertices.get(end), g).findMaximumCapacityPathUsingHeap();
//        getDjikstraParent(g.vertices.get(end), g, g.vertices.get(start));
//        new Dijkstras(g.vertices.get(start), g.vertices.get(end), g).findMaximumCapacityPathWithoutHeap();
//        getDjikstraParent(g.vertices.get(end), g, g.vertices.get(start));
//        new Kruskals(g.vertices.get(start), g.vertices.get(end), g).findMaximumCapacityPath();
//        long endTime = System.nanoTime();
//        System.out.println("Took " + numberFormat.format(((double)(endTime-startTime))/1000000000) + "s to run Djikstra's on dense graph");
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
        Edge e3 = new Edge(v1, v2, 41);
        Edge e4 = new Edge(v2, v4, 35);
        Edge e5 = new Edge(v2, v6, 17);
        Edge e6 = new Edge(v6, v7, 53);
        Edge e7 = new Edge(v6, v5, 40);
        Edge e8 = new Edge(v5, v7, 50);
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

    private static void getDjikstraParent(Vertex destination, Graph graph, Vertex source) {
        Integer min = 100000;
        Vertex v = destination;
        while(!v.equals(source)) {
            min = Math.min(min, graph.getEdgeWeight(v, v.parent));
            v = v.parent;
        }
        System.out.println("Bottleneck is " + min);
    }
}