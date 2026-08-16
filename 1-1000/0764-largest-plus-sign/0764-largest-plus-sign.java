class Solution {
    static final int[][] arr = new int[500][500];
    static int currentMine = 1_000_000;

    public int orderOfLargestPlusSign(final int n, int[][] mines) {
        ++currentMine;
        for (var m : mines) {
            arr[m[0]][m[1]] = currentMine;
        }
        for (int row = 0; row < n; row++) {
            final int[] rowArr = arr[row];
            for (int col = 0; col < n; ) {
                final int nextCol = nextMineY(n, rowArr, col);
                final int c1 = col - 1;
                final int max = (nextCol - c1) / 2;
                for (int i = 1; i <= max; i++) {
                    rowArr[c1 + i] = i;
                    rowArr[nextCol - i] = i;
                }
                col = nextCol + 1;
            }
        }
        int res = 0;
        for (int col = 0; col < n; col++) {
            for (int row = 0; row < n;) {
                final int nextRow = nextMineX(n, row, col);
                final int r1 = row - 1;
                final int max = (nextRow - r1) / 2;
                for (int i = res + 1; i <= max; i++) {
                    int m = Math.max(arr[r1 + i][col], arr[nextRow - i][col]);
                    if (m > res) {
                        res = Math.min(m, i);
                    }
                }
                row = nextRow + 1;
            }
        }
        return res;
    }

    static int nextMineX(final int n, int x, int y) {
        for (int nx = x; nx < n; nx++) {
            if (arr[nx][y] == currentMine) {
                return nx;
            }
        }
        return n;
    }

    static int nextMineY(final int n, final int[] arr, int y) {
        for (int ny = y; ny < n; ny++) {
            if (arr[ny] == currentMine) {
                return ny;
            }
        }
        return n;
    }
}