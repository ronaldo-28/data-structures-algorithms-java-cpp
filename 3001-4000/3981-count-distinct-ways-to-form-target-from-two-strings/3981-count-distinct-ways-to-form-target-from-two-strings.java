class Solution {
    static final int M = 1000000007;

    void add(int[] x, int idx, int y) {
        x[idx] += y;
        if (x[idx] >= M) {
            x[idx] -= M;
        }
    }

    void sub(int[] x, int idx, int y) {
        x[idx] -= y;
        if (x[idx] < 0) {
            x[idx] += M;
        }
    }

    int cal(String word, String target) {
        int n = word.length();
        int[] dp = new int[n + 1];
        
        for (int i = 0; i <= n; i++) {
            dp[i] = 1;
        }

        for (char c : target.toCharArray()) {
            int[] temp = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                temp[i] = temp[i - 1];

                if (word.charAt(i - 1) == c) {
                    add(temp, i, dp[i - 1]);
                }
            }

            dp = temp;
        }

        return dp[n];
    }

    public int interleaveCharacters(String word1, String word2, String target) {
        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            Arrays.fill(dp[i], 1);
        }

        for (char c : target.toCharArray()) {
            int[][] temp = new int[m + 1][n + 1];

            for (int i = 0; i <= m; i++) {
                for (int j = (i > 0 ? 0 : 1); j <= n; j++) {

                    if (i > 0) {
                        // Not use i
                        temp[i][j] += temp[i - 1][j];
                        if (temp[i][j] >= M) {
                            temp[i][j] -= M;
                        }

                        // Use i and use j but i is the last
                        if (c == word1.charAt(i - 1)) {
                            temp[i][j] += dp[i - 1][j];
                            if (temp[i][j] >= M) {
                                temp[i][j] -= M;
                            }

                            if (j > 0) {
                                temp[i][j] -= dp[i - 1][j - 1];
                                if (temp[i][j] < 0) {
                                    temp[i][j] += M;
                                }
                            }
                        }
                    }

                    if (j > 0) {
                        // Not use j
                        temp[i][j] += temp[i][j - 1];
                        if (temp[i][j] >= M) {
                            temp[i][j] -= M;
                        }

                        // Use i and use j but j is the last
                        if (c == word2.charAt(j - 1)) {
                            temp[i][j] += dp[i][j - 1];
                            if (temp[i][j] >= M) {
                                temp[i][j] -= M;
                            }

                            if (i > 0) {
                                temp[i][j] -= dp[i - 1][j - 1];
                                if (temp[i][j] < 0) {
                                    temp[i][j] += M;
                                }
                            }
                        }
                    }

                    if (i > 0 && j > 0) {
                        temp[i][j] -= temp[i - 1][j - 1];
                        if (temp[i][j] < 0) {
                            temp[i][j] += M;
                        }
                    }
                }
            }

            dp = temp;
        }

        int r = dp[m][n];

        r -= cal(word1, target);
        if (r < 0) {
            r += M;
        }

        r -= cal(word2, target);
        if (r < 0) {
            r += M;
        }

        return r;
    }
}