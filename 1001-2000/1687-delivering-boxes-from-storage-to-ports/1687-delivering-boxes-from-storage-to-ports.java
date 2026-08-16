class Solution {
    public int boxDelivering(int[][] box, int __, int max, int limit) {
        int n = box.length;
        int[] dp = new int[n+1];
        int weight = 0;
        int cost = 2;
        for (int l = 0, r = 0; r < n; r++){
            weight += box[r][1];
            if (r!=0 && box[r][0] != box[r-1][0]) cost++;
            while(r-l >= max || weight > limit || (l < r && dp[l] == dp[l+1])){
                weight -= box[l][1];
                if (box[l+1][0] != box[l][0]) cost--;
                l++;
            }
            dp[r+1] = cost + dp[l];
        }
        return dp[n];
    }
}