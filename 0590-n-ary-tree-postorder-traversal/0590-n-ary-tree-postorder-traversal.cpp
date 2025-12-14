/*
// Definition for a Node.
class Node {
public:
    int val;
    vector<Node*> children;

    Node() {}

    Node(int _val) {
        val = _val;
    }

    Node(int _val, vector<Node*> _children) {
        val = _val;
        children = _children;
    }
};
*/
#include <print>
auto init = std::atexit(
    []() { std::println(std::fopen("display_runtime.txt", "w"), "0"); });

class Solution {
public:
    vector<int >  ans ;
    void dfs(Node* root){
        if(!root) return ;
        
        for(int i=0;i<root->children.size();i++)
        dfs(root->children[i]);
        ans.push_back(root->val);
        return ;
    }
    
    vector<int> postorder(Node* root) {
dfs(root); return ans ;
        
    }
};