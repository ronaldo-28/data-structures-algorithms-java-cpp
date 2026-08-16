class Solution {
    public int maximumUniqueSubarray(int[] nums) {

        int[] lastIndex = new int[10001];
        for (int i = 0; i < lastIndex.length; i++) {
            lastIndex[i] = -1;
        }
        int l = -1, sum = 0;
        int[] prefixSum = new int[nums.length+1];

        for (int r = 0; r < nums.length; r++) {
            prefixSum[r+1] = nums[r] + prefixSum[r];
            if (lastIndex[nums[r]] >= 0) {
                l = Math.max(l, lastIndex[nums[r]]);
            }
            sum = Math.max(sum, prefixSum[r+1] - prefixSum[l+1]);
            lastIndex[nums[r]] = r;
        }

        return sum;
        
    }
}