class Solution {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        long count = 0;

        int lastOutOfBoundIdx = -1;

        int lastMinK = -1;
        int lastMaxK = -1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < minK || nums[i] > maxK) {
                // we cannot form any subarray with this element
                lastOutOfBoundIdx = lastMinK = lastMaxK = i;
            } else {
                
                lastMinK = nums[i] == minK ? i : lastMinK;
                lastMaxK = nums[i] == maxK ? i : lastMaxK;

                count += (lastMinK < lastMaxK ? lastMinK : lastMaxK) - lastOutOfBoundIdx;
            }
        }

        return count;
    }
}