class Solution {
    public int minPatches(int[] nums, int n) {
        int patches = 0;
        long reach = 0; // The maximum number we can reach with nums so far.

        int index = 0;
        while (reach < n) {
          if(index < nums.length && nums[index] <= reach+1){
             reach += nums[index];
             index++;
          } else {
            patches++;
            reach += reach + 1;
          }
        }
         return patches;
    }
}