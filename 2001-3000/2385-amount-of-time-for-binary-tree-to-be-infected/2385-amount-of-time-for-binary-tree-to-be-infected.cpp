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
int search(TreeNode* keep, int &bs, int tar, int &maxi){
    if(keep==NULL)
    return 0;
    if(keep->val==tar){
        bs=1;
        int check=0;
        check=forwi(keep, -1);
        if(check>maxi)
        maxi=check;       
        return 1;
    }
    int hui1=0, hui2=0;
   hui1= search(keep->left, bs, tar, maxi);
    if(hui1){
        int check=0;
        check=forwi(keep->right, bs);
     if(check>maxi)
     maxi=check;
     bs++;
    }
    
    hui2= search(keep->right, bs, tar, maxi);
    if(hui2){
        int check=0;
        check=forwi(keep->left, bs);
     if(check>maxi)
     maxi=check;
     bs++;
    }
    return (hui1||hui2);
    
}
int forwi(TreeNode *keep, int k){
    if(keep==NULL)
    return k;
    k++;
    
    int a=forwi(keep->left, k);
    int b=forwi(keep->right, k);
    return max(a, b);
}

    int amountOfTime(TreeNode* root, int start) {
        int maxi=0, bs=0;
        int hui= search(root, bs, start, maxi);
        return maxi;
    }
};