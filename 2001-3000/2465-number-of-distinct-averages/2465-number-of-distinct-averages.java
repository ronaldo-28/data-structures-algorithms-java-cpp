import java.util.*;

class Solution {

    public int distinctAverages(int[] nums) {

        int[] freq = new int[101];

        // Count frequencies
        for (int num : nums) {
            freq[num]++;
        }

        Set<Integer> set = new HashSet<>();

        int low = 0;
        int high = 100;

        while (low <= high) {

            // Find next smallest
            while (low <= 100 && freq[low] == 0) {
                low++;
            }

            // Find next largest
            while (high >= 0 && freq[high] == 0) {
                high--;
            }

            if (low > high) {
                break;
            }

            // Store sum instead of average
            set.add(low + high);

            // Use those numbers
            freq[low]--;
            freq[high]--;
        }

        return set.size();
    }
}