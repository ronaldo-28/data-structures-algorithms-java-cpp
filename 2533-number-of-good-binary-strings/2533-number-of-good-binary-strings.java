class Solution {
    public int goodBinaryStrings(int minLength, int maxLength, int oneGroup, int zeroGroup) {
        int mod = 1000000007;
        int[] dp = new int[maxLength + 1]; 
        // dp[i] represents number of goodBS of length i

        dp[0] = 1;
        // there is one empty goodBS since 0 is a multiple of all numbers

        int result = 0;

        for(int i = 1; i <= maxLength; i++) {
            if(i >= oneGroup) dp[i] = (dp[i] + dp[i - oneGroup]) % mod; // add oneGroup to previous goodBS
            if(i >= zeroGroup) dp[i] = (dp[i] + dp[i - zeroGroup]) % mod; // add zeroGroup to previous goodBS
            if(i >= minLength) result = (result + dp[i]) % mod;
        }

        return result % mod;
    }
}