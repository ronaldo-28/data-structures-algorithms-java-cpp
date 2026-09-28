class Solution {
    public long minimumMoves(int[] nums, int k, int maxChanges) {
        int n = nums.length;
        long minMoves = Long.MAX_VALUE;
        
        // Step 1: Check optimal cost using ONLY adjacent ones and maxChanges
        for (int i = 0; i < n; i++) {
            // Count the available "cheap" ones at distance <= 1
            int c = nums[i];
            if (i > 0) c += nums[i - 1];
            if (i < n - 1) c += nums[i + 1];
            
            int take = Math.min(k, c);
            
            // If the remaining deficit can be covered by artificial ones
            if (k - take <= maxChanges) {
                // Cost calculation mathematically works out to (take - nums[i])
                // Center costs 0, adjacents cost 1.
                long cost = (take - nums[i]) + (long)(k - take) * 2;
                minMoves = Math.min(minMoves, cost);
            }
        }
        
        // Step 2: Check optimal cost when we MUST pull ones from further away
        int need = Math.max(0, k - maxChanges);
        if (need > 0) {
            // Track the exact indices of all 1s
            int[] pos = new int[n];
            int mPos = 0;
            for (int i = 0; i < n; i++) {
                if (nums[i] == 1) {
                    pos[mPos++] = i;
                }
            }
            
            // Build a prefix sum array of the 1-indices for O(1) distance calculations
            long[] pref = new long[mPos + 1];
            for (int i = 0; i < mPos; i++) {
                pref[i + 1] = pref[i] + pos[i];
            }
            
            // Slide a window of size 'need' across the tracked positions
            for (int j = 0; j <= mPos - need; j++) {
                // The optimal center to collect these ones is the median of the window
                int m = j + need / 2;
                long center = pos[m];
                
                // Sum of distances from the median to all elements on its left
                long leftSum = (long)(m - j) * center - (pref[m] - pref[j]);
                
                // Sum of distances from the median to all elements on its right
                long rightSum = (pref[j + need] - pref[m + 1]) - (long)(j + need - 1 - m) * center;
                
                // Total cost is the absolute distances + the cost of utilizing all maxChanges
                long cost = leftSum + rightSum + (long) maxChanges * 2;
                minMoves = Math.min(minMoves, cost);
            }
        }
        
        return minMoves;
    }
}