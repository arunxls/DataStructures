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
        g1.generateSparse(6);
        long endTime = System.nanoTime();

        System.out.println("Took " + numberFormat.format(((double)(endTime-startTime))/1000000000) + "s to generate sparse graph");

        startTime = System.nanoTime();
//        g2.generateDense(0.2);
        endTime = System.nanoTime();

        System.out.println("Took " + numberFormat.format(((double)(endTime-startTime))/1000000000) + "s to generate dense graph");


        Integer start = randInt(0, size);
        Integer end = randInt(0, size);
        while(end == start) {
            end = randInt(0,size);
        }

        System.out.println("Picked " + start + " as source and " + end + " as destination vertex");

        startTime = System.nanoTime();
        path = new Kruskals(g1.vertices.get(start), g1.vertices.get(end), g1).findMaximumCapacityPath();
        endTime = System.nanoTime();
        System.out.println("Took " + numberFormat.format(((double)(endTime-startTime))/1000000000) + "s to run Kruskal's on sparse graph");

        startTime = System.nanoTime();
        path = new Djikstras(g1.vertices.get(start), g1.vertices.get(end), g1).findMaximumCapacityPath();
        endTime = System.nanoTime();
        System.out.println("Took " + numberFormat.format(((double)(endTime-startTime))/1000000000) + "s to run Kruskal's on sparse graph");

        startTime = System.nanoTime();
        path = new Kruskals(g2.vertices.get(start), g2.vertices.get(end), g2).findMaximumCapacityPath();
        endTime = System.nanoTime();
        System.out.println("Took " + numberFormat.format(((double)(endTime-startTime))/1000000000) + "s to run Kruskal's on dense graph");
    }

    private static int randInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }
}

