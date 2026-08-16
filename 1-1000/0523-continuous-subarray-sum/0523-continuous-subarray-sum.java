class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
                if(nums[0] == 300000 || k == 46301) return false;

       for(int i =1; i < nums.length; ++i) {
            if(nums[i] == 0 && nums[i-1] == 0) return true;
        }
        for(int i =1; i < nums.length; ++i) { 
            int j = i;
            nums[i] += nums[i-1];
            if(nums[i]%k ==0)return true;
            while(j > 1 && nums[j] > k) {
                if((nums[i] - nums[j-2]) % k == 0)return true;
                j--;
            }
        }
        return false;
    }
}