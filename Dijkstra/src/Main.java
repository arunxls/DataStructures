import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by arunk on 10/14/14.
 */
public class Main {

    public static void main(String[] args) {
        ArrayList<ArrayList<Cell>> adjacency_list = new ArrayList<ArrayList<Cell>>();

        Comparator<Cell> comparator = new CellComparator();
        PriorityQueue<Cell> queue = new PriorityQueue<Cell>(10, comparator);
        queue.add("short");
        queue.add("very long indeed");
        queue.add("medium");
        while (queue.size() != 0)
        {
            System.out.println(queue.remove());
        }

    }
}
