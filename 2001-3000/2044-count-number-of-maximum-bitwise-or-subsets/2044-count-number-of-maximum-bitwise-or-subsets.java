class Solution {
    int count=0;
    public int countMaxOrSubsets(int[] nums) {
        int maxOr=0;
        for(int a:nums) maxOr |= a;
        backtrack(nums,maxOr,0,0);
        return count;
    }
    void backtrack(int[] nums,int targetOr, int currOr,int index){
        if(currOr==targetOr){
            // for pruning the branches in backtracking
            count = count + (1<<(nums.length-index));
            return;
        }
        for(int i=index;i<nums.length;i++){
            backtrack(nums,targetOr,currOr|nums[i],i+1);
        }
    }
}