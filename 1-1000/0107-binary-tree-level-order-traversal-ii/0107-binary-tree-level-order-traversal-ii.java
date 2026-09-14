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
class Solution 
{
    List<List<Integer>> ans;
    private void DFS(TreeNode node, int lvl)
    {
        if(node == null) return;
        while(ans.size() <= lvl) ans.add(new ArrayList<>());
        ans.get(lvl).add(node.val);
        DFS(node.left, lvl + 1);
        DFS(node.right, lvl + 1);
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) 
    {
        ans = new ArrayList<>();
        DFS(root, 0);
        Collections.reverse(ans);
        return ans;
    }
}