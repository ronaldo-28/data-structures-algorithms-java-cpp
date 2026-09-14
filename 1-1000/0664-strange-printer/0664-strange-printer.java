class Solution {
    int[] data;
    int[] prevIndex;
    int[][] dp;

    public int strangePrinter(String s) {
        preprocessData(s.toCharArray(), 0, 0, new int[128]);
        int n = data.length;
        dp = new int[n][n];
        return dp(0, n - 1);
    }

    private int dp(int start, int end) {
        if (start > end) {
            return 0;
        }
        if (dp[start][end] > 0) {
            return dp[start][end];
        }
        int p = prevIndex[end];
        int min = dp(start, end - 1) + 1;
        while (p >= start) {
            min = Math.min(min, 
                dp(start, p) + dp(p + 1, end - 1)
            );
            p = prevIndex[p];
        }
        return dp[start][end] = min;
    }

    private void preprocessData(char[] raw, int i, int j, int[] map) {
        if (i == raw.length) {
            data = new int[j];
            prevIndex = new int[j];
            Arrays.fill(prevIndex, -1);
            return;
        }
        int symbol = raw[i];
        while (i + 1 < raw.length && raw[i + 1] == symbol) {
            i++;
        }
        preprocessData(raw, i + 1, j + 1, map);
        data[j] = symbol;
        if (map[symbol] > 0) {
            prevIndex[map[symbol]] = j;
        }
        map[symbol] = j;
    }
}