import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) return false;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean foundX = false, foundY = false;

            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();

                // Check if x and y are siblings
                if (current.left != null && current.right != null) {
                    if ((current.left.val == x && current.right.val == y) ||
                        (current.left.val == y && current.right.val == x)) {
                        return false;
                    }
                }

                // Check for x and y at the current level
                if (current.left != null) {
                    queue.add(current.left);
                    if (current.left.val == x) foundX = true;
                    if (current.left.val == y) foundY = true;
                }

                if (current.right != null) {
                    queue.add(current.right);
                    if (current.right.val == x) foundX = true;
                    if (current.right.val == y) foundY = true;
                }
            }

            // If both are found at the same level and not siblings, they are cousins
            if (foundX && foundY) return true;

            // If one is found but not the other, they are not cousins
            if (foundX || foundY) return false;
        }

        return false;
    }
}