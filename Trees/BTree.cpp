#include <iostream>
#include "BTree.h"
#include "Node.h"

using namespace std;

void BTree::insert(int value) {
    cout << "Inserting value " << value << endl;
    root->add(value);
}

BTree::BTree(int size) : root(new Node(size)) {
    cout << "You entered " << size << " as the size\n";
    this->size = size;
}

// void BTree::delete(int value) {
//     std::cout << "Hello World!\n";
// }

// void* BTree::min(int value) {
//     std::cout << "Hello World!\n";
//     int *ptr = NULL;
//     return ptr;
// }

// void* BTree::max(int value) {
//     std::cout << "Hello World!\n";
//     int *ptr = NULL;
//     return ptr;
// }
