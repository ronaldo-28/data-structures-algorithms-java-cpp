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
    private static final TreeNode[] queue = new TreeNode[100000];
    public TreeNode replaceValueInTree(TreeNode root) {
        int read = 0, write = 1, sum = root.val;
        queue[0] = root;

        while(read < write) {
            int temp = 0;
            int size = write - read;
            for(int i = 0; i < size; i++) {
                TreeNode current = queue[read++];
                current.val = sum - current.val;

                int val = 0;
                if(current.left != null) val += current.left.val;
                if(current.right != null) val += current.right.val;

                temp += val;

                if(current.left != null) {
                    current.left.val = val;
                    queue[write++] = current.left;
                }
                if(current.right != null) {
                    current.right.val = val;
                    queue[write++] = current.right;
                }
            }
            sum = temp;
        }
        return root;
    }
}