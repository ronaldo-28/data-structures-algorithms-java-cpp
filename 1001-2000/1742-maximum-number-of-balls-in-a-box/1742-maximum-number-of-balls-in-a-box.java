class Solution {
    char[] s1;
    char[] s2;
    public int countBalls(int lowLimit, int highLimit) {
        lowLimit -= 1;
        s1 = String.valueOf(lowLimit).toCharArray();
        s2 = String.valueOf(highLimit).toCharArray();
        int n = s2.length;
        Integer[][] memo1 = new Integer[n][9 * n + 1];
        Integer[][] memo2 = new Integer[n][9 * n + 1];
        int res = 0;
        for(int i = 1; i <= 9 * n; i++) {
            int cnt = helper(0, i, s2, true, memo1) - helper(0, i, s1, true, memo2);
            res = Math.max(res, cnt);
        }
        return res;
    }

    int helper(int i, int sum, char[] s, boolean isLimit, Integer[][] memo) {
        if (sum < 0) return 0;
        if (i == s.length) {
            return sum == 0 ? 1 : 0;
        }

        if(!isLimit && memo[i][sum] != null) {
            return memo[i][sum];
        } 

        int res = 0;
        int max = isLimit ? s[i] - '0' : 9;
        for(int d = 0; d <= max; d++) {
            res += helper(i + 1, sum - d, s, isLimit && d == max, memo);
        }
        if(!isLimit) {
            memo[i][sum] = res;
        }
        return res;
    }
}