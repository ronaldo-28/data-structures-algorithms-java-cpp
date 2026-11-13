
import java.util.*;

class Solution {
    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;
        int MOD = 1_000_000_007;

        int[] head = new int[n + 1];
        Arrays.fill(head, -1);

        int[] to = new int[2 * edges.length];
        int[] next = new int[2 * edges.length];

        int idx = 0;

        for (int[] e : edges) {
            int u = e[0], v = e[1];

            to[idx] = v;
            next[idx] = head[u];
            head[u] = idx++;

            to[idx] = u;
            next[idx] = head[v];
            head[v] = idx++;
        }

        int[] queue = new int[n];
        int[] parent = new int[n + 1];

        int front = 0, rear = 0;
        queue[rear++] = 1;

        int depth = -1;

        while (front < rear) {
            int size = rear - front;
            depth++;

            while (size-- > 0) {
                int u = queue[front++];

                for (int i = head[u]; i != -1; i = next[i]) {
                    int v = to[i];

                    if (v != parent[u]) {
                        parent[v] = u;
                        queue[rear++] = v;
                    }
                }
            }
        }

        if (depth == 0) return 0;

        long ans = 1;
        for (int i = 0; i < depth - 1; i++) {
            ans = (ans * 2) % MOD;
        }

        return (int) ans;
    }
}