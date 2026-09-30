class Solution {
    public long minOperationsToMakeMedianK(int[] nums, int k) {
        int n = nums.length;
        int median = quickSelect(nums, 0, n, n / 2);
        if (median == k) return 0;
        long cost;

        if (median > k) {
            cost = median - k;
            for (int i = 0; i < n / 2; i++) if (nums[i] > k) cost += nums[i] - k;
        } else {
            cost = k - median;
            for (int i = n / 2 + 1; i < n; i++) if (nums[i] < k) cost += k - nums[i];
        }

        return cost;
    }

    private int quickSelect(int[] nums, int left, int right, int k) {
        int pivotIndex = ThreadLocalRandom.current().nextInt(left, right);
        int pivot = nums[pivotIndex];

        int lessStart = left;
        int current = left;
        int greaterEnd = right;

        while (current < greaterEnd) {
            if (nums[current] < pivot) swap(nums, current++, lessStart++);
            else if (nums[current] > pivot) swap(nums, current, --greaterEnd);
            else current++;
        }

        if (lessStart - left > k) return quickSelect(nums, left, lessStart, k);
        if (greaterEnd - left > k) return pivot;
        return quickSelect(nums, greaterEnd, right, k - (greaterEnd - left));
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}