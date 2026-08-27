/**
 * Definition for a binary tree node.
 * class Node {
 *     char val;
 *     Node left;
 *     Node right;
 *     Node() {this.val = ' ';}
 *     Node(char val) { this.val = val; }
 *     Node(char val, Node left, Node right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    void dfs(Node n, int[] cnt) {
        if (n == null) {
            return;
        }
        dfs(n.left, cnt);
        if (n.val != '+') {
            cnt[n.val - 'a']++;
        }
        dfs(n.right, cnt);
    }

    boolean isEqual(int[] cnt_a, int[] cnt_b) {
        for (int i = 0; i < 26; ++i) {
            if (cnt_a[i] != cnt_b[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean checkEquivalence(Node root1, Node root2) {
        int[] cnt_a = new int[26];
        int[] cnt_b = new int[26];
        dfs(root1, cnt_a);
        dfs(root2, cnt_b);

        return isEqual(cnt_a, cnt_b);
    }
}