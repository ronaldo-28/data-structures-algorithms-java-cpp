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
    Node* lowestCommonAncestor(Node* p, Node * q) {
        vector<Node*> a;
        vector<Node*> b;
        while(p) {
            a.push_back(p);
            p=p->parent;
        }

        while(q) {
            b.push_back(q);
            q=q->parent;
        }

        int i=a.size()-1, j=b.size()-1;
        while(i>=0 && j>=0) {
            if(a[i]->val != b[j]->val) return a[i+1];
            i--; j--;
        }

        if(i==-1) return a[0];
        if(j==-1) return b[0];
        return a[a.size()-1];
    }
};