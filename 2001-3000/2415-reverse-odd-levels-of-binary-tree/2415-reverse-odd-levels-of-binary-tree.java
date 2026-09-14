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
    public TreeNode reverseOddLevels(TreeNode root) {
        if(root.left == null){
            return root;
        }
        reverseOddLevels(root.left,root.right, 1);
        return root;
    }

    public void reverseOddLevels(TreeNode leftChild, TreeNode rightChild, int level){
        if(level%2 != 0){
            int swap = leftChild.val;
            leftChild.val = rightChild.val;
            rightChild.val = swap;
        }
        if(leftChild.left == null){
            return;
        }
        reverseOddLevels(leftChild.left, rightChild.right, level+1);
        reverseOddLevels(leftChild.right, rightChild.left, level+1);
    }
}