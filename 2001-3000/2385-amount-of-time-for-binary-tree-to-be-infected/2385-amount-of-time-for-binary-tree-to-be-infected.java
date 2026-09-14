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
    private int maxTime;                                     // 全局答案

    public int amountOfTime(TreeNode root, int start) {
        maxTime = 0;                                         // 初始化答案
        dfs(root, start);                                    // 一次 DFS 搞定
        return maxTime;                                      // 返回答案
    }

    /**
     * 返回值约定（一值两用，用正负区分）：
     *   正数 h  → 没找到 start，h = 子树高度
     *   负数 -d → 找到了 start，d = start 到当前节点的距离
     *   0 特殊  → 当前节点就是 start（距离为 0，用 -0 没法区分，所以约定负数从 -1 开始）
     *
     * 为了让 start 节点本身返回的值也是负数，我们约定：
     *   start 节点返回 -1（距离 0，但编码为 -1）
     *   start 的父节点收到 -1，知道距离 = |返回值| - 1 = 0
     *   再往上传递 -2，距离 = 1，以此类推
     *
     * 总结：
     *   > 0  → 没找到，值就是高度
     *   < 0  → 找到了，距离 = |值| - 1
     */
    private int dfs(TreeNode node, int start) {
        if (node == null) return 0;                          // 空节点，高度 0

        int left = dfs(node.left, start);                    // 递归左子树
        int right = dfs(node.right, start);                  // 递归右子树

        // ---- 情况 1：当前节点就是 start ----
        if (node.val == start) {
            // 子树内最远距离 = max(左高度, 右高度)（左右都是正数）
            maxTime = Math.max(maxTime, Math.max(left, right));
            return -1;                                       // 编码：找到了，距离 0
        }

        // ---- 情况 2：左右都是正数，都没找到 start ----
        if (left >= 0 && right >= 0) {
            return Math.max(left, right) + 1;                // 普通节点，返回子树高度
        }

        // ---- 情况 3：某一侧找到了 start（某一侧为负数） ----
        // 找到那侧：距离 = |负值| - 1
        // 没找到那侧：高度 = 正值
        int dist = Math.abs(Math.min(left, right)) - 1;      // 找到那侧的距离
        int height = Math.max(left, right);                   // 没找到那侧的高度
        // 答案候选 = 距离 + 高度 + 1（当前节点连接两侧）
        maxTime = Math.max(maxTime, dist + height + 1);
        // 往上传递：距离 + 1，继续用负数编码
        return -(dist + 2);                                  // -(距离+1+1) 因为编码偏移了 1
    }
}