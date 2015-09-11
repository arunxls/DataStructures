import java.util.ArrayList;

/**
 * Created by arunk on 11/21/14.
 */
public class Heap<T extends Comparable<T>> {
    public ArrayList<T> heap;

    //This constructor runs heapify
    public Heap(ArrayList<T> heap) {
        this.heap = new ArrayList<T>(heap);
        heapify();
    }

    //Normal constructor
    public Heap() {
        this.heap = new ArrayList<T>();
    }

    //Pop off the max element
    public T removeMax() {
        swap(0, heap.size()-1);
        T tmp = heap.remove(heap.size()-1);
        shiftDown(0);
        return tmp;
    }

    //Insert a new element
    public void insert (T e) {
        heap.add(e);
        shiftUp(heap.size() -1);
    }

    private Integer getParentIndex(Integer child) {
        return (int) Math.ceil(((double) child )/2) - 1;
    }

    private void heapify() {
        Integer N = getParentIndex(heap.size()-1);
        for(int i = N; i >= 0; i--)
            shiftDown(i);

    }

    private void shiftDown(Integer parent) {
        Integer lchild = 2*parent + 1;
        Integer rchild = 2*parent + 2;

        if(lchild >= heap.size() && rchild >= heap.size()) return;

        Integer max = parent;
        if(lchild < heap.size() && heap.get(parent).compareTo(heap.get(lchild)) == -1) {
            max = lchild;
        }

         if(rchild < heap.size() && heap.get(max).compareTo(heap.get(rchild)) == -1) {
             max = rchild;
        }

        if(!parent.equals(max)) {
            swap(max, parent);
            shiftDown(max);
        }
    }

    private void shiftUp(Integer child) {
        Integer parent = getParentIndex(child);
        if(parent < 0) return;

        if(heap.get(parent).compareTo(heap.get(child)) == -1) {
            swap(child, parent);
            shiftUp(parent);
        }
    }

    private void swap(Integer i, Integer j) {
        T tmp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, tmp);
    }
}
