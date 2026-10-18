class Solution {
    public int minDeletions(int[] nums) {
        if(nums == null || nums.length <=1){
            return 0;
        }
        int n = nums.length;
        int ans = 0;
        int last = nums[0];
        for(int i=1;i<n;i++) {
            if(nums[i] == last){
                ans++;
            } else {
                last = nums[i];
            }
        }
        return ans;
    }
}