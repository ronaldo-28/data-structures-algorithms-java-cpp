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
    List<Integer> res = new ArrayList<>();
    int max_level = -1;
    
     public List<Integer> rightSideView(TreeNode root) {
        if (root == null)
            return new ArrayList<>();
        
        helper(root, 0);
        return res;
    }
    
    public void helper(TreeNode node, int level) { 
        if(level > max_level){
            res.add(node.val);
            max_level = level;
        }
        if (node.right != null) 
            helper(node.right, level + 1);  
        if (node.left != null) 
            helper(node.left, level + 1);
    }    
}