class Solution {
    public int maximizeWin(int[] prizes, int k) {
        
        int n = prizes.length;
        if(n < 3 || prizes[n - 1] - prizes[0] <= k * 2) return n;
        int[] dp = new int[n + 1];
        int left = 0, ans = 0;
        for(int i = 0; i < n; i++) {
            while(prizes[i] - prizes[left] > k) left++;
            int val = i-left+1;
            dp[i + 1] = Math.max(dp[i], val);
            ans = Math.max(ans,val+dp[left]);
        }
        return ans;
    }
}
