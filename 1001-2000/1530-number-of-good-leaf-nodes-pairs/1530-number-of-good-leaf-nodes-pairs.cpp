class Solution {
public:
    int cnt=0;
    array<int, 10> dfs(TreeNode* root, int d) {
        // x[k-1]=number of leafs with distance=k
        array<int, 10> x={0};
        if (!root) return x;

        if (!root->left && !root->right) {
            x[0] = 1;// root is a leaf node
            return x;
        }

        auto left= dfs(root->left, d);
        auto right= dfs(root->right, d);

        for (int i=0; i <d-1; i++) 
            x[i+1] = left[i]+right[i];

        // Prefix sum avoids of nested-loop
        for (int i=1; i <= d-1; i++)
            left[i] += left[i- 1];
        
        //root is the LCA, apply multiplication principle for counting
        for (int i = 0; i < d-1; i++) 
            cnt += left[i]* right[d-2-i];
        
        return x;
    }

    int countPairs(TreeNode* root, int distance) {
        dfs(root, distance);
        return cnt;
    }
};





auto init = []() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    return 'c';
}();