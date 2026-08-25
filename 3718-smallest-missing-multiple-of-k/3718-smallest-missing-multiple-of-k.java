class Solution {
    public int missingMultiple(int[] nums, int k) {
        for(int multiple=k;;multiple+=k){
            boolean found=false;
            for(int i=0;i<nums.length;i++){
                if(nums[i]==multiple){
                    found=true;
                    break;
                }
            }
            if(!found){
            return multiple;
            }
        }
        
    }
}