/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    TreeNode ret;
    Integer depthOfU;
   
    public TreeNode findNearestRightNode(TreeNode root, TreeNode u) {
        dfs(root, 0, u);
        return ret;
    }
   
    private void dfs(TreeNode root, int depth, TreeNode u) {
        if (root == null) return;
       
        if ((depthOfU != null) && (depth == depthOfU) && (root.val != u.val)) {
            ret = root;
            return;
        }
       
        if (root.val == u.val) {
            depthOfU = depth;
        }

        dfs(root.left, depth+1, u);
        if (ret != null) return; // Answer is found.
        dfs(root.right, depth+1, u);
    }
}