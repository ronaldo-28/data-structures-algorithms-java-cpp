import java.util.*;

class Solution {
    long[][][] memo = new long[20][200][2];
    String s;
    int OFFSET = 100;

    long dp(int idx, int bal, int tight, int total) {
        if (bal < 0 || bal >= 200) return 0;
        if (idx == total) return bal == OFFSET ? 1 : 0;
        if (idx >= 25) return 0;

        if (memo[idx][bal][tight] != -1)
            return memo[idx][bal][tight];

        long ans = 0;
        int limit = (tight == 1) ? (s.charAt(idx) - '0') : 9;

        for (int d = 0; d <= limit; d++) {
            int nextTight = (tight == 1 && d == limit) ? 1 : 0;
            int nextBal = bal + ((idx % 2 == 0) ? d : -d);
            ans += dp(idx + 1, nextBal, nextTight, total);
        }

        return memo[idx][bal][tight] = ans;
    }

    long solve(long n) {
        if (n < 10) return 0;

        String num = Long.toString(n);
        int len = num.length();
        long total = 0;

        // Smaller lengths
        for (int l = 2; l < len; l++) {
            for (long[][] layer : memo)
                for (long[] row : layer)
                    Arrays.fill(row, -1);

            s = "9".repeat(l);
            for (int d = 1; d <= 9; d++) {
                total += dp(1, OFFSET + d, 0, l);
            }
        }

        // Same length
        s = num;
        int first = s.charAt(0) - '0';

        for (int d = 1; d <= first; d++) {
            for (long[][] layer : memo)
                for (long[] row : layer)
                    Arrays.fill(row, -1);

            int tight = (d == first) ? 1 : 0;
            total += dp(1, OFFSET + d, tight, len);
        }

        return total;
    }

    public long countBalanced(long low, long high) {
        return solve(high) - solve(low - 1);
    }
}