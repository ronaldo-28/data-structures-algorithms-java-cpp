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
class BSTIterator {
public:
    vector<int> nums;
    int i=-1;
    BSTIterator(TreeNode* root) {
        auto dfs = [&](auto&& self, TreeNode* node)->void{
            if(!node)
                return;  
            self(self, node->left);
            nums.push_back(node->val);
            self(self, node->right);
        };
        dfs(dfs, root);
        // reverse(nums.begin(), nums.end());
    }
    
    bool hasNext() {
        if(i+1 < nums.size())
            return true;
        return false;
    }
    
    int next() {
        return nums[++i];
    }
    
    bool hasPrev() {
        if(i-1 >=0)
            return true;
        return false;
    }
    
    int prev() {
        return nums[--i];
    }
};

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator* obj = new BSTIterator(root);
 * bool param_1 = obj->hasNext();
 * int param_2 = obj->next();
 * bool param_3 = obj->hasPrev();
 * int param_4 = obj->prev();
 */