import java.util.*;

class Solution {
    public int minOperations(int[] nums, int k) {

        Arrays.sort(nums);

        int n = nums.length;
        long[] added = new long[n];

        int i = 0;
        int j = 0;
        int size = 0;
        int count = 0;

        while (true) {
            long x;

            if (i < n && (j >= size || nums[i] <= added[j])) {
                x = nums[i++];
            } else {
                x = added[j++];
            }

            if (x >= k) {
                return count;
            }
            long y;

            if (i < n && (j >= size || nums[i] <= added[j])) {
                y = nums[i++];
            } else {
                y = added[j++];
            }

            long newValue = 2 * x + y;

            added[size++] = newValue;
            count++;
        }
    }
}