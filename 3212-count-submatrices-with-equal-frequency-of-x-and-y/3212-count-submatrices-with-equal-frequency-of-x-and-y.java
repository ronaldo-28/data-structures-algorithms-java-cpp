class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int clen = grid[0].length;
        int[] psum = new int[clen];
        int leftmostXCol = clen;
        int res = 0;

        for (char[] row : grid) {
            int csum = 0;
            for (int c = 0; c < clen; ++c) {
                char ch = row[c];
                if (ch == 'X') {
                    csum++;
                    if (c < leftmostXCol) leftmostXCol = c; 
                } else if (ch == 'Y') {
                    csum--;
                }

                if ((psum[c] += csum) == 0 && c >= leftmostXCol) ++res;
            }
        }

        return res;
    }
}