class Solution {
    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        int[][] dp = new int[k + 1][n];
        Map<Integer, Integer> valToIndex = new HashMap<>();
        int[] compressed = new int[n];
        int uniqueCount = 0;
        
        for (int i = 0; i < n; i++) {
            if (!valToIndex.containsKey(nums[i])) {
                valToIndex.put(nums[i], uniqueCount++);
            }
            compressed[i] = valToIndex.get(nums[i]);
        }
        
        int maxLen = 0;
        int[] maxWithPrev = new int[k + 1];
        int[][] maxForVal = new int[uniqueCount][k + 1];
        
        for (int i = 0; i < n; i++) {
            int val = compressed[i];
            for (int j = k; j >= 0; j--) {
                int len = 1;
                len = Math.max(len, maxForVal[val][j] + 1);
                if (j > 0) {
                    len = Math.max(len, maxWithPrev[j - 1] + 1);
                }
                
                dp[j][i] = len;
                maxForVal[val][j] = Math.max(maxForVal[val][j], len);
                maxWithPrev[j] = Math.max(maxWithPrev[j], len);
                maxLen = Math.max(maxLen, len);
            }
        }
        
        return maxLen;
    }
}