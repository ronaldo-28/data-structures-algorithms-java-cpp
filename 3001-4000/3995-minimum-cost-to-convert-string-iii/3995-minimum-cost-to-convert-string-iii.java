class Solution {
    public int minCost(String source, String target, List<List<String>> rules, int[] costs) {
        int n = source.length();
        if (n != target.length()) return -1;

        long INF = 1L << 60;
        long[] dp = new long[n + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        int m = rules.size();

        for (int i = 0; i < n; i++) {
            if (dp[i] == INF) continue;

            if (source.charAt(i) == target.charAt(i)) {
                dp[i + 1] = Math.min(dp[i + 1], dp[i]);
            }

            for (int r = 0; r < m; r++) {
                String pattern = rules.get(r).get(0);
                String repl = rules.get(r).get(1);
                int len = pattern.length();

                if (i + len > n) continue;

                boolean ok = true;
                int wildcards = 0;

                for (int k = 0; k < len; k++) {
                    char pc = pattern.charAt(k);
                    char sc = source.charAt(i + k);
                    char tc = target.charAt(i + k);

                    if (pc == '*') {
                        wildcards++;
                    } else if (pc != sc) {
                        ok = false;
                        break;
                    }

                    if (repl.charAt(k) != tc) {
                        ok = false;
                        break;
                    }
                }

                if (!ok) continue;

                long cost = dp[i] + costs[r] + wildcards;
                dp[i + len] = Math.min(dp[i + len], cost);
            }
        }

        return dp[n] == INF ? -1 : (int) dp[n];
    }
}