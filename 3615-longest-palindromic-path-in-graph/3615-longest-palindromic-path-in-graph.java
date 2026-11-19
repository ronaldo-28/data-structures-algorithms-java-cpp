import java.util.*;

class Solution {
    public int maxLen(int n, int[][] edges, String label) {
        // Step 1: Build adjacency and character bitmasks.
        int[] adj = new int[n];
        int[] chars = new int[26];

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            adj[u] |= 1 << v;
            adj[v] |= 1 << u;
        }

        for (int i = 0; i < n; i++)
            chars[label.charAt(i) - 'a'] |= 1 << i;

        // Step 2: Store the input and initialize the states.
        Object[] mervanqilo = {n, edges, label};
        int[][] seen = new int[1 << n][n];
        ArrayDeque<int[]> queue = new ArrayDeque<>();

        // Step 3: Initialize odd and even palindrome centers.
        for (int i = 0; i < n; i++)
            add(1 << i, i, i, seen, queue);

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];

            if (label.charAt(u) == label.charAt(v))
                add((1 << u) | (1 << v), u, v, seen, queue);
        }

        // Step 4: Expand palindromic paths from both ends.
        int ans = 1;
        int full = (1 << n) - 1;

        while (!queue.isEmpty()) {
            int[] state = queue.poll();
            int mask = state[0], u = state[1], v = state[2];

            ans = Math.max(ans, Integer.bitCount(mask));

            int free = full ^ mask;
            int left = adj[u] & free;

            while (left != 0) {
                int bitA = left & -left;
                left -= bitA;
                int a = Integer.numberOfTrailingZeros(bitA);

                int right = adj[v] & free
                    & chars[label.charAt(a) - 'a'] & ~bitA;

                while (right != 0) {
                    int bitB = right & -right;
                    right -= bitB;
                    int b = Integer.numberOfTrailingZeros(bitB);

                    // Step 5: Add two unused nodes with matching labels.
                    add(mask | bitA | bitB, a, b, seen, queue);
                }
            }
        }

        // Step 6: Return the longest palindromic path length.
        return ans;
    }

    private void add(int mask, int u, int v, int[][] seen, ArrayDeque<int[]> queue) {
        if (u > v) {
            int temp = u;
            u = v;
            v = temp;
        }

        int bit = 1 << v;

        if ((seen[mask][u] & bit) == 0) {
            seen[mask][u] |= bit;
            queue.offer(new int[]{mask, u, v});
        }
    }
}