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
    public List<Long> zigzagLevelSum(TreeNode root) {
        List<Long> res = new ArrayList<>();
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        boolean isOdd = true;
        while (!q.isEmpty()){
            int k = q.size();
            long level = 0L;
            if (isOdd){ // 奇数层：从左往右处理 (从队头 poll)
                boolean stop = false;
                for (int i = 0; i < k; i++){
                    TreeNode cur = q.pollFirst();
                    if (cur.left == null) stop = true;
                    if (!stop) level += cur.val;
                    // 下一层（偶数层）子节点从队尾 offer：先左后右
                    if (cur.left != null) q.offerLast(cur.left);
                    if (cur.right != null) q.offerLast(cur.right);
                }
            } else { // 偶数层：从右往左处理 (从队尾 poll)
                boolean stop = false;
                for (int i = 0; i < k; i++){   
                    TreeNode cur = q.pollLast();
                    if (cur.right == null) stop = true;
                    if (!stop) level += cur.val;
                    // 下一层（奇数层）子节点从队头 push：先右后左
                    if (cur.right != null) q.offerFirst(cur.right);
                    if (cur.left  != null) q.offerFirst(cur.left);

                }
            }
            res.add(level);
            isOdd = !isOdd;
        }
        return res;
    }
/** 
    普通的单向队列（Queue）就像一条单行道，只靠“颠倒子节点入队顺序”，只能影响下一层的顺序，但会把再下一层的节点左右相对位置彻底颠倒（导致 6, 7 跑到了 4, 5 的前面）。

    这就是为什么必须使用 Deque（双端队列）：
    偶数层从队尾出队（pollLast），同时把下一层的节点从队头压入（offerFirst），这样才能保证下一层的节点不会被颠倒位置。
*/
}