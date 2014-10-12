import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int size;
        Scanner Keyboard = new Scanner(System.in);

        System.out.println("Enter the size of the B+ tree you want to construct!");
        size = Keyboard.nextInt();

        Tree<Integer> tree = new Tree<Integer>(size);

        while(true) {
            System.out.println("Enter the operation you would like to perform (or 'help')");

            String opt = Keyboard.next();

            if(opt.equals("i")){
                int value = Keyboard.nextInt();
                System.out.println("insert " + value);
                tree.insert(value);
            } else if(opt.equals("d")){
                int value = Keyboard.nextInt();
                System.out.println("delete " + value);
                tree.delete(value);
            } else if(opt.equals("s")){
                int value = Keyboard.nextInt();
                System.out.println("search for " + value);
                System.out.println(value + " " + (tree.search(value) ? "exists" : "does not exist"));
            } else if(opt.equals("min")){
                System.out.println("min = " + tree.min());
            } else if(opt.equals("max")){
                System.out.println("max = " + tree.max());
            } else if(opt.equals("quit")){
                System.out.println("Bye bye!");
                break;
            } else if(opt.equals("help")){
                print_help();
            } else if(opt.equals("print")){
                BFS<Integer> bfs = new BFS<Integer>(tree);
                bfs.print();
            } else{
                System.out.println("Invalid option, try again!");
                Keyboard.nextLine();
            }

        }
    }

    public static void print_help () {
        System.out.println("The available options are");
        System.out.println("1. 'i <int>' to insert an element");
        System.out.println("2. 'd <int>' to delete an element");
        System.out.println("3. 's <int>' to find a particular element\n" +
            "\tReturns 'exists' or 'does not exist' depending on the success of the search");
        System.out.println("4. 'min' to return the minimum element");
        System.out.println("5. 'max' to return the maximum element");
        System.out.println("6. 'print' to print a BFS of the tree");
        System.out.println("7. 'quit' to exit");
    }
}
