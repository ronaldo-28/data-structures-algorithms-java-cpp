import java.util.*;

class Solution {
    public int minMoves(String[] matrix) {
        // Step 1: Collect teleportation portals.
        int m = matrix.length, n = matrix[0].length();
        List<Integer>[] portals = new ArrayList[26];
        for (int i = 0; i < 26; i++)
            portals[i] = new ArrayList<>();

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                char c = matrix[i].charAt(j);
                if (c >= 'A' && c <= 'Z')
                    portals[c - 'A'].add(i * n + j);
            }

        // Step 2: Initialize 0-1 BFS.
        int size = m * n;
        int[] dist = new int[size];
        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] visited = new boolean[size];
        boolean[] used = new boolean[26];
        dist[0] = 0;

        Deque<Integer> q = new ArrayDeque<>();
        q.addFirst(0);
        int[] dirs = {-1, 0, 1, 0, -1};

        // Step 3: Process cells in shortest-distance order.
        while (!q.isEmpty()) {
            int u = q.pollFirst();
            if (visited[u]) continue;
            visited[u] = true;

            int d = dist[u];
            if (u == size - 1) return d;

            int r = u / n, c = u % n;
            char ch = matrix[r].charAt(c);

            // Step 4: Process zero-cost teleportations.
            if (ch >= 'A' && ch <= 'Z') {
                int k = ch - 'A';
                if (!used[k]) {
                    used[k] = true;
                    for (int v : portals[k]) {
                        if (d < dist[v]) {
                            dist[v] = d;
                            q.addFirst(v);
                        }
                    }
                }
            }

            // Step 5: Process adjacent cells.
            for (int k = 0; k < 4; k++) {
                int nr = r + dirs[k], nc = c + dirs[k + 1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n &&
                    matrix[nr].charAt(nc) != '#') {
                    int v = nr * n + nc;
                    if (d + 1 < dist[v]) {
                        dist[v] = d + 1;
                        q.addLast(v);
                    }
                }
            }
        }

        // Step 6: Destination is unreachable.
        return -1;
    }
}