class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int hash[] = new int[n];
        int dp[] = new int[n];        

        int max = 0, maxIndex = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            hash[i] = i;
            int limit = (nums[i] + 1) / 2;
            for (int j = 0; j < i && nums[j] <= limit; j++) {
                if (nums[i] % nums[j] == 0 && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    if (max < dp[i]) {
                        max = dp[i];
                        maxIndex = i;
                    }
                    hash[i] = j;
                }
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(nums[maxIndex]);
        while (hash[maxIndex] != maxIndex) {
            maxIndex = hash[maxIndex];
            ans.add(nums[maxIndex]);
        }
        return ans;
    }
}