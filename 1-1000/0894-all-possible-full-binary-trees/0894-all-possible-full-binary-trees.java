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

    private Map<Integer, List<TreeNode>> dp = new HashMap<>();

    public List<TreeNode> allPossibleFBT(int n) {
        if (dp.containsKey(n)) {
            return dp.get(n);
        }

        List<TreeNode> res = new ArrayList<>();
        if (n == 1) {
            res.add(new TreeNode(0));
        } else if (n % 2 == 1) { // Full binary trees have an odd number of nodes
            for (int i = 0; i < n; i++) {
                int r = n - 1 - i;
                List<TreeNode> leftTrees = allPossibleFBT(i);
                List<TreeNode> rightTrees = allPossibleFBT(r);

                for (TreeNode left : leftTrees) {
                    for (TreeNode right : rightTrees) {
                        TreeNode root = new TreeNode(0, left, right);
                        res.add(root);
                    }
                }
            }
        }
        dp.put(n, res);
        return res;
    }
}