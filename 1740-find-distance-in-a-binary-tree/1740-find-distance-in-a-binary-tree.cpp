/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */

class Solution {
public:
    TreeNode* findlca(TreeNode* root, int p, int q) {
        if (!root || root -> val == p || root -> val == q) {
            return root;
        }
        TreeNode* left = findlca(root -> left, p, q);
        TreeNode* right = findlca(root -> right, p, q);
        
        return !left ? right : !right ? left : root;
    }
    
    int findDistance(TreeNode* root, int p, int q) {
        
        TreeNode* lca = findlca(root, p, q);
        int dp = -1, dq = -1, level = 0;
        
        queue<TreeNode*> qu;
        qu.push(lca);
        
        while(!qu.empty() && (dp == -1 || dq == -1)) {
            
            int size = qu.size();
            
            for(int i = 0; i < size; i++) {
                
                TreeNode* curr = qu.front();
                qu.pop();
                
                if(curr -> val == p) dp = level;
                if(curr -> val == q) dq = level;
                
                if(curr -> left) qu.push(curr->left);
                if(curr -> right) qu.push(curr->right);
            }
            level++;
        }
        
        return dp + dq;
    }
};