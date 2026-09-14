class Solution {
    public int largestUniqueNumber(int[] nums) {
        int[] occurrances = new int[1002];
        for(int num : nums) {
            occurrances[num]++;
        }
        int max = -1;
        if(nums.length < 1000) {
            for(int i = 0; i < nums.length; i++) {
                if(occurrances[nums[i]] == 1) {
                    max = Math.max(max, nums[i]);
                }
            }
        } else {
            for(int i = occurrances.length - 1; i >= 0; i--) {
                if(occurrances[i] == 1) return i;
            }
        }
        return max;
    }
}