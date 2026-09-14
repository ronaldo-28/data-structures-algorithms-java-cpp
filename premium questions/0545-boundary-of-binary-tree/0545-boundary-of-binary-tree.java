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
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        
        List<Integer> res = new ArrayList<>();
        
        if(root == null) return res;
        
        res.add(root.val);
        getBounds(root.left, res, true, false);
        getBounds(root.right, res, false, true);
        
        return res;
    }
    
    private void getBounds(TreeNode node, List<Integer> res, boolean isLB, boolean isRB){
        
        if(node == null) return;
        
        if(isLB) res.add(node.val);
        
        if(!isLB && !isRB && node.left == null && node.right == null) {
            res.add(node.val);
        }
        
        getBounds(node.left, res, isLB, isRB && node.right == null);
        getBounds(node.right, res, isLB && node.left == null, isRB);
        
        if(isRB) res.add(node.val);
        
    }
}