class Solution {

    String[][] dp;

    public String encode(String s) {
        int n = s.length();

        dp = new String[n][n];
        // dp[i][j] = current best encoded form of s[i .. j]

        for (int l=1 ; l<=n ; l++) {
            for (int i=0 ; i<=n-l ; i++) {
                int j = i + l - 1;
                // check 所有 s.substring

                dp[i][j] = encodeRepeat(s.substring(i, i + l), i, l);

                if (dp[i][j].length() < l) {
                    // 確定 dp[i][j] 就是目前 encoded form of s[i .. j] 的最佳解
                    continue;
                }

                // 否則 dp[i][j].length() >= l
                // i.e. 目點 dp[i][j] 可能還不是最佳解
                // i.e. 長度相同並不表示 dp[i][j] 就是當下最佳解
                // 也就是目前 dp[i][j] 中間可能可以再切兩半組出目前更好的最佳解
                // 所以進行以下切兩半作業
                int min = l, idx = -1;

                for (int k=i ; k<j ; k++) {
                    int cur = dp[i][k].length() + dp[k + 1][j].length();
                    if (min > cur) {
                        min = cur;
                        idx = k;
                    }
                }

                if (idx != -1) {
                    // 確定可再切兩半找出目前更好的最佳解
                    dp[i][j] = dp[i][idx] + dp[idx + 1][j];
                }
            }
        }

        return dp[0][n-1];
    }

    private String encodeRepeat(String sub, int i, int l) {
        if (l < 5) {
            return sub;
        }

        int r = (sub + sub).indexOf(sub, 1);
        // 都用 s.substrig 去找有沒辦法組出 encoded repeat
        // 若真的能有效縮短長度
        // 就將 encoded repeat 回傳

        if (r < l) {
            StringBuilder sb = new StringBuilder();
            sb.append(l/r).append('[').append(dp[i][i + r - 1]).append(']');

            if (sb.length() < l) {
                return sb.toString();
            }
        }

        return sub;
    }
}