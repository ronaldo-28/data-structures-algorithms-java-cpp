class Solution {
    public int minSkips(int[] dist, int speed, int hoursBefore) {
        long hours = 0;
        for(int d: dist){
            hours += (d + speed -1 )/speed;
        }

        if(hours <= hoursBefore){
            return 0;
        }
        int n = dist.length;
        int dp[] = new int[n];

        for(int i=0;i<n;i++){
            for(int j=n-1;j>=0;j--){
                int notSkip = ((dp[j] + dist[i] + speed - 1)/speed )* speed;
                int skip = (j-1 >=0) ? dp[j-1] + dist[i] : notSkip;

                if(i == n-1){
                    dp[j] += dist[i];
                }else{
                    dp[j] = Math.min(skip, notSkip);
                }
            }
        }

        for(int i=0;i<n;i++){
            if(dp[i] <= hoursBefore * speed) return i;
        }

        return -1;
    }
}