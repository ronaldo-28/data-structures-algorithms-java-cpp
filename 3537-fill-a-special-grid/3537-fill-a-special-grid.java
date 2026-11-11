class Solution {
        public int[][] specialGrid(int n) {
            if (n==0) return new int[][]{{0}};
            int d = 1, x = 1;
            for (int i = 0; i < n; i++) {
                d *= 4;
                x *= 2;
            }
            int[][] ans = new int[x][x];
            fill(ans, 0, d - 1, 0, 0, x - 1, x - 1, d, x);
            return ans;
        }

        private void fill(int[][] a, int f, int t, int fX, int fY, int tX, int tY, int d, int dX) {
            if (d == 4) {
                a[fX][fY] = t;
                a[fX][tY] = t - 3;
                a[tX][fY] = t - 1;
                a[tX][tY] = t - 2;
                return;
            }
            d/=4;
            dX/=2;
            fill(a, f, f + d - 1, fX, fY + dX, tX - dX, tY, d, dX);
            fill(a, f + d, f + 2 * d - 1, fX + dX, fY + dX, tX, tY, d, dX);
            fill(a, f + 2 * d, f + 3 * d - 1, fX + dX, fY, tX, tY - dX, d , dX);
            fill(a, f + 3 * d, t, fX, fY, tX - dX, tY - dX, d , dX);
        }
    }