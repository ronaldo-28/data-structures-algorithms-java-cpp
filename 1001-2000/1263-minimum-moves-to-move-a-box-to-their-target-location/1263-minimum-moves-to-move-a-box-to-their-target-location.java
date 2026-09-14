class Solution {
    char[][] g;
    int m, n;
    int[][] dir = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public int minPushBox(char[][] grid) {
        g = grid;
        m = g.length; 
        n = g[0].length;
        int step = 0;
        boolean[][][] vs = new boolean[m][n][4];
        
        Queue<int[]> q = new LinkedList<>();
        int[] st = new int[]{-1, -1}, ed = new int[]{-1, -1}, pl = new int[]{-1, -1};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] == 'B') st = new int[]{i, j};
                if (g[i][j] == 'T') ed = new int[]{i, j};
                if (g[i][j] == 'S') pl = new int[]{i, j};
            }
        }
        q.offer(new int[]{st[0], st[1], pl[0], pl[1]});
        while (!q.isEmpty()) {
            for (int i = 0, l = q.size(); i < l; i++) {
                int[] curr = q.poll();
                if (curr[0] == ed[0] && curr[1] == ed[1]) return step;
                for (int j = 0; j < dir.length; j++) {
                    if (vs[curr[0]][curr[1]][j]) continue;
                    int[] d = dir[j];
                    int r0 = curr[0] + d[0], c0 = curr[1] + d[1];  // where pl stands, have room to push;
                    if (r0 < 0 || r0 >= m || c0 < 0 || c0 >= n || g[r0][c0] == '#') continue;
                    int r = curr[0] - d[0], c = curr[1] - d[1];  // box next spots;
                    if (r < 0 || r >= m || c < 0 || c >= n || g[r][c] == '#') continue;
                    if (!reachable(r0, c0, curr)) continue;
                    vs[curr[0]][curr[1]][j] = true;
                    q.offer(new int[]{r, c, curr[0], curr[1]});
                }
            }
            step++;
        }
        return -1;
    }
    
    private boolean reachable(int i, int j, int[] curr) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{curr[2], curr[3]});
        boolean[][] vs = new boolean[m][n];
        vs[curr[0]][curr[1]] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (cur[0] == i && cur[1] == j) return true;
            for (int[] d : dir) {
                int r = cur[0] - d[0], c = cur[1] - d[1];  // box next spots;
                if (r < 0 || r >= m || c < 0 || c >= n || vs[r][c] || g[r][c] == '#') continue;
                vs[r][c] = true;
                q.offer(new int[]{r, c});
            }
        }
        return false;
    }
}