import java.util.List;

class Solution {
    public int minLengthAfterRemovals(List<Integer> nums) {
        int n = nums.size();
        int midVal = nums.get(n / 2);

        // Find lower bound (first occurrence of midVal)
        int left = 0, right = n - 1;
        int first = n;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums.get(mid) >= midVal) {
                first = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        // Find upper bound (last occurrence of midVal)
        left = 0;
        right = n - 1;
        int last = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums.get(mid) <= midVal) {
                last = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        int maxFreq = last - first + 1;

        // If majority element exists, it bounds the minimum length
        if (maxFreq > n / 2) {
            return 2 * maxFreq - n;
        }

        // Otherwise, return 0 if even length, 1 if odd length
        return n % 2;
    }
}