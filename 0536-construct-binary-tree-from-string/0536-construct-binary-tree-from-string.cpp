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
    TreeNode* str2tree(string s) {
      int idx = 0; 
      return helper(idx, s);        
    }

    TreeNode* helper(int& idx, string_view s){
      if(idx >= s.size()) return nullptr;
      if(s[idx] == ')'){
        idx++;
        return nullptr;
      }
      TreeNode* root = new TreeNode(0);
      bool isLeft = true;
      bool isNegative = false;
      while(idx < s.size()){
        char c = s[idx++];
        if(c >= '0' && c <= '9'){
          root->val = root->val * 10 + (c - '0');
        }
        if(c == '-'){
          isNegative = true;
        } 
        if(c == '('){
          if(isLeft){
            root->left = helper(idx, s);    
            isLeft = false;
          }else{
            root->right = helper(idx, s);
          }
        }
        if(c == ')' || idx == s.size()){
          break;
        }
      }
      if(isNegative){
        root->val = -(root->val);
      }
      return root;
    }
};