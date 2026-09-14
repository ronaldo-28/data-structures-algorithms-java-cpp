import java.util.*;

class Solution {
    public List<List<String>> solveNQueens(int n) {
        final String[] rowByCol = new String[n];
        for (int c = 0; c < n; c++) rowByCol[c] = rowString(c, n);

        final List<List<String>> out = new ArrayList<>();
        final int[] positions = new int[n];
        final long mask = (n == 64) ? ~0L : ((1L << n) - 1);

        dfs(0, n, 0L, 0L, 0L, mask, positions, rowByCol, out);
        return out;
    }

    private void dfs(int row, int n, long cols, long d1, long d2, long mask,
                     int[] positions, String[] rowByCol, List<List<String>> out) {
        if (row == n) {
            List<String> sol = new ArrayList<>(n);
            for (int r = 0; r < n; r++) sol.add(rowByCol[positions[r]]);
            out.add(sol);
            return;
        }

        long blocked = cols | d1 | d2;
        long avail = (~blocked) & mask;

        while (avail != 0) {
            long p = avail & -avail;
            avail -= p;

            int col = Long.numberOfTrailingZeros(p);
            positions[row] = col;

            dfs(row + 1, n,
                cols | p,
                (d1 | p) << 1,
                (d2 | p) >>> 1,
                mask, positions, rowByCol, out);
        }
    }

    private static String rowString(int qCol, int n) {
        char[] row = new char[n];
        Arrays.fill(row, '.');
        row[qCol] = 'Q';
        return new String(row);
    }
}