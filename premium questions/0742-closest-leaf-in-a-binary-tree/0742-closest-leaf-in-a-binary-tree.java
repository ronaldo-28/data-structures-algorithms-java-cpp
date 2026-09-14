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

    private int bestDistance = Integer.MAX_VALUE;
    private int answer;

    static class Info {
        int closestLeafDistance;
        int closestLeafValue;
        int distanceToTarget;

        Info(int closestLeafDistance,
             int closestLeafValue,
             int distanceToTarget) {

            this.closestLeafDistance = closestLeafDistance;
            this.closestLeafValue = closestLeafValue;
            this.distanceToTarget = distanceToTarget;
        }
    }

    public int findClosestLeaf(TreeNode root, int k) {
        dfs(root, k);
        return answer;
    }

    private Info dfs(TreeNode node, int k) {

        if (node == null) {
            return null;
        }

        // Leaf
        if (node.left == null && node.right == null) {

            int distanceToTarget =
                    node.val == k ? 0 : -1;

            if (node.val == k) {
                answer = node.val;
                bestDistance = 0;
            }

            return new Info(
                    0,
                    node.val,
                    distanceToTarget
            );
        }

        Info left = dfs(node.left, k);
        Info right = dfs(node.right, k);

        // ---------------------------------
        // Closest leaf
        // ---------------------------------

        Info closest;

        if (left == null) {
            closest = right;
        } else if (right == null) {
            closest = left;
        } else {
            closest = left.closestLeafDistance
                    <= right.closestLeafDistance
                    ? left
                    : right;
        }

        int closestLeafDistance =
                closest.closestLeafDistance + 1;

        int closestLeafValue =
                closest.closestLeafValue;

        // ---------------------------------
        // Distance to target k
        // ---------------------------------

        int distanceToTarget = -1;

        if (node.val == k) {

            distanceToTarget = 0;

        } else if (left != null && left.distanceToTarget != -1) {

            distanceToTarget =
                    left.distanceToTarget + 1;

        } else if (right != null && right.distanceToTarget != -1) {

            distanceToTarget =
                    right.distanceToTarget + 1;
        }

        // ---------------------------------
        // Candidate answer
        // ---------------------------------

        if (distanceToTarget != -1) {

            int distance =
                    distanceToTarget
                    + closestLeafDistance;

            // Current node counted twice
            distance--;

            if (distance < bestDistance) {
                bestDistance = distance;
                answer = closestLeafValue;
            }
        }

        return new Info(
                closestLeafDistance,
                closestLeafValue,
                distanceToTarget
        );
    }
}