import java.util.*;

class Solution {
    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        int n = nums.size();
        
        for (int i = 0; i + 2 * k <= n; i++) {
            if (isStrictlyIncreasing(nums, i, i + k - 1) &&
                isStrictlyIncreasing(nums, i + k, i + 2 * k - 1)) {
                return true;
            }
        }
        
        return false;
    }
    
    private boolean isStrictlyIncreasing(List<Integer> nums, int start, int end) {
        for (int i = start; i < end; i++) {
            if (nums.get(i) >= nums.get(i + 1)) return false;
        }
        return true;
    }
}