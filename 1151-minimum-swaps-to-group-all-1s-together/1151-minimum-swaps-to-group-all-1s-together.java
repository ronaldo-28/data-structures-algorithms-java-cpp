class Solution {
    public int minSwaps(int[] nums) {
        int totalOnes = 0;
        for(int x : nums) totalOnes += x;
        
        int currOnes = 0; // track current first window ones.
        for (int i = 0; i < totalOnes; ++i) {
            currOnes += nums[i];
        }
        
        int res = (totalOnes - currOnes);
        for(int i = totalOnes; i <nums.length; i++) { // process rest eles.
            currOnes += nums[i] - nums[i - totalOnes];
            res = Math.min(res, totalOnes - currOnes);
        }
        
        return res;
    }
}