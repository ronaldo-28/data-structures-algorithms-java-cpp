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
    int i=0;
    int j=0;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        i=inorder.length-1;
        j=i;
        return buildTree(inorder,postorder,-3001);
    }
    public TreeNode buildTree(int[] inorder, int[] postorder,int x) {
        if(i<0)
            return null;
        if(inorder[j]==x){
            j--;
            return null;
        }

        TreeNode cur = new TreeNode(postorder[i--]);
        cur.right = buildTree(inorder,postorder,cur.val);
        cur.left = buildTree(inorder,postorder,x);
        return cur;
    }
}