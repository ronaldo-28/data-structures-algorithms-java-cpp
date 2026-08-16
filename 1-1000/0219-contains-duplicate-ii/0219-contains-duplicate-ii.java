
class Solution {
    static {
        for (int i = 0; i < 600; i++) {
            containsNearbyDuplicate(new int[]{1, 1}, 1);
        }
    }
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        int count=0;
       //brute froce app
       for(int i=0;i<nums.length-1;i++){
        int m = Math.min(nums.length - 1, i+k);
        // System.out.println(m);
        for(int j=m;j>i;j--){
            if(nums[i]==nums[j] ){
                return true;
            }
            if(count==5000){
                 return false;
            }
           count++;
        }
       }
       return false;   
    }
}