/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
/*
 * 1
 * / \
 * 2 3
 * \
 * 4
 * / \
 * 5 6
 * \
 * 7
 */
class Solution {
    public int longestZigZag(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode node, int dir) {
        if (node == null) {
            return 0;
        }
        boolean isLeft=true;
        int max=0;
        int leftcount=0;
        TreeNode curr=node.left;

        while(curr!=null){
         leftcount++;
         if(isLeft){
            max=Math.max(max,dfs(curr.left,2));
            curr=curr.right;
         }
         else{
            max=Math.max(max,dfs(curr.right,1));
            curr=curr.left;
         }
        isLeft=!isLeft;
    }

        int rightcount=0;
        isLeft=false;
        curr=node.right;

        while(curr!=null){
         rightcount++;
         if(isLeft){
            max=Math.max(max,dfs(curr.left,2));
            curr=curr.right;
         }
         else{
            max=Math.max(max,dfs(curr.right,1));
            curr=curr.left;
         }
        isLeft=!isLeft;
       }
        if(dir==1) leftcount++;
        else if(dir==2)
          rightcount++;
    return Math.max(Math.max(leftcount, rightcount), max);
    }
}