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
    
    public int dfs(TreeNode root,int tar,Long csum,Map<Long,Integer> ma){
        if(root==null)
         return 0;

        csum+=root.val;

        int count=ma.getOrDefault(csum-tar,0);
        
        ma.put(csum,ma.getOrDefault(csum,0)+1);
        count+=dfs(root.left,tar,csum,ma);
        count+=dfs(root.right,tar,csum,ma);
        ma.put(csum,ma.getOrDefault(csum,0)-1);

        return count; 
    }

    public int pathSum(TreeNode root, int tar) {
        Map<Long,Integer> ma=new HashMap<>();
        ma.put((long)0,1);
        int c= dfs(root,tar,(long)0,ma);
        return c;
    }
}