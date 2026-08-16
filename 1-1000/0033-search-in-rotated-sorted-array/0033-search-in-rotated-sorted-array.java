class Solution {
    public int search(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] > nums[hi]) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        int pivot = lo;
        lo = 0; hi = nums.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int realMid = (mid + pivot) % nums.length;
            if (nums[realMid] == target) {
                return realMid;
            }
            if (nums[realMid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1;
    }
}
