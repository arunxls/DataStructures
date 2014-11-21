import java.util.ArrayList;

/**
 * Created by arunk on 11/21/14.
 */
public class Heap {
    public ArrayList<Edge> heap;

    public Heap(ArrayList<Edge> heap) {
        this.heap = new ArrayList<Edge>(heap);

        for(int i = getParentIndex(heap.size()-1); i >= 0; i--) {
            heapify(i);
        }
    }

    public Edge max() {
        return heap.get(0);
    }

    public Edge removeMax() {
        Edge tmp = heap.get(0);
        heap.set(0, heap.get(heap.size()-1));
        heap.set(heap.size() - 1, tmp);
        //heapify();
        return tmp;
    }

    public void insert (Edge e) {
        heap.add(e);
        //heapify();
    }

    private Integer getParentIndex(Integer child) {
        return (int) Math.ceil(((double) child )/2) - 1;
    }

    private void heapify(Integer index) {
        Integer left_child = 2*index + 1;
        Integer right_child = 2*index + 2;

        if(left_child >= heap.size() && right_child >= heap.size()) return;

        if(left_child < heap.size() && heap.get(index).compareTo(heap.get(left_child)) == -1) {
            swap(left_child, index);
            heapify(left_child);
        }

        if(right_child < heap.size() && heap.get(index).compareTo(heap.get(right_child)) == -1){
            swap(right_child, index);
            heapify(right_child);
        }
    }

    private void swap(Integer i, Integer j) {
        Edge tmp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, tmp);
    }
}
