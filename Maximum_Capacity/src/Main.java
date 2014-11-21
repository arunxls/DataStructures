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
//        Heap heap = new Heap<Integer>(size);

        Graph g1 = new Graph(size); Graph g2 = new Graph(size);

        long startTime = System.nanoTime();
        g1.generateSparse(6);
        long endTime = System.nanoTime();

        System.out.println("Took " + numberFormat.format(((double)(endTime-startTime))/1000000000) + "s to generate sparse graph");

        startTime = System.nanoTime();
        g2.generateDense(0.2);
        endTime = System.nanoTime();

        System.out.println("Took " + numberFormat.format(((double)(endTime-startTime))/1000000000) + "s to generate dense graph");
    }
}
