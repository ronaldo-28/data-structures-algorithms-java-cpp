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
    public int minimumLevel(TreeNode root) {
        Queue<TreeNode> qu = new LinkedList<>();
        long minSum = Long.MAX_VALUE;
        int minLevel = 1;
        int level = 0;
        qu.add(root);
        while (qu.size() != 0) {
            int s = qu.size();
            long sum = 0;
            level++;
            while (s-- > 0) {
                TreeNode x = qu.remove();
                sum += x.val;
                if (x.left != null)
                    qu.add(x.left);
                if (x.right != null)
                    qu.add(x.right);
            }
            if (sum < minSum) {
                minSum = sum;
                minLevel = level;
            }
        }
        return minLevel;
    }
}