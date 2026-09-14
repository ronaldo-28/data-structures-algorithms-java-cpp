public class Solution {
    public int[][] divideArray(int[] nums, int k) {
        int n = nums.length;
        
        // Step 1: Find the minimum and maximum boundaries to size our bucket array
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num < min) min = num;
            if (num > max) max = num;
        }
        
        // Step 2: Build the frequency counting bucket array
        int[] counts = new int[max - min + 1];
        for (int num : nums) {
            counts[num - min]++;
        }
        
        int[][] result = new int[n / 3][3];
        int groupIdx = 0;
        int itemIdx = 0;
        
        // Step 3: Iterate through the buckets to collect triplets sequentially
        for (int i = 0; i < counts.length; i++) {
            while (counts[i] > 0) {
                // Map the bucket index back to the actual integer value
                int actualValue = i + min;
                
                // Add the value to the current triplet row
                result[groupIdx][itemIdx] = actualValue;
                counts[i]--;
                itemIdx++;
                
                // Once a triplet of size 3 is fully formed, validate it
                if (itemIdx == 3) {
                    // Check if the difference between max and min of this triplet is > k
                    if (result[groupIdx][2] - result[groupIdx][0] > k) {
                        return new int[0][0]; // Impossible configuration
                    }
                    // Move to the next group row and reset item pointer
                    groupIdx++;
                    itemIdx = 0;
                }
            }
        }
        
        return result;
    }
}