class Solution {
    char[] digits;
    Pair[][][] memo; // only for tight=false and started=true-ish

    class Pair {
        long count, sum;
        Pair(long count, long sum) {
            this.count = count;
            this.sum = sum;
        }
    }

    public int totalWaviness(int num1, int num2) {
        return (int) calc(num2) - calc(num1 - 1);
    }

    private int calc(int x) {
        if (x <= 0) return 0;

        digits = String.valueOf(x).toCharArray();

        // pos up to digits.length, prevDigit 0-10, prevDir 0-2
        memo = new Pair[digits.length][11][3];

        return (int) dfs(0, 10, 0, true, false).sum;
    }

    private Pair dfs(int pos, int prevDigit, int prevDir, boolean tight, boolean started) {
        if (pos == digits.length) {
            return started ? new Pair(1, 0) : new Pair(0, 0);
        }

        if (!tight && started && memo[pos][prevDigit][prevDir + 1] != null) {
            return memo[pos][prevDigit][prevDir + 1];
        }

        int limit = tight ? digits[pos] - '0' : 9;

        long totalCount = 0;
        long totalSum = 0;

        for (int d = 0; d <= limit; d++) {
            boolean nextTight = tight && d == limit;

            if (!started && d == 0) {
                Pair child = dfs(pos + 1, 10, 0, nextTight, false);
                totalCount += child.count;
                totalSum += child.sum;
                continue;
            }

            int nextDir = prevDir;
            int addWave = 0;

            if (!started) {
                nextDir = 0;
            } else {
                if (d > prevDigit) nextDir = 1;
                else if (d < prevDigit) nextDir = -1;
                else nextDir = 0;

                if (prevDir == 1 && nextDir == -1) addWave = 1;
                if (prevDir == -1 && nextDir == 1) addWave = 1;
            }

            Pair child = dfs(pos + 1, d, nextDir, nextTight, true);

            totalCount += child.count;
            totalSum += child.sum + (long) addWave * child.count;
        }

        Pair ans = new Pair(totalCount, totalSum);

        if (!tight && started) {
            memo[pos][prevDigit][prevDir + 1] = ans;
        }

        return ans;
    }
}