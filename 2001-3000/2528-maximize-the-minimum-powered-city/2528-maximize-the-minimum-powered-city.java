class Solution {
    public long maxPower(int[] stations, int r, int k) {
        // binary search for max possible
        // as if pow is possible, anything less than pow is possible
        // in the check function for any index i thats lower than target
        // we build target - power[i] at i + r so it can cover the most
        // power[] is the total powers for each city
        int n = stations.length;
        long[] power = new long[n];
        // sliding window with size r+1
        long curr = 0;
        long max = 0;
        for (int i = 0; i <= r; ++i) {
            curr += stations[i];
        }
    
        for (int i = 0; i < n; ++i) {
            if (i > 0 && i+r < n) {
                curr += stations[i+r];
            }

            if (i > r) {
                curr -= stations[i-r-1];
            }

            power[i] = curr;
            max = Math.max(max, curr);
        }

        long left = 0, right = max+k;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (check(mid, power, r, k)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }

    boolean check(long target, long[] power, int r, int k) {
        long[] dropoff = new long[power.length];
        int used = 0;
        long currExtra = 0;
        for (int i = 0; i < power.length; ++i) {
            currExtra -= dropoff[i];
            if (power[i] + currExtra >= target) continue;
            else {
                long needed = target - (power[i] + currExtra);

                used += needed;
                if (used > k) return false;
                currExtra += needed;
                // build at i + r with range [i, i+2r]
                int end = i + 2*r + 1;
                if (end < power.length) dropoff[end] = needed;
            }
        }

        return true;
    }
}