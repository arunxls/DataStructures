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
        addKey(findNode(element), element);
    }

    public void delete(T element) {
        if(search(element)){
            deleteKey(findNode(element),element);
        } else {
            System.out.println("The element '" + element + "' does not exist!");
        }
    }

    public Boolean search(T element) {
        return findNode(element).containsKey(element);
    }

    public T min() {
        ArrayList<T> keys = findNode(this.root.minKey()).getKeys();
        return keys.get(0);
    }

    public T max() {
        ArrayList<T> keys = findNode(this.root.maxKey()).getKeys();
        return keys.get(keys.size() - 1);
    }

    private void addKey(Node<T> node, T element) {
        if(node.addKey(node.findPositionToInsert(element), element).size() > max_nodes) {
            reportAddToParent(node, node.split(min_nodes));
        }
    }

    private void reportAddToParent(Node<T> sibling1, Node<T> sibling2) {
        Node<T> parent = sibling1.getParent();
        if(parent == null) {
            // We need a new root
            parent = newRoot();
            parent.addMaxChild(sibling1);
            addKey(parent,sibling1.maxKey());
            this.root = parent;
            sibling1.setParent(this.root);
            sibling2.setParent(this.root);
        }
        sibling1.updateParentKey();
        parent.addChild(parent.findPositionToInsert(sibling2.maxKey()),sibling2);
        addKey(parent,sibling2.maxKey());
    }

    private Node<T> newRoot() {
        return new Node<T>(new ArrayList<T>(), new ArrayList<Node<T>>(), null);
    }

    private Node<T> findNode(T element) {
        Node<T> node = this.root;
        while(!node.isLeaf()) {
            node = node.getChild(node.findPositionToSearch(element));
        }
        return node;
    }

    private void deleteKey(Node<T> node, T element) {
        if(node.isRoot()) {
            node.removeKey(node.findPositionToInsert(element));
            if(!node.isLeaf() && (node.getChildren().size()) < min_nodes) {
                // In this case, we promote the combination of all children to be the root
                Node<T> new_root = newRoot();
                for(Node<T> child : node.getChildren()) {
                    new_root.addChild(child.getChildren());
//                    new_root.addKey(child.getKeys());
                }
            }
        } else {
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
        }
    }
//
//    private void borrowFromMin(Node<T> sibling1, Node<T> sibling2) {
//        sibling1.addMaxChild(sibling2.removeMinChild());
//        addKey(sibling1, sibling2.removeMinKey());
//
//    }
}
