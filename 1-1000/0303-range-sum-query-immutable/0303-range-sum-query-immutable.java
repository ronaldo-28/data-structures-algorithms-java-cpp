class NumArray {
    private int[] prefixSum;

    public NumArray(int[] nums) {
        if (nums.length > 0) {
            prefixSum = new int[nums.length];
            prefixSum[0] = nums[0];

            for (int i = 1; i < nums.length; i++) {
                prefixSum[i] = prefixSum[i - 1] + nums[i];
            }
        }
    }

    public int sumRange(int left, int right) {
        if (prefixSum == null) {
            return 0;
        }

        int leftSum = (left == 0) ? 0 : prefixSum[left - 1];
        int rightSum = prefixSum[right];

        return rightSum - leftSum;
    }
}
