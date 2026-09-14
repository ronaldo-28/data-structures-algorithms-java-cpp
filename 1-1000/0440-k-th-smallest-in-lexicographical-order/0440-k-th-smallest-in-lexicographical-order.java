class Solution {
    public int findKthNumber(int n, int k) {
        int cur = 1;
        k = k - 1;  // Since we start from 1
        
        while (k > 0) {
            long steps = calculateSteps(n, cur, cur + 1);
            if (steps <= k) {
                // The kth number is not under the current prefix.
                k -= steps;
                cur++;
            } else {
                // Go to the next level in the tree.
                cur *= 10;
                k--;
            }
        }
        return cur;
    }
    
    // Helper method to calculate steps between cur and next prefix
    private long calculateSteps(int n, long first, long last) {
        long steps = 0;
        while (first <= n) {
            steps += Math.min((long)n + 1, last) - first;
            first *= 10;
            last *= 10;
        }
        return steps;
    }
}
