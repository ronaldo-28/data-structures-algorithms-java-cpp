class Solution {
    public int bestRotation(int[] nums) {
        int n = nums.length;
        // change[k] stores the net change in score when we rotate by k
        int[] change = new int[n];

        for (int i = 0; i < n; i++) {
            // A number nums[i] will lose its point when its index 
            // becomes nums[i] - 1. 
            // The rotation k that causes this is:
            int left = (i - nums[i] + 1 + n) % n;
            
            // At this specific k, the score drops by 1
            change[left]--;
        }

        int maxScore = -1;
        int bestK = 0;
        int currentScore = 0;

        // Every rotation also shifts an element from index 0 to n-1.
        // Since nums[i] < n, an element moving to n-1 ALWAYS gains a point.
        // This is handled by adding 1 to the score at every step in the loop.
        for (int k = 0; k < n; k++) {
            currentScore += change[k] + 1;
            if (currentScore > maxScore) {
                maxScore = currentScore;
                bestK = k;
            }
        }

        return bestK;
    }
}