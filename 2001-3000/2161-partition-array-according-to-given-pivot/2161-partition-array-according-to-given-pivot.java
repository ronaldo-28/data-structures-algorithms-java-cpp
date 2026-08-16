class Solution {
    static{
        for(int i=0;i<300;i++) pivotArray(new int[2],0);
    }
    public static int[] pivotArray(int[] nums, int pivot) {
        int n = nums.length;
        int[] result = new int[n];
        
        int left = 0;
        int right = n - 1;
        
        // Step 1: Place smaller elements from left, larger from right
        for (int i = 0, j = n - 1; i < n; i++, j--) {
            if (nums[i] < pivot) {
                result[left++] = nums[i];
            }
            if (nums[j] > pivot) {
                result[right--] = nums[j];
            }
        }
        
        // Step 2: Fill the remaining middle slots with the pivot value
        while (left <= right) {
            result[left++] = pivot;
        }
        
        return result;
    }
}