class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }

        if(n > 10){
          n = 10; // There won't be unique digits for n > 10
        }
        int[] dp = new int[n + 1];
        dp[0] = 1; // Base case
        dp[1] = 10; // 0 to 9

        for (int i = 2; i <= n; i++) {
          int uniqueDigits = 9;
            for(int j = 1; j < i; j++){
                uniqueDigits *= (10 - j);
            }
            dp[i] = dp[i-1] + uniqueDigits;
        }
        return dp[n];
    }
}