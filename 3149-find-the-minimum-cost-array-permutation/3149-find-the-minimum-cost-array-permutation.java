class Solution {
    public int[] findPermutation(int[] nums) {
        int n = nums.length - 1, m = 1 << n; //pretend like the permutation is 1-indexed cuz 0's pos is fixed
        int[][] dp = new int[n][m]; //store best value
        int[][] path = new int[n][m]; //store best index

        //fill up dp array with INF, and initialize base case as dp[i][1 << i] = score(i + 1, nums[0])
        for(int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            dp[i][1 << i] = Math.abs(i + 1 - nums[0]);
            path[i][1 << i] = -1;
        }

        //traverse all states dp[prevIndex][bitmask]
        for(int i = 1; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int filter = 1 << j;
                if((filter & i) == 0) continue;
                for(int k = 0; k < n; k++) {
                    if(j == k || (i & (1 << k)) == 0) continue;
                    int val = dp[k][i ^ filter] + Math.abs(j + 1 - nums[k + 1]);
                    if(val < dp[j][i]) { //track the best value and it's path
                        dp[j][i] = val;
                        path[j][i] = k;
                    }
                }
            }
        }

        //loop through dp[i][full_mask] and find the best path
        int min = Integer.MAX_VALUE, index = -1, mask = m - 1;
        for(int i = 0; i < n; i++) {
            int val = dp[i][mask] + nums[i + 1]; //add the final score(nums[i], 0), as the last element loops back to 0
            if(val < min) {
                min = val;
                index = i;
            }
        }
        
        //go through the best path and make it into an array
        int[] ans = new int[n + 1];
        for(int i = 0; i < n; i++) {
            ans[i + 1] = index + 1;
            int filter = 1 << index;
            index = path[index][mask];
            mask ^= filter;
        }
        return ans;
    }
}