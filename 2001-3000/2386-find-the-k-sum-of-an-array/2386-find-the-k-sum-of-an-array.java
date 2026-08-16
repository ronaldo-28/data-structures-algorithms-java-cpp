import java.util.Arrays;

class Solution {
    public long kSum(int[] nums, int k) {
        long maxSum = 0;
        int n = nums.length;
        
        // Step 1: Calculate the absolute maximum sum possible
        // Transform the array into its absolute values simultaneously
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                maxSum += nums[i];
            } else {
                nums[i] = -nums[i];
            }
        }
        
        // Base case: If k == 1, the absolute maximum sum is our answer
        if (k == 1) {
            return maxSum;
        }

        // Step 2: Sort the absolute values in ascending order
        Arrays.sort(nums);

        // Step 3: Use primitive flat arrays to build an ultra-fast min-heap.
        // This avoids memory allocations of Object wrappers like Long[] or Pair.
        long[] heapSum = new long[k + 1];
        int[] heapIdx = new int[k + 1];
        int heapSize = 0;

        // Push the first state: (reduction amount, next index to consider)
        heapSize++;
        heapSum[heapSize] = nums[0];
        heapIdx[heapSize] = 0;

        long currentReduction = 0;

        // Step 4: Extract the smallest reduction from the heap k-1 times
        for (int i = 0; i < k - 1; i++) {
            // Pop the root of the heap
            currentReduction = heapSum[1];
            int idx = heapIdx[1];

            // Re-heapify downwards (standard min-heap pop)
            long lastSum = heapSum[heapSize];
            int lastIdx = heapIdx[heapSize];
            heapSize--;

            if (heapSize > 0) {
                int parent = 1;
                while (parent * 2 <= heapSize) {
                    int child = parent * 2;
                    if (child + 1 <= heapSize && heapSum[child + 1] < heapSum[child]) {
                        child++;
                    }
                    if (lastSum <= heapSum[child]) {
                        break;
                    }
                    heapSum[parent] = heapSum[child];
                    heapIdx[parent] = heapIdx[child];
                    parent = child;
                }
                heapSum[parent] = lastSum;
                heapIdx[parent] = lastIdx;
            }

            // Step 5: Push next branching decisions if within bounds
            if (idx + 1 < n) {
                long nextSum1 = currentReduction + nums[idx + 1];
                long nextSum2 = currentReduction + nums[idx + 1] - nums[idx];

                // Push nextSum1
                heapSize++;
                int hole = heapSize;
                while (hole > 1 && nextSum1 < heapSum[hole / 2]) {
                    heapSum[hole] = heapSum[hole / 2];
                    heapIdx[hole] = heapIdx[hole / 2];
                    hole /= 2;
                }
                heapSum[hole] = nextSum1;
                heapIdx[hole] = idx + 1;

                // Push nextSum2
                heapSize++;
                hole = heapSize;
                while (hole > 1 && nextSum2 < heapSum[hole / 2]) {
                    heapSum[hole] = heapSum[hole / 2];
                    heapIdx[hole] = heapIdx[hole / 2];
                    hole /= 2;
                }
                heapSum[hole] = nextSum2;
                heapIdx[hole] = idx + 1;
            }
        }

        // The k-th largest sum = max_possible_sum - (k-1)th smallest reduction
        return maxSum - currentReduction;
    }
}