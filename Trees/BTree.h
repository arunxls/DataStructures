#ifndef BTREE
#define BTREE

#include "Node.h"

class BTree {
public:
    void insert(int value);

    BTree(int size);
    // ~BTree();

    // void delete(int value);
    // void* max();
    // void* min();
private:
    // Node* root = new Node();
    int size;
    Node* root;
};

#endif
