import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by arunk on 11/14/14.
 */
public class Main {
    public static void main(String[] args) {
        int size;
        DecimalFormat numberFormat = new DecimalFormat("#.00000");

        Scanner Keyboard = new Scanner(System.in);

        System.out.println("------------------------");
        System.out.println("Enter the size of graph");
        size = Keyboard.nextInt();

        int iteration = 0;
        for(int i = 0; i < 5; i++ ) {
            for(int j = 0; j < 10; j++) {
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

                startTime = System.nanoTime();
                new Dijkstras(g1.vertices.get(start), g1.vertices.get(end), g1).findMaximumCapacityPathWithoutHeap();
                endTime = System.nanoTime();
                System.out.println("\t\tTook " + numberFormat.format(((double)(endTime-startTime))/1000000000) + "s to run Djikstra's without heap on sparse graph");


                System.out.println("\t------ Running against dense graph ------");

                startTime = System.nanoTime();
                new Kruskals(g2.vertices.get(start), g2.vertices.get(end), g2).findMaximumCapacityPath();
                endTime = System.nanoTime();
                System.out.println("\t\tTook " + numberFormat.format(((double)(endTime-startTime))/1000000000) + "s to run Kruskal's on dense graph");

                startTime = System.nanoTime();
                new Dijkstras(g2.vertices.get(start), g2.vertices.get(end), g2).findMaximumCapacityPathUsingHeap();
                endTime = System.nanoTime();
                System.out.println("\t\tTook " + numberFormat.format(((double)(endTime-startTime))/1000000000) + "s to run Djikstra's on dense graph");

                startTime = System.nanoTime();
                new Dijkstras(g2.vertices.get(start), g2.vertices.get(end), g2).findMaximumCapacityPathWithoutHeap();
                endTime = System.nanoTime();
                System.out.println("\t\tTook " + numberFormat.format(((double)(endTime-startTime))/1000000000) + "s to run Djikstra's without heap on dense graph");
            }
        }
    }

    private static int randInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }
}