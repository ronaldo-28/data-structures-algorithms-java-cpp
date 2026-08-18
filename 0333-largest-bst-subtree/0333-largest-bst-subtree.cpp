class Solution {
public:
    struct Info {
        bool isBST;
        int size;
        int minVal;
        int maxVal;

        Info(bool bst, int s, int mn, int mx)
            : isBST(bst), size(s), minVal(mn), maxVal(mx) {}
    };

    int ans = 0;

    Info dfs(TreeNode* root) {
        // Empty tree is a BST
        if (root == nullptr) {
            return Info(true, 0, INT_MAX, INT_MIN);
        }

        Info left = dfs(root->left);
        Info right = dfs(root->right);

        // Check if current subtree is a BST
        if (left.isBST && right.isBST &&
            root->val > left.maxVal &&
            root->val < right.minVal) {

            int size = left.size + right.size + 1;

            ans = max(ans, size);

            int minVal = min(root->val, left.minVal);
            int maxVal = max(root->val, right.maxVal);

            return Info(true, size, minVal, maxVal);
        }

        // Current subtree is not a BST
        return Info(false, 0, 0, 0);
    }

    int largestBSTSubtree(TreeNode* root) {
        ans = 0;
        dfs(root);
        return ans;
    }
};