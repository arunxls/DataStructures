import java.util.ArrayList;

/**
 * Created by arunk on 9/26/14.
 */
public class Node<T extends Comparable<T>>{

    private ArrayList<T> keys;
    private ArrayList<Node<T>> children;
    private Node<T> parent;

    public Node(ArrayList<T> keys, ArrayList<Node<T>> children, Node<T> parent) {
        this.keys       = keys;
        this.children   = children;
        this.parent     = parent;
    }

    public Boolean isRoot() {
        return this.parent == null;
    }

    public Boolean isLeaf() {
        return this.children.size() == 0;
    }

    public Boolean isEmpty() {
        return this.keys.size() == 0;
    }

    public Integer size() { return Integer.max(this.keys.size(), this.children.size()); }



    public Node<T> getChild(Integer position) {
        return children.get(position);
    }

    public Node<T> addMaxChild(Node<T> child) {
        this.children.add(child);
        return this;
    }

    public Node<T> addChild(Integer index, Node<T> child) {
        this.children.add(index, child);
        return this;
    }

    public Node<T> addChild(ArrayList<Node<T>> children) {
        for(Node<T> child : children) {
            this.children.add(child);
        }
        return this;
    }

    private Node<T> removeChild(int index) {
        Node<T> child = this.children.get(index);
        this.children.remove(index);
        return child;
    }

    public ArrayList<Node<T>> getChildren() {
        return children;
    }

    public Node<T> deleteChild(Integer index) {
        return this.removeChild(index);
    }

    public void removeMinChild() {

    }



    public ArrayList<T> getKeys() {
        return keys;
    }

    private Node<T> addKey(T element) {
        this.keys.add(element);
        return this;
    }

    public Node<T> addKey(Integer position, T element) {
        this.keys.add(position, element);
        return this;
    }

//    public Node<T> add

    public T maxKey() {
        return this.keys.get(this.keys.size() - 1);
    }

    public T minKey() {
        return this.keys.get(0);
    }

    public T removeKey(int index) {
        T key = this.keys.get(index);
        this.keys.remove(index);
        return key;
    }

    public Boolean containsKey(T element) {
        return this.keys.indexOf(element) != -1;
    }

    public Node<T> deleteKey(T element) {
        this.keys.remove(this.keys.indexOf(element));
        return this;
    }



    public void updateParentKey() {
        this.parent.keys.set(this.parent.children.indexOf(this),this.maxKey());
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }


    public Integer findPositionToInsert(T element) {
        Integer index = 0;
        for (T key : keys) {
            if(element.compareTo(key) <= 0) { break; }
            index++;
        }
        return index;
    }

    public Integer findPositionToSearch(T element) {
        Integer index = this.findPositionToInsert(element);
        if(index == this.size()) index--;
        return index;
    }



    public void print() {
        System.out.print(' ');
        for (T key : keys) {
            System.out.print(key);
            System.out.print('-');
        }
        System.out.print('|');
    }

    public Node<T> split(Integer start) {
        Node<T> sibling = new Node<T>(new ArrayList<T>(), new ArrayList<Node<T>>(), this.parent);

        Integer stop = this.size();
        for(int i = start; i < stop; i++) {
            sibling.addKey(this.removeKey(start));
            if(!this.isLeaf()) {
                Node<T> child = this.removeChild(start);
                sibling.addMaxChild(child);
                child.setParent(sibling);
            }
        }

        return sibling;
    }


    public Node<T> leftSibling() {
        if(this.isRoot()) {
            return null;
        } else {
            Integer index = this.getParent().getChildren().indexOf(this);
            if(index == 0) {
                return null;
            } else {
                return this.getParent().getChildren().get(index -1);
            }
        }
    }

    public Node<T> rightSibling() {
        if(this.isRoot()) {
            return null;
        } else {
            Integer index = this.getParent().getChildren().indexOf(this);
            if(index == (this.getParent().getChildren().size() -1)) {
                return null;
            } else {
                return this.getParent().getChildren().get(index + 1);
            }
        }
    }

    public Integer indexInParent() {
        return this.parent.getChildren().indexOf(this);
    }


}
