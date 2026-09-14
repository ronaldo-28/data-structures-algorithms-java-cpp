import java.util.*;

class Solution {
    public long minimumFuelCost(int[][] roads, int seats) {
        int n = roads.length + 1;

        int[] head = new int[n];
        Arrays.fill(head, -1);

        int[] to = new int[2 * roads.length];
        int[] next = new int[2 * roads.length];

        int idx = 0;
        for (int[] r : roads) {
            int u = r[0], v = r[1];

            to[idx] = v;
            next[idx] = head[u];
            head[u] = idx++;

            to[idx] = u;
            next[idx] = head[v];
            head[v] = idx++;
        }

        long ans = 0;
        int[] parent = new int[n];
        int[] order = new int[n];
        int[] size = new int[n];

        Arrays.fill(parent, -1);

        int top = 0;
        order[top++] = 0;

        for (int i = 0; i < top; i++) {
            int u = order[i];
            for (int e = head[u]; e != -1; e = next[e]) {
                int v = to[e];
                if (v == parent[u]) continue;
                parent[v] = u;
                order[top++] = v;
            }
        }

        Arrays.fill(size, 1);

        for (int i = n - 1; i > 0; i--) {
            int u = order[i];
            int p = parent[u];

            ans += (size[u] + seats - 1) / seats;
            size[p] += size[u];
        }

        return ans;
    }
}