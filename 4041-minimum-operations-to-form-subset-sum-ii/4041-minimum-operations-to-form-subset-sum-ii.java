class Solution {
    public int minOperations(int[] nums, int sum) {
        int INF = Integer.MAX_VALUE / 2;

        int[] dp = new int[sum+1];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for(int x: nums){
            List<int[]> options = new ArrayList<>();
            int value = x;
            int divCost = 0;

            while(value > 0){
                long curr = value;
                int cost = divCost;

                while(curr<=sum){
                    options.add(new int[]{(int)curr, cost});

                    curr*=2;
                    cost++;
                }

                value/=2;
                divCost++;
            }

            int[] newDp = dp.clone();

            for(int[] option: options){
                int val = option[0];
                int ops = option[1];

                for(int s=0 ; s+val<=sum; s++){
                    if(dp[s]!=INF){
                        newDp[s + val] = Math.min(newDp[s + val], dp[s] + ops);
                    }
                }
            }

            dp = newDp;
        }

        return dp[sum] == INF ? -1: dp[sum];
    }
}