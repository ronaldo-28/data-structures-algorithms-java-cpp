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
    //reuseable arrays, these store the value, parent, left child, and right child for each node
    private static final int[] value = new int[1001], parent = new int[1001], left = new int[1001], right = new int[1001];
    //another reuseable array, this tracks which values we've already seen on the current path
    private static final boolean[] seen = new boolean[2001];

    private int size = 0; //total # of nodes
    public int maxSum(TreeNode root) {
        //give each node an index, and store the value, parent, left child, and right child for each index
        buildGraph(root, -1);

        //for each index, find the maximum path sum starting at that node, and return the total maximum
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < size; i++) max = Math.max(max, dfs(i, -1)); 
        return max;
    }

    private int buildGraph(TreeNode root, int prev) {
        if(root == null) return -1;

        //as we count the # of nodes, use the current count as the index for the current node
        //like adding something to a List, the previous size of the List becomes the index for the new element
        int index = size++;

        //store the current node value, parent index, left child index, and right child index
        //if the parent, left, or right node doesn't exist, store it as -1 instead
        value[index] = root.val;
        parent[index] = prev;
        left[index] = buildGraph(root.left, index); 
        right[index] = buildGraph(root.right, index);

        return index; //return the current index
    }

    private int dfs(int index, int prev) {
        if(index == -1 || seen[value[index] + 1000]) return 0; //stop if the current node is null or it is a duplicate value
        //mark the current value as seen (we add 1000 so we can't have a negative index)
        seen[value[index] + 1000] = true;

        //find the maximum path for the parent, left child, and right child (but don't double back to the prev node)
        int max = 0;
        if(left[index] != prev) max = Math.max(max, dfs(left[index], index));
        if(right[index] != prev) max = Math.max(max, dfs(right[index], index));
        if(parent[index] != prev) max = Math.max(max, dfs(parent[index], index));

        seen[value[index] + 1000] = false; //mark the current value as NOT seen, cuz we backtracking
        return max + value[index]; //return the current node value plus the maximum path sum of the connected nodes
    }
}