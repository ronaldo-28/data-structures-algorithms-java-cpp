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
    vector<TreeNode*> solve(int start, int end){
        //base case
        if(start>end)
            return {NULL};
        //ek case
        vector<TreeNode*> ans;
        for(int i=start;i<=end;i++){
            vector<TreeNode*> left = solve(start, i-1);
            vector<TreeNode*> right = solve(i+1, end);
            for(auto lf:left)
                for(auto rt:right){
                    TreeNode* root = new TreeNode(i);
                    root->left=lf;
                    root->right=rt;
                    ans.push_back(root);
                }
        }
        return ans;
    }
    map<pair<int,int>,vector<TreeNode*>>dp;
    vector<TreeNode*> solveTD(int start, int end){
        //base case
        if(start>end)
            return {NULL};
        if(dp.find({start,end})!=dp.end())
            return dp[{start,end}];
        //ek case
        vector<TreeNode*> ans;
        for(int i=start;i<=end;i++){
            vector<TreeNode*> left = solveTD(start, i-1);
            vector<TreeNode*> right = solveTD(i+1, end);
            for(auto lf:left)
                for(auto rt:right){
                    TreeNode* root = new TreeNode(i);
                    root->left=lf;
                    root->right=rt;
                    ans.push_back(root);
                }
        }
        return dp[{start,end}] = ans;
    }
    vector<TreeNode*> generateTrees(int n) {
        // return solve(1,n);
        return solveTD(1,n);
    }
};