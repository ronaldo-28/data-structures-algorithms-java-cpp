class Solution {
    public long subArrayRanges(int[] nums) {
        int n = nums.length;

        long sumMin = 0;
        long sumMax = 0;

        // MIN contribution
        for (int i = 0; i < n; i++) {
            int countLeft = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] > nums[i]) countLeft++;
                else break;
            }

            int countRight = 1;
            for (int j = i + 1; j < n; j++) {
                if (nums[j] >= nums[i]) countRight++;
                else break;
            }

            sumMin += (long) countLeft * countRight * nums[i];
        }

        // MAX contribution
        for (int i = 0; i < n; i++) {
            int countLeft = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) countLeft++;
                else break;
            }

            int countRight = 1;
            for (int j = i + 1; j < n; j++) {
                if (nums[j] <= nums[i]) countRight++;
                else break;
            }

            sumMax += (long) countLeft * countRight * nums[i];
        }

        return sumMax - sumMin;
    }
}