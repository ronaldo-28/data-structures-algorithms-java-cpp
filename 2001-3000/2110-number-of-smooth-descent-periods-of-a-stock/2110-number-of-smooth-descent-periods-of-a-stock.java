class Solution {
    public long getDescentPeriods(int[] prices) {
        long ans = 1;
        int n = prices.length;
        int smooth = 1;

        for(int i = 1; i < n; i++){
            if(prices[i-1]-prices[i] == 1){
                smooth++;
            }else{
                smooth = 1;
            }

            ans += smooth;
        }

        return ans;
    }
}