import java.util.Arrays;

class Solution {
    public int minMaxWeight(int n, int[][] edges, int threshold) {
        if (n == 1) return 0; // defensive; constraints guarantee n >= 2
        int m = edges.length;

        // Pack (weight, index) into a long -> primitive sort, no boxed comparator.
        long[] order = new long[m];
        for (int i = 0; i < m; i++) order[i] = ((long) edges[i][2] << 32) | i;
        Arrays.sort(order);

        // Reverse adjacency built incrementally as a linked-list-in-arrays.
        int[] head = new int[n];
        Arrays.fill(head, -1);
        int[] nxt = new int[m];
        int[] dst = new int[m];

        boolean[] seen = new boolean[n];
        seen[0] = true;
        int reached = 1;
        int[] stack = new int[n];

        for (int k = 0; k < m; k++) {
            int idx = (int) order[k];
            int w   = (int) (order[k] >>> 32);
            int from = edges[idx][1]; // reversed source = v
            int to   = edges[idx][0]; // reversed target = u

            dst[k] = to;            // insert reversed edge from -> to
            nxt[k] = head[from];
            head[from] = k;

            // Source already connected to 0? This edge may pull in new nodes.
            if (seen[from] && !seen[to]) {
                int sp = 0;
                stack[sp++] = to;
                seen[to] = true;
                reached++;
                while (sp > 0) {
                    int x = stack[--sp];
                    for (int e = head[x]; e != -1; e = nxt[e]) {
                        int y = dst[e];
                        if (!seen[y]) { seen[y] = true; reached++; stack[sp++] = y; }
                    }
                }
                if (reached == n) return w; // first full connection => minimal W
            }
        }
        return -1;
    }
}