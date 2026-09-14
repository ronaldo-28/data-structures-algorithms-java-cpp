import java.util.Arrays;

class Solution {
    public long countOperationsToEmptyArray(int[] nums) {
        int n = nums.length;
        
        // Pack value (high 32 bits) and original index (low 32 bits) into a primitive long.
        // This eliminates object allocations (like Integer[] or custom objects) entirely.
        long[] packed = new long[n];
        for (int i = 0; i < n; i++) {
            packed[i] = ((long) nums[i] << 32) | i;
        }
        
        // Primitive sort executes at pure hardware speeds (Dual-Pivot Quicksort)
        Arrays.sort(packed);
        
        // The array requires at least 'n' direct removal operations baseline
        long totalOperations = n;
        
        // Extract the original index of the first (smallest) element
        int prevIndex = (int) (packed[0] & 0xFFFFFFFFL);
        
        // Linearly sweep through elements in ascending order
        for (int i = 1; i < n; i++) {
            int currIndex = (int) (packed[i] & 0xFFFFFFFFL);
            
            // If the current element's original position appears BEFORE the previous element,
            // a wrap-around pass was forced. Add the remaining unremoved elements to the cost.
            if (currIndex < prevIndex) {
                totalOperations += (n - i);
            }
            
            prevIndex = currIndex;
        }
        
        return totalOperations;
    }
}