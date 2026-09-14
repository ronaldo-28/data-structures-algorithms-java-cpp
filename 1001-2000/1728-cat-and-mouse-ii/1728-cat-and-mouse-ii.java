class Solution {
    private static final int[] X = new int[] {1, 0, -1, 0};
    private static final int[] Y = new int[] {0, -1, 0, 1};
    private static final int MOUSE = -1;
    private int m, n, cj, mj, len, size, foodIdx;
    private int[] color, degree;
    private char[][] gr;
    public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
        m = grid.length; n = grid[0].length(); cj = catJump; mj = mouseJump; len = m * n; size = len * len;
        int mRow = 0, mCol = 0, cRow = 0, cCol = 0, fRow = 0, fCol = 0;
        color = new int[size << 1];
        degree = new int[len << 1];
        gr = new char[m][n];
        for (int i = 0; i < m; ++i) {
            var g = grid[i].toCharArray();
            gr[i] = g;
            for(int j = 0; j < n; ++j) {
                char val = g[j];
                if (val == 'M') {
                    mRow = i;
                    mCol = j;
                } else if (val == 'C') {
                    cRow = i;
                    cCol = j;
                } else if (val == 'F') {
                    fRow = i;
                    fCol = j;
                }
            }
        }
        for (int r = 0; r < m; ++r) { // initialization
            int base = r * n;
            for (int c = 0; c < n; ++c) {
                if (gr[r][c] == '#') continue;
                int idx = base + c;
                for (int t = 0; t < 2; ++t) { // type - 0 : mouse, 1 : cat
                    int cnt = 0, jump = t == 0 ? mouseJump : catJump;
                    for (int i = 0; i < 4; ++i) {
                        int x = X[i], y = Y[i];
                        for (int j = 1; j <= jump; ++j) {
                            int nr = r + x * j, nc = c + y * j;
                            if(!valid(nr, nc, gr)) break;
                            ++cnt;
                        }
                    }
                    degree[idx + t * len] = ++cnt; // do not move
                }
            }
        }
        // dfs all known-state nodes
        foodIdx = fRow * n + fCol;
        for (int r = 0; r < m; ++r) {
            for (int c = 0; c < n; ++c) {
                char ch = gr[r][c];
                if (ch == '#' || ch == 'F') continue; // mouse and cat can not both have food
                dfs(foodIdx, r * n + c, 1);
            }
        }
        return color[(mRow * n + mCol) * len + cRow * n + cCol] == MOUSE;
    }
    // find all nodes that can be won by mouse, then the rest are cat's win.
    private void dfs(int mouseIdx, int catIdx, int k) {
        int idx = mouseIdx * len + catIdx + k * size;
        if (color[idx] == MOUSE || mouseIdx == catIdx || catIdx == foodIdx) return; // state-known node, mouse get caught, cat has the food
        color[idx] = MOUSE;
        boolean isMouse = k == 0;
        int tar = isMouse ? catIdx : mouseIdx, r = tar / n, c = tar % n, jump = isMouse ? cj: mj, k2 = 1 - k;
        for (int i = 0; i < 4; ++i) {
            int x = X[i], y = Y[i];
            for (int j = 1; j <= jump; ++j) {
                int nr = r + x * j, nc = c + y * j;
                if(!valid(nr, nc, gr)) break;
                int parentIdx = nr * n + nc;
                if (k2 == 0) dfs(parentIdx, catIdx, 0); // now is mouse's turn and it can win by move to the child
                else if (++color[mouseIdx * len + parentIdx + size] == degree[parentIdx + len]) dfs(mouseIdx, parentIdx, 1); 
            }
        }
        int id = mouseIdx * len + catIdx + k2 * size; // do not move
        if (k2 == 0) dfs(mouseIdx, catIdx, 0);
        else if (++color[mouseIdx * len + catIdx + size] == degree[catIdx + len]) dfs(mouseIdx, catIdx, 1);
    }
    private boolean valid(int r, int c, char[][] grid) { 
        return r > -1 && r < m && c > -1 && c < n && grid[r][c] != '#';  // should not outside or hit wall
    }
}