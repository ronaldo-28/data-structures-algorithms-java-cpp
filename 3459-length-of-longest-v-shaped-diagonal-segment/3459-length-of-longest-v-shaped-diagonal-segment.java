class Solution {
    static int[] dx = {-1, -1, 1, 1 }, dy = {-1, 1, 1, -1 };
    static int[] que = new int[250000];
    public int lenOfVDiagonal(int[][] grid) {

        int m = grid.length, n = grid[0].length;
        int[][][] len0s = new int[4][m][n], len2s = new int[4][m][n];
        
         
         int write = 0, read =0;

        for (int k = 0; k < 4; k++) {
           
            int dr = dx[k], dc = dy[k];
            int startR = dr == 1 ? m - 1 : 0, endR = dr == 1 ? -1 : m, stepR = -dr ;
            int startC = dc == 1 ? n - 1 : 0, endC = dc == 1 ? -1 : n, stepC = -dc;

            int[][] len0 = len0s[k], len2 = len2s[k];

            for (int r = startR; r != endR; r += stepR) {
                for (int c = startC; c != endC; c += stepC) {
                    if(grid[r][c] == 1) {
                         if(k == 0)
                             que[write++] = r << 9 | c;
                         continue;
                    }
                    int nr = r + dr, nc = c + dc;

                    if (grid[r][c] == 0)
                        len0[r][c] = (nr >= 0 && nr < m && nc >= 0 && nc < n) ? 1 + len2[nr][nc] : 1;
                    else
                        len2[r][c] = (nr >= 0 && nr < m && nc >= 0 && nc < n) ? 1 + len0[nr][nc] : 1;
                }
            }
        }
        
        int max = Math.max(m, n);
        int ans = 0;

        while(read < write){

                int cur = que[read++], r = cur >> 9, c = cur & ((1 << 9) - 1);

                for (int k = 0; k < 4; k++) {

                    int dr = dx[k], dc = dy[k], turnK = (k + 1) & 3;
                    int segLen = 1, len = segLen;
                    for (int x = r + dr, y = c + dc ; ; x += dr, y += dc) {

                        if (x < 0 || x >= m || y < 0 || y >= n) break;
                        if (grid[x][y] != (segLen & 1) * 2) break;
                        
                        int secondLeg = (( segLen & 1) == 1) ? len2s[turnK][x][y] : len0s[turnK][x][y];
                        len = Math.max(len, Math.max(segLen + secondLeg, ++segLen));
                    }

                    ans = Math.max(ans, len);
                }
            

            if(ans == max) return ans;
        }

        return ans;
    }
}