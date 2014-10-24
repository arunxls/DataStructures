/**
 * Created by arunk on 10/11/14.
 */
public class Cell<T extends Comparable<T>> {
    public Node<T> child;
    public T key;

    public Cell() {}
    public Cell(Node<T> child) {
        this.child = child;
    }
    public Cell(T key) {
        this.key = key;
    }
    public Cell(Node<T> child, T key) {
        this.child = child;
        this.key = key;
    }

    public T print() {
        return key;
    }

    public Integer compareTo(Cell<T> cell) {
        return this.key.compareTo(cell.key);
    }
}
