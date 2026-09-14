class Solution {
    public int minimumCost(String sentence, int k) {
        if(sentence.length() <= k)
            return 0;
        int[] dp = new int[sentence.length() + 1];
        String[] parts = sentence.split(" ");
        
        for(int i = 1; i <= parts.length; i++){
            int len = 0;
            dp[i] = Integer.MAX_VALUE;
            for(int j = i; j > 0; j--){
                len += parts[j - 1].length();
                if(j < i){
                    len++;
                }
                if(len > k){
                    break;
                }
                int curr = ((k - len) * (k - len));
                if(i == parts.length){
                    curr = 0;
                }
                dp[i] = Math.min(dp[i], curr + dp[j - 1]);
            }
        }
        return dp[parts.length];
    }
}