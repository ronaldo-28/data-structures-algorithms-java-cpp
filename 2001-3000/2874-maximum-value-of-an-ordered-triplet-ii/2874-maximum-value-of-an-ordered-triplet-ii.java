class Solution {
    public long maximumTripletValue(int[] nums) {
        long ans = 0;
        int maxVal = 0, maxDiff = 0;
        
        for (int num : nums) {
            ans = Math.max(ans, (long) maxDiff * num);
            maxDiff = Math.max(maxDiff, maxVal - num);
            maxVal = Math.max(maxVal, num);
        }
        return ans;
    }
}