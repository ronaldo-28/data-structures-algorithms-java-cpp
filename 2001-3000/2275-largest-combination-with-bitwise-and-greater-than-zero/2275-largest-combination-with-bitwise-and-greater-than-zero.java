class Solution {
    public int largestCombination(int[] candidates) {
        int result = 0;
        int maxValue = Integer.MIN_VALUE;
        for (int candidate : candidates) {
            maxValue = Math.max(maxValue, candidate);
        }
        int mask = 1;
        for (int i = 0; i < 32 && mask <= maxValue; i++) {
            int indexHits = 0;
            for (int candidate : candidates) {
                indexHits += (candidate >> i) & 1;
            }
            result = Math.max(result, indexHits);
            mask <<= 1;
        }
        return result;
    }
} 