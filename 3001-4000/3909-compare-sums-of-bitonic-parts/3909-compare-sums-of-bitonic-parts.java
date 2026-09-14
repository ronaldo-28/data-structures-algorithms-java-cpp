class Solution {
    public int compareBitonicSums(int[] nums) {
        int max=nums[0];
        int ind=0;
       for(int i=0;i<nums.length;i++){
           if(max<nums[i]){
               max=nums[i];
               ind=i;
           }
       }
        long ass=0;
        long des=0;
       for(int i=0;i<=ind;i++){
           ass+=nums[i];
       }
        for(int i=ind;i<nums.length;i++){
            des+=nums[i];
        }
        if(des==ass){
            return -1;
        }
        return des>ass?1:0;
    }
}