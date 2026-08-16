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
    public static TreeNode[] queue = new TreeNode[3001];
    public static int l, r;
    public static int[] index = new int[3001];

    public int widthOfBinaryTree(TreeNode root) {
        int ans = 1;
        l = r = 0;
        index[r] = 1;
        queue[r++] = root;
        while (l < r) {
            int size = r - l;
            ans = Math.max(ans, index[r-1] - index[l] + 1);
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue[l];
                int a = index[l++];
                if (cur.left != null) {
                    queue[r] = cur.left;
                    index[r++] = 2 * a;
                }
                if (cur.right != null) {
                    queue[r] = cur.right;
                    index[r++] = 2 * a + 1;
                }
            }
        }
        return ans;
    }
}