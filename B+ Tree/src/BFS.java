import java.util.LinkedList;

/**
 * Created by arunk on 10/9/14.
 */
public class BFS<T extends Comparable<T>> {
    Tree<T> tree;

    public BFS(Tree<T> tree) {
        this.tree = tree;
    }

    public void print() {
        LinkedList<Node<T>> q1 = new LinkedList<Node<T>>();
        if(!(tree.getRoot() == null)) q1.addLast(tree.getRoot());
        LinkedList<Node<T>> q2 = new LinkedList<Node<T>>();

        while (!listIsEmpty(q1) || !listIsEmpty(q2)) {
            System.out.println(' ');
            Node<T> node;
            while(!listIsEmpty(q1)) {
                node = printFirstNode(q1);
                if(!(node == null) && !node.isEmpty()) q2.addAll(node.getChildren());
            }
            System.out.println(' ');
            while(!listIsEmpty(q2)) {
                node = printFirstNode(q2);
                if(!(node == null) && !node.isEmpty()) q1.addAll(node.getChildren());
            }
        }
        System.out.println("\n");
    }

    private Boolean listIsEmpty(LinkedList<Node<T>> list) {
        return list.size() == 0;
    }

    private Node<T> printFirstNode(LinkedList<Node<T>> list) {
        Node<T> node = list.removeFirst();
        node.print();
        return node;
    }
}
