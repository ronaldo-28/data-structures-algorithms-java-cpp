import java.util.*;

class Solution {
    public int subtreeInversionSum(int[][] edges, int[] nums, int k) {
        int n = nums.length;
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        int[] parent = new int[n];
        int[] bfsOrder = new int[n];
        boolean[] visited = new boolean[n];
        List<Integer>[] children = new List[n];
        for (int i = 0; i < n; i++) children[i] = new ArrayList<>();

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(0);
        visited[0] = true;
        parent[0] = -1;
        int idx = 0;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            bfsOrder[idx++] = u;
            for (int v : adj[u]) {
                if (!visited[v]) {
                    visited[v] = true;
                    parent[v] = u;
                    children[u].add(v);
                    queue.add(v);
                }
            }
        }

        final long NEG_INF = Long.MIN_VALUE / 4;
        int K = k;
        int size = K + 1;

        long[][][] T = new long[n][2][size]; // T[node][parity][d]

        for (int oi = n - 1; oi >= 0; oi--) {
            int v = bfsOrder[oi];
            List<Integer> ch = children[v];

            for (int p = 0; p < 2; p++) {
                int signP = (p == 0) ? 1 : -1;
                int sign1mP = -signP;

                long[] C = new long[size];
                Arrays.fill(C, NEG_INF);
                C[size - 1] = 0;

                for (int c : ch) {
                    long[] childTable = T[c][p];
                    long[] shifted = new long[size];
                    Arrays.fill(shifted, NEG_INF);
                    for (int dp = 1; dp <= K - 1; dp++) shifted[dp] = childTable[dp - 1];
                    long best = Math.max(childTable[K - 1], childTable[K]);
                    shifted[K] = best;

                    long[] newC = new long[size];
                    Arrays.fill(newC, NEG_INF);
                    for (int d1 = 0; d1 <= K; d1++) {
                        if (C[d1] <= NEG_INF / 2) continue;
                        for (int d2 = 0; d2 <= K; d2++) {
                            if (shifted[d2] <= NEG_INF / 2) continue;
                            if (d1 + d2 < K) continue;
                            int nd = Math.min(d1, d2);
                            long val = C[d1] + shifted[d2];
                            if (val > newC[nd]) newC[nd] = val;
                        }
                    }
                    C = newC;
                }

                long[] Tvp = T[v][p];
                Arrays.fill(Tvp, NEG_INF);
                for (int d = 1; d <= K; d++) {
                    if (C[d] <= NEG_INF / 2) continue;
                    Tvp[d] = (long) nums[v] * signP + C[d];
                }

                long selVal = (long) nums[v] * sign1mP;
                boolean valid = true;
                for (int c : ch) {
                    long[] childTable1mp = T[c][1 - p];
                    long best = Math.max(childTable1mp[K - 1], childTable1mp[K]);
                    if (best <= NEG_INF / 2) { valid = false; break; }
                    selVal += best;
                }
                if (valid && selVal > Tvp[0]) Tvp[0] = selVal;
            }
        }

        long ans = NEG_INF;
        for (int d = 0; d <= K; d++) ans = Math.max(ans, T[0][0][d]);
        return (int) ans;
    }
}