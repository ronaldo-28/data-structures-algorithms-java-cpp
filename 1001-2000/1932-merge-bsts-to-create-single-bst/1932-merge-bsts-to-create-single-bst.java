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
    int traverseCount = 0;
    
    public TreeNode canMerge(List<TreeNode> trees) {
        // Convert the List of trees to an Array of trees.
        int n = trees.size();
        if (n == 1)  return trees.get(0);
        TreeNode[] treesa = new TreeNode[n];
        trees.toArray(treesa);
        
        // Find the highest node value for roots and leaves, from 
        // the array of tiny trees.
        int maxVal = 0;
        int nodeCount = n;
        for (TreeNode node : treesa) {
            if (node.val > maxVal)  maxVal = node.val;
            if (node.left != null) {
                if (node.left.val > maxVal)  maxVal = node.left.val;
                nodeCount++;
            }
            if (node.right != null) {
                if (node.right.val > maxVal)  maxVal = node.right.val;
                nodeCount++;
            }
        }

        // Map root and leaves to arrays.  Arrays contain the index+1 
        // of the original tree in the pssed List.  If a duplicate leaf 
        // node is found, then final tree not valid, because binary 
        // search trees don't allow any duplicate values.
        final int[] roots = new int[maxVal + 1];
        final int[] leaves = new int[maxVal + 1];
        for (int i = n; i > 0; i--) {
            TreeNode node = treesa[i - 1];
            roots[node.val] = i;
            if (node.left != null) {    
                if (leaves[node.left.val] != 0)  
                    return null;    // Left is dup leaf val.
                leaves[node.left.val] = i;
            }
            if (node.right != null) {   
                if (leaves[node.right.val] != 0)  
                    return null;    // Right is dup leaf val.
                leaves[node.right.val] = i;
            }
        }
        
        // Try to connect the roots of the passed tiny trees, to the 
        // leaves of other passed tiny trees.
        int connectedCount = 0;
        TreeNode unconnectableRoot = null;
        for (int i = n - 1; i >= 0; i--) {
            TreeNode root = treesa[i];
            if (leaves[root.val] == 0) {
                if (unconnectableRoot != null)
                    return null;    // Multiple roots cannot conect to a leaf.
                unconnectableRoot = root;
            } else {
                TreeNode parent = treesa[leaves[root.val] - 1];
                if (parent.left != null && parent.left.val == root.val) {
                    parent.left = root;
                } else {
                    parent.right = root;
                }
                connectedCount++;
                nodeCount--;
            }
        }
        if (connectedCount != n - 1 || unconnectableRoot == null)
            return null;        // Not enough or too many tiny trees conected to leaves
        
        // Validate the binary search tree values are in the 
        // correct order.
        if (!traverse(unconnectableRoot, 0, maxVal + 1))
            return null;        // Values in tree are NOT a binary search tree.
        if (traverseCount != nodeCount)
            return null;        // Not enough nodes in the tree.  Cycles exist.
        return unconnectableRoot;
    }
    
    
    private boolean traverse(TreeNode node, int rangeLowLim, int rangeHighLim) {
        if (node == null)  return true;
        traverseCount++;
        if (node.val <= rangeLowLim || node.val >= rangeHighLim)
            return false;       // Node's value is outside of allowable range.
        if (!traverse(node.left, rangeLowLim, node.val))
            return false;       // Left children have bad BST values.
        return traverse(node.right, node.val, rangeHighLim);
    }
}