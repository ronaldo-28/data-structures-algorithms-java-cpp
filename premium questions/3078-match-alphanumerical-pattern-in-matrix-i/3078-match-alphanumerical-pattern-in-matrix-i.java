class Solution {
    public int[] findPattern(int[][] board, String[] pattern) {
        int m = board.length, n = board[0].length;
        int r = pattern.length, c = pattern[0].length();

        for (int i = 0; i + r <= m; i++) {
            for (int j = 0; j + c <= n; j++) {
                if (check(board, pattern, i, j, r, c))
                    return new int[]{i, j};
            }
        }
        return new int[]{-1, -1};
    }

    private boolean check(int[][] board, String[] pattern, int si, int sj, int r, int c) {
        int[] map = new int[26];        // letter -> digit (+1), 0 means unassigned
        boolean[] used = new boolean[10];  // is this digit already taken by a letter?

        for (int a = 0; a < r; a++) {
            for (int b = 0; b < c; b++) {
                char pc = pattern[a].charAt(b);
                int val = board[si + a][sj + b];

                if (pc >= '0' && pc <= '9') {
                    if (pc - '0' != val) return false;   // digit must match exactly
                } else {
                    int letter = pc - 'a';
                    if (map[letter] == 0) {              // letter not yet assigned
                        if (used[val]) return false;     // but this digit is taken → fail
                        map[letter] = val + 1;           // assign (store val+1 to avoid 0 clash)
                        used[val] = true;
                    } else {                             // letter already assigned
                        if (map[letter] != val + 1) return false;  // must match its digit
                    }
                }
            }
        }
        return true;
    }
}