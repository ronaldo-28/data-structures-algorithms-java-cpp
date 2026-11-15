class Solution {
    public int minOperations(String word1, String word2) {
        int n = word1.length();
        char[] s = word1.toCharArray();
        char[] t = word2.toCharArray();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        int[][] cnt = new int[26][26];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int plain = cost(s, t, i, j, false, cnt);
                int rev = 1 + cost(s, t, i, j, true, cnt);
                dp[j + 1] = Math.min(dp[j + 1], dp[i] + Math.min(plain, rev));
            }
        }
        return dp[n];
    }

    int cost(char[] s, char[] t, int i, int j, boolean rev, int[][] cnt) {
        int ops = 0;
        for (int p = i; p <= j; p++) {
            int a = (rev ? s[i + j - p] : s[p]) - 'a';
            int b = t[p] - 'a';
            if (a == b) continue;
            if (cnt[b][a] > 0) {
                cnt[b][a]--;
            } else {
                cnt[a][b]++;
                ops++;
            }
        }
        for (int p = i; p <= j; p++) {
            int a = (rev ? s[i + j - p] : s[p]) - 'a';
            cnt[a][t[p] - 'a'] = 0;
        }
        return ops;
    }
}