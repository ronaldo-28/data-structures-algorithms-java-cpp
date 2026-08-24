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
    boolean found = false;
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        if(found || root1 == null)return found;
        found = search(target-root1.val, root2);
        if(found)return found;
        found = search(root1.val-target, root2);
        if(found)return found;

        twoSumBSTs(root1.left, root2, target);
        twoSumBSTs(root1.right, root2, target);
        return found;
    }

    boolean search(int val, TreeNode n){
        if(n == null)return false;
        TreeNode temp = n;
        while(temp != null){
            if(temp.val == val)return true;
            else if(val > temp.val)temp = temp.right;
            else temp = temp.left;
        }
        return false;
    }
}