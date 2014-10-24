import java.util.ArrayList;

/**
 * Created by arunk on 9/26/14.
 */
public class Tree<T extends Comparable<T>> {
    private Integer min_nodes;
    private Integer max_nodes;
    private Node<T> root;

    public Tree(Integer size) {
        max_nodes = size;
        min_nodes = (int) Math.ceil((float) size / (float) 2);

        System.out.println("Creating a (" + min_nodes + ", " + max_nodes + ") tree");
        root = newRoot();
    }

    public Node<T> getRoot() {
        return this.root;
    }

    public void insert(T element) {
        Cell<T> cell = new Cell<T>(element);
        add(findCell(cell), cell);
    }

    public void delete(T element) {
        Cell<T> cell = new Cell<T>(element);
        if(search(element)){
            remove(findCell(cell),cell);
        } else {
            System.out.println("The element '" + element + "' does not exist!");
        }
    }

    public Boolean search(T element) {
        Integer index = 0;
        Node<T> node = findCell(new Cell<T>(element));
        for(Cell<T> cell : node.getCells()) {
            if(cell.key.compareTo(element) == 0) { break; }
            index++;
        }
        return !(index.equals(node.size()));
    }

    public T min() {
        Node<T> node = this.root;
        while (!node.isLeaf()) {
            node = node.minCell().child;
        }
        return node.minCell().key;
    }

    public T max() {
        Node<T> node = this.root;
        while (!node.isLeaf()) {
            node = node.maxCell().child;
        }
        return node.maxCell().key;
    }

    private void add(Node<T> node, Cell<T> cell) {
        if(node.addCell(findPositionToInsert(node, cell), cell).size() > max_nodes) {
            reportAddToParent(node, split(node, min_nodes));
        }
    }

    private void reportAddToParent(Node<T> sibling1, Node<T> sibling2) {
        Node<T> parent = sibling1.getParent();
        if(parent == null) {
            // We need a new root
            parent = newRoot();
            add(parent,new Cell<T>(sibling1));
            this.root = parent;
            sibling1.setParent(this.root);
            sibling2.setParent(this.root);
        }
        updateParent(sibling1);
        add(parent,new Cell<T>(sibling2, sibling2.maxCell().key));
    }

    private void updateParent(Node<T> node) {
        node.getParent().getCell(getIndexInParent(node)).key = node.maxCell().key;
    }

    private Integer getIndexInParent(Node<T> node) {
        if (node.isRoot()) {
            return -1;
        } else {
            Integer index = 0;
            for (Cell<T> cell : node.getParent().getCells()) {
                if ((cell.child) == node) break;
                index++;
            }
            return index;
        }
    }

    private Node<T> newRoot() {
        return new Node<T>(null);
    }

    private Node<T> findCell(Cell<T> cell) {
        Node<T> node = this.root;
        while(!node.isLeaf()) {
            node = node.getCell(findPositionToSearch(node, cell)).child;
        }
        return node;
    }

    private void remove(Node<T> node, Cell<T> cell) {
        node.removeCell(findPositionToInsert(node,cell));
        if(node.isRoot() && !node.isLeaf() && !node.isEmpty() && (getChildren(node).size()) < min_nodes) {
            // In this case, we promote the combination of all children to be the root
            Node<T> new_root = newRoot();
            for(Node<T> child : getChildren(node)) {
                for(Cell<T> c : child.getCells()) {
                    new_root.addMaxCell(c);
                    c.child.setParent(new_root);
                }
            }
            this.root = new_root;
        } else if(!node.isRoot() && !node.isEmpty() && node.size() < min_nodes) {
            // 1. Try to borrow max from the left sibling if left sibling > min nodes
            //    Update parentKey in this case since max for left sibling changes
            // 2. Try to borrow min from the right sibling if right sibling > min nodes
            //    Update parentKey in this case since max for node changes
            // -------------------------------------------------------------------------
            // Both siblings have minimum number of children
            // -------------------------------------------------------------------------
            // 3. Try to merge with the right sibling - since min + (< min) cannot be greater
            //    than maximum, we will not need to split. Update parent with delete Key request
            // 4. Try to merge with the left sibling - since min + (< min) cannot be greater
            //    than maximum, we will not need to split. Update parent with delete key request
            // In both these cases, deleteKey will need to be called recursively since parent may be
            // left will < min children.
            //

            Node<T> left_sibling = leftSibling(node);
            Node<T> right_sibling = rightSibling(node);

            if(!(left_sibling == null) && (left_sibling.size() > min_nodes)) {
                node.addMinCell(left_sibling.removeCell(left_sibling.size()-1));
                updateParent(left_sibling);
            } else if(!(right_sibling == null) && (right_sibling.size() > min_nodes)) {
                node.addMaxCell(right_sibling.removeCell(0));
                updateParent(node);
            } else if (!(right_sibling == null)) {
                merge(right_sibling, node);
                remove(node.getParent(),right_sibling.minCell());
            } else if (!(left_sibling == null)) {
                merge(left_sibling, node);
                remove(node.getParent(),left_sibling.maxCell());
                updateParent(left_sibling);
            } else {
                // Throw exception!
                Node<T> exception = null;
                exception.getParent();
            }
        }
    }

    private void merge(Node<T> node1, Node<T> node2) {
        Cell<T> cell = new Cell<T>();
        while(!node2.isEmpty()) {
            cell = node2.removeMaxCell();
            add(node1,cell);
            if(cell.child != null) cell.child.setParent(node1);
        }
        if(cell.child != null) updateParent(cell.child);
    }

    private Integer findPositionToInsert(Node<T> node, Cell<T> cell) {
        Integer index = 0;
        for (Cell<T> c : node.getCells()) {
            if(cell.compareTo(c) <= 0) { break; }
            index++;
        }
        return index;
    }

    private Integer findPositionToSearch(Node<T> node, Cell<T> cell) {
        Integer index = this.findPositionToInsert(node, cell);
        if(index.equals(node.size())) index--;
        return index;
    }

    private Node<T> split(Node<T> node, Integer start) {
        Node<T> sibling = new Node<T>(node.getParent());

        Integer stop = node.size();
        for(int i = start; i < stop; i++) {
            Cell<T> cell = node.removeCell(start);
            sibling.addMaxCell(cell);
            if(!node.isLeaf()) { cell.child.setParent(sibling); }
        }
        return sibling;
    }

    public ArrayList<Node<T>> getChildren(Node<T> node) {
        ArrayList<Node<T>> nodes = new ArrayList<Node<T>>();
        for(Cell<T> cell : node.getCells()) {
            if(cell.child != null) nodes.add(cell.child);
        }
        return nodes;
    }

    private Node<T> leftSibling(Node<T> node) {
        Integer index = getIndexInParent(node);
        if(index <= 0){
            return null;
        } else {
            return node.getParent().getCell(index -1).child;
        }
    }

    private Node<T> rightSibling(Node<T> node) {
        Integer index = getIndexInParent(node);
        if(index == (node.getParent().size() -1)){
            return null;
        } else {
            return node.getParent().getCell(index + 1).child;
        }
    }
}
