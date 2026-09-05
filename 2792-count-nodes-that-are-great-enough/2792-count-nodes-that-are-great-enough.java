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
    int k, ans = 0;
    public int countGreatEnoughNodes(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return ans;
    }
    private int[] dfs(TreeNode root) {
        if(root == null) return new int[0];

        //get sorted arrays from the left and right children, merge them, and stop when the resulting array has k elements
        int index = 0, i = 0, j = 0;
        int[] right = dfs(root.right), left = dfs(root.left), arr = new int[Math.min(k, right.length + left.length + 1)];
        while(index < k && i < right.length && j < left.length) arr[index++] = right[i] < left[j] ? right[i++] : left[j++];
        while(index < k && i < right.length) arr[index++] = right[i++];
        while(index < k && j < left.length) arr[index++] = left[j++];

        //insert the root into the sorted smallest k elements if it belongs there, otherwise increment the final answer
        if(index == k && arr[k - 1] < root.val) ans++;
        else insertVal(arr, root.val);
        return arr;
    }

    private static void insertVal(int[] arr, int val) {
        for(int i = arr.length - 2; i >= 0; i--) {
            if(arr[i] <= val) {
                arr[i + 1] = val;
                return;
            }
            arr[i + 1] = arr[i];
        }
        arr[0] = val;
    }

}