class Solution {
    public int lengthOfLIS(int[] nums, int k) {
        // Step 1: Find the maximum element bounds in the array to size our tree
        int maxVal = 0;
        for (int num : nums) {
            if (num > maxVal) {
                maxVal = num;
            }
        }

        // Step 2: Initialize a flat iterative Segment Tree
        // Using size = 2 * maxVal provides complete coverage for a 1-indexed iterative tree
        int n = maxVal + 1;
        int[] tree = new int[2 * n];
        int globalMaxLIS = 0;

        // Step 3: Process numbers sequentially
        for (int num : nums) {
            // We want to query the max LIS ending in the range [max(1, num - k), num - 1]
            int left = Math.max(1, num - k);
            int right = num - 1;
            
            int maxPreviousLIS = 0;
            if (left <= right) {
                // High-performance iterative range maximum query: O(log M)
                int l = left + n;
                int r = right + n + 1; // Open interval bound
                
                while (l < r) {
                    if ((l & 1) == 1) {
                        maxPreviousLIS = Math.max(maxPreviousLIS, tree[l]);
                        l++;
                    }
                    if ((r & 1) == 1) {
                        r--;
                        maxPreviousLIS = Math.max(maxPreviousLIS, tree[r]);
                    }
                    l >>= 1;
                    r >>= 1;
                }
            }

            // Current length is the best preceding subsequence + 1
            int currentLIS = maxPreviousLIS + 1;
            globalMaxLIS = Math.max(globalMaxLIS, currentLIS);

            // High-performance iterative single element update: O(log M)
            int idx = num + n;
            if (currentLIS > tree[idx]) {
                tree[idx] = currentLIS;
                while (idx > 1) {
                    idx >>= 1;
                    int leftChildValue = tree[idx << 1];
                    int rightChildValue = tree[(idx << 1) | 1];
                    int maxChildValue = leftChildValue > rightChildValue ? leftChildValue : rightChildValue;
                    
                    // If parent is already up-to-date, break early
                    if (tree[idx] == maxChildValue) {
                        break;
                    }
                    tree[idx] = maxChildValue;
                }
            }
        }

        return globalMaxLIS;
    }
}