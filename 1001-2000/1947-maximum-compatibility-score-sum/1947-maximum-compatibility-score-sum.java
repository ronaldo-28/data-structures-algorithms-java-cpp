class Solution {
    int[][] cost;
    Integer[] dp;
    int dp(int mask,int n){
        int i = Integer.bitCount(mask);
        if(i==n) return 0;
        if(dp[mask]!=null) return dp[mask];
        int ans = 0;

        for(int j=0;j<n;j++){
            if((mask & 1 << j) == 0){
                int newMask = mask | 1<<j;

                ans = Math.max(ans, cost[i][j] + dp(newMask,n));
            }
        }

        return dp[mask] = ans;
    }
    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        int n = students.length;
        int m = students[0].length;

        cost = new int[n][n];

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                int count = 0;
                for(int k=0;k<m;k++){
                    if(students[i][k] == mentors[j][k]){
                        count++;
                    } 
                }
                cost[i][j] = count;
            }
        }

        // for(int i=0;i<n;i++){
        //     for(int j=0;j<n;j++){
        //         System.out.print(cost[i][j] + " ");
        //     }
        //     System.out.println();
        // }

         dp = new Integer[1<<n];

        return dp(0,n);
    }
}