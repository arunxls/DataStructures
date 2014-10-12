#include <iostream>
#include <string>
#include "BTree.h"

using namespace std;

int main (){
    int size;
    cout << "Enter the b value for the tree: \n";
    cin >> size;

    BTree* tree = new BTree(size);

    cout << "Enter the operations you would like to perform \n";
    cout << "1. 'i element' to insert an element\n";
    cout << "2. 'd <num>' to delete an element\n";
    cout << "3. 's <num>' to find a particular element\n";
    cout << "4. 'min' to return the minimum element\n";
    cout << "5. 'max' to return the maximum element\n";
    cout << "6. 'quit' to exit\n";

    while(1) {
        string opt;
        cin >> opt;

        if(opt == "i") {
            int value;
            cin >> value;
            tree->insert(value);
        } else if (opt == "d") {
            int value;
            cin >> value;
            // tree->delete();
        } else if(opt == "s") {
            int value;
            cin >> value;
            // tree->search();
        } else if(opt == "min") {
            // tree->min();
        } else if(opt == "max") {
            // tree->max();
        } else if(opt == "quit") {
            break;
        } else {
            cout << "Incorrect operation! Try again\n";
        }
    }


    return 0;
}

