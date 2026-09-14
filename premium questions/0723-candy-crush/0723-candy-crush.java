class Solution {
    public int[][] candyCrush(int[][] b) {
        int R = b.length;
        int C = b[0].length;
        var keepGoing = true;
        while (keepGoing) {
            keepGoing = false;
            var re = new boolean[R][C];
            for (int r = 0; r < R; r++) {
                for (int c = 0; c + 2 < C; c++) {
                    if (b[r][c] != 0 && b[r][c] == b[r][c + 1] && b[r][c] == b[r][c + 2]) {
                        keepGoing = re[r][c] = re[r][c + 1] = re[r][c + 2] = true;
                    }
                }
            }
            for (int c = 0; c < C; c++) {
                for (int r = 0; r + 2 < R; r++) {
                    if (b[r][c] != 0 && b[r][c] == b[r + 1][c] && b[r][c] == b[r + 2][c]) {
                        keepGoing = re[r][c] = re[r + 1][c] = re[r + 2][c] = true;
                    }
                }
            }
            for (int c = 0; c < C; c++) {
                int rr = R - 1;
                for (int r = R - 1; r >= 0; r--) {
                    if (!re[r][c]) {
                        b[rr--][c] = b[r][c];
                    }
                }
                while (rr >= 0) {
                    b[rr--][c] = 0;
                }
            }
        }
        return b;
    }
}