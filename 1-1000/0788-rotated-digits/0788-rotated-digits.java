class Solution {
    public int rotatedDigits(int n) {
        int[] digits = getDigits(n);
        Integer[][][] dp = new Integer[digits.length][2][2];

        return dfs(digits, 0, true, false, dp);
    }
    private int dfs(int[] digits, int pos, boolean tight, boolean changed, Integer[][][] dp) {
        if(pos == digits.length) {
            return changed?1:0;
        }
        int isTight = tight?1:0;
        int isChanged = changed?1:0;
        if(dp[pos][isTight][isChanged] != null) return dp[pos][isTight][isChanged];
        int count = 0;
        int limit = tight?digits[pos]:9;
        for(int d=0; d<=limit; d++) {
            if(d == 3 || d == 4 || d == 7) continue;
            boolean nextChanged = changed || (d == 2 || d == 5 || d == 6 || d == 9);
            boolean nextTight = tight && (d == limit);
            count += dfs(digits, pos +1, nextTight, nextChanged, dp);
        }
        return dp[pos][isTight][isChanged] = count;
    }
    private int[] getDigits(int x) {
        String s = String.valueOf(x);
        int n = s.length();
        int[] digits = new int[n];
        for(int i=0; i<n; i++) {
            digits[i] = s.charAt(i) - '0';
        } 
        return digits;
    }
}