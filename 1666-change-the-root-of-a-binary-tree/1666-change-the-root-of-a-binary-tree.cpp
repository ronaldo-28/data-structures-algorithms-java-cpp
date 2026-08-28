/*
// Definition for a Node.
class Node {
public:
    int val;
    Node* left;
    Node* right;
    Node* parent;
};
*/

class Solution {
public:
    Node* flipBinaryTree(Node* root, Node* leaf) {
        Node* cur = leaf;
        Node* parent = cur->parent;

        while (cur != root) {
            Node* grandparent = parent->parent;

            // If cur has a left child, move it to the right.
            if (cur->left != nullptr) {
                cur->right = cur->left;
                cur->left->parent = cur;
            }

            // Original parent becomes cur's left child
            cur->left = parent;
            parent->parent = cur;

            // Remove cur from its old parent
            if (parent->left == cur)
                parent->left = nullptr;
            else if (parent->right == cur)
                parent->right = nullptr;

            // Move upward
            cur = parent;
            parent = grandparent;
        }

        // New root
        leaf->parent = nullptr;

        return leaf;
    }
};