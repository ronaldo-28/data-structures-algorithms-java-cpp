class Solution {
    public int[] getAverages(int[] nums, int k) {
        if (k == 0) return nums;

        int n = nums.length;

        var res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = -1;
        }

        int windowSize = 2 * k + 1;
        if (windowSize > n) return res;

        long sum = 0;
        for (int i = 0; i < windowSize; i++) {
            sum += nums[i];
        }

        res[k] = (int) (sum / windowSize);

        for (int i = windowSize; i < n; i++) {
            sum += nums[i] - nums[i - windowSize];
            res[i - k] = (int) (sum / windowSize);
        }

        return res;
    }
}