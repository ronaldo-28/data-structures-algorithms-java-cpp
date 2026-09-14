class Solution {
    // time = O(nlogn), space = O(n)
    public int maximumTeamSize(int[] startTime, int[] endTime) {
        int n = startTime.length;
        int[] a = startTime.clone();
        int[] b = endTime.clone();
        Arrays.sort(a);
        Arrays.sort(b);

        int res = 1;
        for (int i = 0; i < n; i++) {
            int l = startTime[i], r = endTime[i];
            int x = upper_bound(a, r);
            int y = lower_bound(b, l);
            res = Math.max(res, x - y);
        }
        return res;
    }

    private int upper_bound(int[] nums, int t) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (nums[mid] <= t) l = mid;
            else r = mid - 1;
        }
        return nums[r] <= t ? r : r - 1;
    }

    private int lower_bound(int[] nums, int t) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (nums[mid] < t) l = mid;
            else r = mid - 1;
        }
        return nums[r] < t ? r : r - 1;
    }
}