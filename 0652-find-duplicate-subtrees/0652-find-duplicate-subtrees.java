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
    int uid = 0;
    Map<Triple, Integer> ids;
    Map<Integer, Integer> counts;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        ids = new HashMap<>();
        counts = new HashMap<>();
    
        List<TreeNode> res = new ArrayList<>();

        dfs(root, res);

        return res;
    }

    int dfs(TreeNode node, List<TreeNode> res) {
        if (node == null) return 0;

        int leftId = dfs(node.left, res);
        int rightId = dfs(node.right, res);

        Triple key = new Triple(node.val, leftId, rightId);

        int id = ids.getOrDefault(key, 0);
        if (id == 0) {
            id = ++uid;
            ids.put(key, id);
        }

        int count = counts.getOrDefault(id, 0) + 1;
        counts.put(id, count);

        if (count == 2) {
            res.add(node);
        }

        return id;
    }

    class Triple {
        int val;
        int leftId;
        int rightId;

        Triple(int v, int l, int r) {
            val = v;
            leftId = l;
            rightId = r;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Triple t = (Triple) o;

            return val == t.val && leftId == t.leftId && rightId == t.rightId;
        }

        @Override 
        public int hashCode() {
            int code = 1;

            code = code * 31 + val;
            code = code * 31 + leftId;
            code = code * 31 + rightId;

            return code;
        }
    }
}