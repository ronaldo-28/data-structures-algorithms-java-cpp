import java.util.Arrays;

class Solution {

    public int maxWeight(int n, int[][] edges, int k, int t) {
        if (t <= 0 || k > n - 1) return -1;
        if (k == 0) return 0;

        int W = (t + 63) >>> 6;          // words per node

        // Keep only edges that can ever matter (w < t)
        int kept = 0;
        for (int[] e : edges) if (e[2] < t) kept++;
        if (kept == 0) return -1;

        int[] eu = new int[kept], ev = new int[kept], ew = new int[kept];
        int c = 0;
        for (int[] e : edges) {
            if (e[2] < t) {
                eu[c] = e[0]; ev[c] = e[1]; ew[c] = e[2];
                c++;
            }
        }

        // prev[u*W + i] = word i of the set of exact weights reachable at node u
        long[] prev = new long[n * W];
        long[] cur = new long[n * W];
        boolean[] prevHas = new boolean[n];
        boolean[] curHas = new boolean[n];

        // Layer 0: weight 0 at every node
        for (int u = 0; u < n; u++) {
            prev[u * W] = 1L;
            prevHas[u] = true;
        }

        for (int j = 1; j <= k; j++) {
            Arrays.fill(cur, 0L);
            Arrays.fill(curHas, false);
            boolean any = false;

            for (int i = 0; i < kept; i++) {
                int u = eu[i];
                if (!prevHas[u]) continue;

                int v = ev[i];
                int w = ew[i];
                int ws = w >>> 6;
                int bs = w & 63;
                int srcBase = u * W;
                int dstBase = v * W;

                // cur[v] |= prev[u] << w
                if (bs == 0) {
                    for (int d = W - 1; d >= ws; d--) {
                        cur[dstBase + d] |= prev[srcBase + d - ws];
                    }
                } else {
                    int inv = 64 - bs;
                    for (int d = W - 1; d > ws; d--) {
                        int s = d - ws;
                        cur[dstBase + d] |= (prev[srcBase + s] << bs)
                                          | (prev[srcBase + s - 1] >>> inv);
                    }
                    cur[dstBase + ws] |= prev[srcBase] << bs;
                }
                curHas[v] = true;
                any = true;
            }

            if (!any) return -1;

            long[] tmp = prev; prev = cur; cur = tmp;
            boolean[] tmpH = prevHas; prevHas = curHas; curHas = tmpH;
        }

        // Largest set bit below t across all nodes
        int lastBits = t - ((W - 1) << 6);
        long lastMask = (lastBits == 64) ? -1L : ((1L << lastBits) - 1);

        int answer = -1;
        for (int u = 0; u < n; u++) {
            if (!prevHas[u]) continue;
            int base = u * W;
            for (int d = W - 1; d >= 0; d--) {
                long word = prev[base + d];
                if (d == W - 1) word &= lastMask;
                if (word != 0) {
                    int best = (d << 6) + 63 - Long.numberOfLeadingZeros(word);
                    if (best > answer) answer = best;
                    break;
                }
            }
        }
        return answer;
    }
}