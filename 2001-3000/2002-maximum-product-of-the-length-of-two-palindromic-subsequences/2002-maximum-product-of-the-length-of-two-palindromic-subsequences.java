class Solution {
    int n;
    boolean[][] valid;
    public int maxProduct(String s) {
        n = s.length();
        int m = (1 << n) - 1;
        valid = new boolean[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) valid[i][j] = s.charAt(i) == s.charAt(j);
        }
        int[] dp = new int[m];
        for(int i = 1; i < m; i++) dp[i] = calcSize(s, i);
        int ans = 0;
        for(int i = m - 1; i > 0; i--) {
            int x = dp[i];
            if(x * (n - x) > ans) {
                int mask = m ^ i;
                for(int j = mask; j > 0; j = (j - 1) & mask) ans = Math.max(ans, x * dp[j]);
            }
        }
        return ans;
    }
    private int calcSize(String s, int mask) {
        int left = 0, right = n, ans = 0;
        while(left <= right) {
            if((mask & (1 << left)) == 0) left++;
            else if((mask & (1 << right)) == 0) right--;
            else if(!valid[left++][right--]) return 0;
            else ans += 2;
        }
        return left - right == 2 ? ans - 1 : ans;
    }
}