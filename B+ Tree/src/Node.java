import java.util.ArrayList;

/**
 * Created by arunk on 9/26/14.
 */
public class Node<T extends Comparable<T>>{
    private ArrayList<Cell<T>> cells;
    private Node<T> parent;

    public Node(Node<T> parent) {
        this.cells = new ArrayList<Cell<T>>();
        this.parent = parent;
    }

    public Boolean isRoot() {
        return this.parent == null;
    }

    public Boolean isLeaf() {
        return (this.cells.size() == 0) || (this.cells.get(0).child == null);
    }

    public Boolean isEmpty() {
        return this.cells.size() == 0;
    }

    public Integer size() { return this.cells.size(); }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public Cell<T> getCell(Integer index) {
        return this.cells.get(index);
    }

    public Node<T> addMaxCell(Cell<T> cell) {
        return this.addCell(this.cells.size(), cell);
    }

    public Node<T> addMinCell(Cell<T> cell) {
        return this.addCell(0, cell);
    }

    public Node<T> addCell(Integer index, Cell<T> cell) {
        this.cells.add(index, cell);
        return this;
    }

    public Cell<T> removeCell(int index) {
        Cell<T> cell = this.cells.get(index);
        this.cells.remove(index);
        return cell;
    }

    public Cell<T> removeMaxCell() {
        return this.removeCell(this.size()-1);
    }

    public ArrayList<Cell<T>> getCells() {
        return this.cells;
    }

    public Cell<T> maxCell() {
        return this.cells.get(this.cells.size() -1);
    }

    public Cell<T> minCell() {
        return this.cells.get(0);
    }

    public void print() {
        System.out.print(' ');
        for (Cell<T> cell : this.cells) {
            System.out.print(cell.print());
            System.out.print('-');
        }
        System.out.print('|');
    }
}
