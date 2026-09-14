class Solution {
    public int numSquarefulPerms(int[] nums) {
        return permutate(nums, 0);
    }
    int permutate(int[] nums, int idx){
        if(idx>=nums.length){
            return 1;
        }
        int sum = 0;
        for(int i=idx; i<nums.length; i++){
            if(!shouldSwap(nums, idx, i)) continue;
            swap(nums, idx, i);
            if(idx==0 ||  sumSquare(nums, idx)) sum += permutate(nums, idx+1);
            swap(nums, idx, i);
        }
        return sum;
    }
    boolean shouldSwap(int[] nums, int index, int ii){
        for(int i=index; i<ii; i++){
            if(nums[i]==nums[ii]) return false;
        }
        return true;
    }
    void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    boolean sumSquare(int[] nums, int idx){
        int sq = (int) Math.sqrt(nums[idx] + nums[idx-1]);
        return (sq*sq)==(nums[idx]+nums[idx-1]);
    }
}