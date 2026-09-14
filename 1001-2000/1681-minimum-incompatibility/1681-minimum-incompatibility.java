class Solution {
    int minIncomp = Integer.MAX_VALUE;

    public int minimumIncompatibility(int[] nums, int k) {
        int n = nums.length;
        int subsetSize = n / k;
        int[] freq = new int[17];
        for (int num : nums) {
            freq[num]++;
            if (freq[num] > k) return -1; 
        }
        backtrack(freq, k, subsetSize, 0, 0, 0, 0, 0);
        return minIncomp;
    }
    
    private void backtrack(int[] freq, int subsetsLeft, int subsetSize, int currentSize, 
                           int currentMin, int currentMax, int currentSum, int lastPicked) {
        if (currentSum >= minIncomp) return;
        if (subsetsLeft == 0) {
            minIncomp = currentSum;
            return;
        }
        if (currentSize == subsetSize) {
            backtrack(freq, subsetsLeft - 1, subsetSize, 0, 0, 0, currentSum + (currentMax - currentMin), 0);
            return;
        }
        for (int i = lastPicked + 1; i <= 16; i++) {
            if (freq[i] > 0) {
                freq[i]--;
                int nextMin = (currentSize == 0) ? i : currentMin;
                int nextMax = i;
                backtrack(freq, subsetsLeft, subsetSize, currentSize + 1, nextMin, nextMax, currentSum, i);
                freq[i]++;
                if (currentSize == 0) break;
            }
        }
    }
}