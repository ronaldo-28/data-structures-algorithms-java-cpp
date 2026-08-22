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
    public TreeNode[] splitBST(TreeNode root, int target) {
        List<TreeNode> smalls=new ArrayList();
        List<TreeNode> bigs=new ArrayList();
        split(root,target,smalls,bigs);
        TreeNode[] res=new TreeNode[2];
        res[0]=parse(smalls);
        res[1]=parse(bigs);
        return res;
    }
    
    private void split(TreeNode root,int target,List<TreeNode> smalls,List<TreeNode> bigs){
        if(root==null){
            return;
        }
        if(root.val<=target){
            smalls.add(root);
            TreeNode right=root.right;
            root.right=null;
            split(right,target,smalls,bigs);
        }else{
            bigs.add(root);
            TreeNode left=root.left;
            root.left=null;
            split(left,target,smalls,bigs);
        }
    }
    
    private TreeNode parse(List<TreeNode> list){
        if(list.isEmpty()){
            return null;
        }
        TreeNode root=list.get(0);
        for(int i=1;i<list.size();i++){
            TreeNode node=list.get(i);
            combine(root,node);
        }
        return root;
    }
    
    private void combine(TreeNode root,TreeNode node){
        if(node.val>root.val){
            if(root.right==null){
                root.right=node;
            }else{
                combine(root.right,node);
            }
        }else {
            if(root.left==null){
                root.left=node;
            }else{
                combine(root.left,node);
            }
        }
    }
}