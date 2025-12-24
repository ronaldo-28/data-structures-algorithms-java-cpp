class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        
        // Step 1: Build prefix sum to get subarray sums efficiently
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        // sum[i] = sum of subarray starting at i of length k
        int[] sum = new int[n - k + 1];
        for (int i = 0; i <= n - k; i++) {
            sum[i] = prefixSum[i + k] - prefixSum[i];
        }

        // Step 2: left[i] = starting index of max sum subarray from [0..i]
        int[] left = new int[n - k + 1];
        int best = 0;
        for (int i = 0; i < sum.length; i++) {
            if (sum[i] > sum[best]) best = i;
            left[i] = best;
        }

        // Step 3: right[i] = starting index of max sum subarray from [i..end]
        int[] right = new int[n - k + 1];
        best = sum.length - 1;
        for (int i = sum.length - 1; i >= 0; i--) {
            if (sum[i] >= sum[best]) best = i; // >= ensures lexicographically smallest
            right[i] = best;
        }

        // Step 4: middle subarray + best left and right
        int[] res = new int[3];
        int maxSum = 0;
        for (int mid = k; mid < sum.length - k; mid++) {
            int l = left[mid - k];
            int r = right[mid + k];
            int total = sum[l] + sum[mid] + sum[r];
            if (total > maxSum) {
                maxSum = total;
                res[0] = l;
                res[1] = mid;
                res[2] = r;
            }
        }

        return res;
    }
}