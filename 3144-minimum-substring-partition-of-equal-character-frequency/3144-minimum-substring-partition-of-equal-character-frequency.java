import java.util.Arrays;

class Solution {
    public int minimumSubstringsInPartition(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        
        for (int i = 1; i <= n; i++) {
            int[] freq = new int[26];
            int unique = 0;
            int maxFreq = 0;
            
            for (int j = i; j > 0; j--) {
                int c = arr[j - 1] - 'a';
                if (freq[c] == 0) unique++;
                freq[c]++;
                if (freq[c] > maxFreq) maxFreq = freq[c];
                
                // If substring j-1 to i-1 is balanced
                if (maxFreq * unique == (i - j + 1)) {
                    if (dp[j - 1] != Integer.MAX_VALUE && dp[j - 1] + 1 < dp[i]) {
                        dp[i] = dp[j - 1] + 1;
                    }
                }
            }
        }
        
        return dp[n];
    }
}