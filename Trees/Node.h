#ifndef NODE
#define NODE

class Node {
public:
    void set_ptr(int index, int value);

    void add(int value);

    Node(int branches);
    // ~BTree();

    // void delete(int value);
    // void* max();
    // void* min();
private:
    int branches;
    Node *ptr;
    std::vector<Node*> v;
    int *val;
};

#endif
