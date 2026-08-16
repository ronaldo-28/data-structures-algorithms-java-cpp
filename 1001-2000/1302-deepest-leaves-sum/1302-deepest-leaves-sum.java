class Solution {
    int maxDepth = 0;
    int sum = 0;
    
    public int deepestLeavesSum(TreeNode root) {
        dfs(root, 0);
        return sum;
    }
    
    private void dfs(TreeNode node, int depth) {
        if (node == null) return;
        
        if (depth > maxDepth) {
            maxDepth = depth;
            sum = node.val;
        } else if (depth == maxDepth) {
            sum += node.val;
        }
        
        dfs(node.left, depth + 1);
        dfs(node.right, depth + 1);
    }
}