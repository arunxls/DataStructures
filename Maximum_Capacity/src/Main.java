import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Created by arunk on 11/14/14.
 */
public class Main {
    public static void main(String[] args) {
        int size;
        DecimalFormat numberFormat = new DecimalFormat("#.00");

        Scanner Keyboard = new Scanner(System.in);

        System.out.println("Enter the size of graph");
        size = Keyboard.nextInt();

        Graph g1 = new Graph(size); Graph g2 = new Graph(size);

        long startTime = System.nanoTime();
        g1.generateSparse(6);
        long endTime = System.nanoTime();

        System.out.println("Took " + numberFormat.format(((double)(endTime-startTime))/1000000000) + "s to generate sparse graph");

        startTime = System.nanoTime();
        g2.generateDense(0.2);
        endTime = System.nanoTime();

        System.out.println("Took " + numberFormat.format(((double)(endTime-startTime))/1000000000) + "s to generate dense graph");

        Heap heap = new Heap(g1.edges);
//        Integer c = heap.heap.size();
//        for(int i = 0; i < c; i++) {
//            System.out.println(heap.removeMax().weight);
//        }

    }
}
