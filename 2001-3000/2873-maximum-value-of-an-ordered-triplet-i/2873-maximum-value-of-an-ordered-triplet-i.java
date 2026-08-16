class Solution {
    public long maximumTripletValue(int[] nums) {

        long ans = 0;

        int maxI = nums[0];
        int maxDiff = 0;

        for (int k = 1; k < nums.length; k++) {

            ans = Math.max(ans, (long) maxDiff * nums[k]);

            maxDiff = Math.max(maxDiff, maxI - nums[k]);

            maxI = Math.max(maxI, nums[k]);
        }

        return ans;
    }
}