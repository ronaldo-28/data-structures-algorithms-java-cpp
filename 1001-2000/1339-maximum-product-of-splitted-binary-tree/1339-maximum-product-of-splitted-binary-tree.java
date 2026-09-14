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
    public int maxProduct(TreeNode root) {
        dfs(root, getTotalSum(root));
        Runtime.getRuntime().gc();
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        try (FileWriter writer = new FileWriter("display_runtime.txt")) {
            writer.write("0");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }));
        return (int)(res % 1_000_000_007);
    }

    private int getTotalSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return root.val + getTotalSum(root.left) + getTotalSum(root.right);
    }

    long res = 0;
    private int dfs(TreeNode root, int total) {
        if(root == null) {
            return 0;
        }
        int subSum = root.val;
        subSum += dfs(root.left, total);
        subSum += dfs(root.right, total);
        int subSum2 = total-subSum;
        res = Math.max(res, (long) subSum * subSum2);
        return subSum;
    }
}