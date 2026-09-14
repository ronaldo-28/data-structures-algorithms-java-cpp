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
    // public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
    //     List<List<Integer>> result = new ArrayList<>();
    //     List<Integer> path = new ArrayList<>();
    //     dfs(root, targetSum, path, result);
    //     return result;
    // }

    // private void dfs(TreeNode node, int targetSum, List<Integer> path, List<List<Integer>> result) {
    //     if (node == null) return;

    //     path.add(node.val);

    //     if (node.val == targetSum && node.left == null && node.right == null) {
    //         result.add(new ArrayList<>(path));
    //     } else {
    //         dfs(node.left, targetSum - node.val, path, result);
    //         dfs(node.right, targetSum - node.val, path, result);
    //     }

    //     path.remove(path.size() - 1);
    // }

    List<List<Integer>> result = new ArrayList<>();
    int[] array = new int[1001];

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        dfs(root, targetSum, 0);
        return result;
    }

    private void dfs(TreeNode node, int remaining, int depth) {
        if (node == null) return;

        array[depth] = node.val;

        if (node.left == null && node.right == null && remaining == node.val) {
            List<Integer> path = new ArrayList<>();
            for (int i = 0; i <= depth; i++) {
                path.add(array[i]);
            }
            result.add(path);
        } else {
            dfs(node.left, remaining - node.val, depth + 1);
            dfs(node.right, remaining - node.val, depth + 1);
        }
    }

}