class Solution {
    public int singleNonDuplicate(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mid % 2 == 0) { // mid is even
                if (mid + 1 < nums.length && nums[mid] == nums[mid + 1]) {
                    left = mid + 2; // Single element is in right half
                } else {
                    right = mid; // Single element is in left half or at mid
                }
            } else { // mid is odd
                if (mid - 1 >= 0 && nums[mid] == nums[mid - 1]) {
                    left = mid + 1; // Single element is in right half
                } else {
                    right = mid - 1; // Single element is in left half or at mid
                }
            }
        }
        return nums[left];
    }
}