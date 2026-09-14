class Solution {
    public int divisibleGame(int[] nums) {
        int n = nums.length;
        // Maps a prime number to the indices in `nums` where it is a factor
        Map<Integer, List<Integer>> primeToIndices = new HashMap<>();

        // 1. Efficient inline Prime Factorization
        for (int i = 0; i < n; i++) {
            int temp = nums[i];
            
            // Extract factor 2
            if (temp % 2 == 0) {
                primeToIndices.computeIfAbsent(2, k -> new ArrayList<>()).add(i);
                while (temp % 2 == 0) temp /= 2;
            }
            
            // Extract odd prime factors
            for (int p = 3; p * p <= temp; p += 2) {
                if (temp % p == 0) {
                    primeToIndices.computeIfAbsent(p, k -> new ArrayList<>()).add(i);
                    while (temp % p == 0) temp /= p;
                }
            }
            
            // If the remaining number is a prime itself
            if (temp > 1) {
                primeToIndices.computeIfAbsent(temp, k -> new ArrayList<>()).add(i);
            }
        }

        // 2. Precompute Prefix Sums for O(1) interval queries
        long[] pref = new long[n];
        pref[0] = nums[0];
        for (int i = 1; i < n; i++) {
            pref[i] = pref[i - 1] + nums[i];
        }

        long globalMaxDiff = Long.MIN_VALUE;
        int bestK = -1;

        // 3. Sparse Kadane's Algorithm for each Prime
        for (Map.Entry<Integer, List<Integer>> entry : primeToIndices.entrySet()) {
            int p = entry.getKey();
            List<Integer> V = entry.getValue();

            long curr = nums[V.get(0)];
            long maxSum = curr;

            for (int j = 1; j < V.size(); j++) {
                int prevIdx = V.get(j - 1);
                int currIdx = V.get(j);
                
                long gapSum = 0;
                // If there are elements strictly between the valid indices, subtract them
                if (currIdx - prevIdx > 1) {
                    gapSum = -(pref[currIdx - 1] - pref[prevIdx]);
                }

                // Standard Kadane transition equation
                curr = Math.max((long) nums[currIdx], curr + gapSum + nums[currIdx]);
                maxSum = Math.max(maxSum, curr);
            }

            // Record the maximum score difference and handle smallest k tie-breaker
            if (maxSum > globalMaxDiff) {
                globalMaxDiff = maxSum;
                bestK = p;
            } else if (maxSum == globalMaxDiff && p < bestK) {
                bestK = p;
            }
        }

        // 4. Edge Case: If no primes exist (e.g. array only contains 1s)
        if (bestK == -1) {
            long maxNeg = Long.MIN_VALUE;
            for (int x : nums) {
                maxNeg = Math.max(maxNeg, -(long) x);
            }
            globalMaxDiff = maxNeg;
            bestK = 2; // Smallest allowable k > 1
        }

        // 5. Handle Large modulo logic
        long MOD = 1_000_000_007;
        long ans = (globalMaxDiff % MOD) * bestK;
        ans %= MOD;
        
        // Map any resulting negative modulo back to a positive bound
        if (ans < 0) {
            ans = (ans + MOD) % MOD;
        }

        return (int) ans;
    }
}