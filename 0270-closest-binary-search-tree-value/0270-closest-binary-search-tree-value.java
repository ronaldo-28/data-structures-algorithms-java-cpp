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
    public int closestValue(TreeNode root, double target) {
        int ceil = root.val;
        int floor = root.val;

        TreeNode curr = root;
        while(curr != null){
            if(curr.val < target){
                floor = curr.val;
                curr = curr.right;
            }else{
                ceil = curr.val;
                curr = curr.left;
            }
        }

         
         double diff1 = Math.abs(ceil - target);
         double diff2 = Math.abs(floor - target);
         
         return (diff1 < diff2) ? ceil : floor;
        
    }
}