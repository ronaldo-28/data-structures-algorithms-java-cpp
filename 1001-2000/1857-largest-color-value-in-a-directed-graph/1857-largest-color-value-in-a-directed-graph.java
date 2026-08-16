import java.util.*;

class Solution {
    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        int m = edges.length;

        int[] head = new int[n];
        Arrays.fill(head, -1);
        int[] to = new int[m];
        int[] next = new int[m];
        int[] indeg = new int[n];

        int[] col = new int[n];
        for (int i = 0; i < n; i++) col[i] = colors.charAt(i) - 'a';

        int ei = 0;
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            to[ei] = v;
            next[ei] = head[u];
            head[u] = ei;
            ei++;
            indeg[v]++;
        }

        int[][] dp = new int[n][26];
        for (int i = 0; i < n; i++) {
            dp[i][col[i]] = 1;
        }

        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (indeg[i] == 0) q.add(i);
        }

        int processed = 0, ans = 0;

        while (!q.isEmpty()) {
            int u = q.poll();
            processed++;

            for (int c = 0; c < 26; c++) {
                ans = Math.max(ans, dp[u][c]);
            }

            for (int e = head[u]; e != -1; e = next[e]) {
                int v = to[e];
                int cv = col[v];

                for (int c = 0; c < 26; c++) {
                    int cand = dp[u][c] + (c == cv ? 1 : 0);
                    if (cand > dp[v][c]) dp[v][c] = cand;
                }

                if (--indeg[v] == 0) q.add(v);
            }
        }

        return processed == n ? ans : -1;
    }
}