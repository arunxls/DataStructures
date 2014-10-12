#include <stdio.h>
#include <assert.h>
#include "Node.h"
#include <vector>
#include <list>

using namespace std;

Node::Node(int branches) {
    this->branches = (branches-1);
    vector <Node *> ptr(branches);
    vector <int *> value(branches);
}

Node::add(int value) {
    int free_ptr = 0;
    for (size_t i = 0; ptr.size(); ++i) {
        if(ptr(i) = NULL) {
            free_ptr = 1;
        }
    }
}
