
class Solution {
    public int missingElement(int[] nums, int k) {
        int index;

        // initialize search
        int left = 0;
        int right = nums.length - 1;

        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            int numMissing = nums[mid] - mid - nums[0];

            if (numMissing < k) left = mid;
            else right = mid - 1;
        }

        // post processing
        if (nums[right] - right - nums[0] < k) index = right;
        else if (nums[left] - left - nums[0] < k) index = left;
        else return -1;

        int missingAtIndex = nums[index] - index - nums[0];
        return nums[index] + (k - missingAtIndex);
    }
}