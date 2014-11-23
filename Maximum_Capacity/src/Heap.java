import java.util.ArrayList;

/**
 * Created by arunk on 11/21/14.
 */
public class Heap<T extends Comparable> {
    public ArrayList<T> heap;

    public Heap(ArrayList<T> heap) {
        this.heap = new ArrayList<T>(heap);
        heapify();
    }

    public Heap() {
        this.heap = new ArrayList<T>();
    }

    public T delete(T e) {
        int index = 0;
        for(T t: heap) {
            if(t.equals(e)) break;
            index++;
        }
        swap(index, heap.size()-1);
        T tmp = heap.remove(heap.size()-1);
        shiftDown(index);
        return tmp;
    }

    public T removeMax() {
        swap(0, heap.size()-1);
        T tmp = heap.remove(heap.size()-1);
        shiftDown(0);
        return tmp;
    }

    public void insert (T e) {
        heap.add(e);
        shiftUp(heap.size() -1);
    }

    private Integer getParentIndex(Integer child) {
        return (int) Math.ceil(((double) child )/2) - 1;
    }

    private void heapify() {
        for(int i = getParentIndex(heap.size()-1); i >= 0; i--) shiftDown(i);
    }

    private void shiftDown(Integer parent) {
        Integer lchild = 2*parent + 1;
        Integer rchild = 2*parent + 2;

        if(lchild >= heap.size() && rchild >= heap.size()) return;

        if(lchild < heap.size() && heap.get(parent).compareTo(heap.get(lchild)) == -1) {
            swap(lchild, parent);
            shiftDown(lchild);
        }

        if(rchild < heap.size() && heap.get(parent).compareTo(heap.get(rchild)) == -1) {
            swap(rchild, parent);
            shiftDown(rchild);
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
