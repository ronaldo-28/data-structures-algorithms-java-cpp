// 这里最关键的发现就是：最终我们只关心 queries 从后到前的位置，然后其他的顺序我们并不关心！
class Solution {
    public int[] simulationResult(int[] windows, int[] queries) {
        int n = windows.length;
        int[] ans = new int[n];
        boolean[] used = new boolean[n + 1];

        int idx = 0;

        for (int i = queries.length - 1; i >= 0; i--) {
            if (!used[queries[i]]) {
                ans[idx++] = queries[i];
                used[queries[i]] = true;
            }
        }

        for (int i = 0; i < n; i++) {
            if (!used[ windows[i] ]) {
                ans[idx++] = windows[i];
                used[ windows[i] ] = true;
            }
        }

        return ans;
    }
}