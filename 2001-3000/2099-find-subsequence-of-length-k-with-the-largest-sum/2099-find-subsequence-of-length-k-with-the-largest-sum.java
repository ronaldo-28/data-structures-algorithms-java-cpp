class Solution {
   public int[] maxSubsequence(int[] nums, int k) {
       int[] result = new int[k];
       int resultSize = 0;
       int left = -100000;
       int right = 100000;
       int pivotCount = 0;
       int upperPivotCount = 0;

       while (left <= right) {
           int mid = (left + right) / 2;
           if (!isApplicable(nums, k, mid)) {
               right = mid - 1;
           } else {
               left = mid + 1;
           }
       }

       for (int num : nums) {
           if (num > right) {
               upperPivotCount ++;
           }
       }

       pivotCount = k - upperPivotCount;

       for (int i = 0; resultSize < k; i ++) {
           if (nums[i] > right) {
               result[resultSize ++] = nums[i];
           } else if (nums[i] == right && pivotCount > 0) {
               result[resultSize ++] = nums[i];
               pivotCount --;
           }
       }

       return result;
   }

   private boolean isApplicable(int[] nums, int k, int pivot) {
       for (int num : nums) {
           if (num >= pivot) {
               k --;
           }
       }
       return k <= 0;
   }
}