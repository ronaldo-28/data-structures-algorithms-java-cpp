class Solution {
    public int findMax(int[] nums, int l, int r){
        int res = 0;
        for(int k = l;k<=r;k++){
            res = Math.max(res,k+nums[k]);
        }
        return res;
    }
    public int jump(int[] nums) {
        int jumps = 0;
        int l = 0;
        int r = 0;
        while(r<nums.length-1){
            int maxJump = findMax(nums,l,r);
            l=r+1;
            r = maxJump;
            jumps++;
        }
        return jumps;
    }
}