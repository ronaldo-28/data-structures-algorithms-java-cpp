class Solution {
       public int longestCycle(int[] edges) {
        int n = edges.length, step = -1, ans = -1;
        for (int i = 0; i < n; i++) {
            int x = i, start = step;
            while (x >= 0) {
                int t = x;
                x = edges[x];
                edges[t] = --step;
            }
            if (x < start) {
                ans = Math.max(ans, x - step);
            }
        }
        return ans;
    }
}