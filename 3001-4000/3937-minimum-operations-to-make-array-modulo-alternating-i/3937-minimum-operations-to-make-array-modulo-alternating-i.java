class Solution {
    public int minOperations(int[] nums, int k) {
        long[] evenCosts = computeCosts(nums, k, true);
        long[] oddCosts = computeCosts(nums, k, false);
        long minE1 = Long.MAX_VALUE, minE2 = Long.MAX_VALUE;
        int idxE1 = -1, idxE2 = -1;
        for (int i = 0; i < k; i++) {
            if (evenCosts[i] < minE1) {
                minE2 = minE1;
                idxE2 = idxE1;
                minE1 = evenCosts[i];
                idxE1 = i;
            } else if (evenCosts[i] < minE2) {
                minE2 = evenCosts[i];
                idxE2 = i;
            }
        }
        
        long minO1 = Long.MAX_VALUE, minO2 = Long.MAX_VALUE;
        int idxO1 = -1, idxO2 = -1;
        for (int i = 0; i < k; i++) {
            if (oddCosts[i] < minO1) {
                minO2 = minO1;
                idxO2 = idxO1;
                minO1 = oddCosts[i];
                idxO1 = i;
            } else if (oddCosts[i] < minO2) {
                minO2 = oddCosts[i];
                idxO2 = i;
            }
        }
        
        if (idxE1 != idxO1) {
            return (int) (minE1 + minO1);
        } else {
            return (int) Math.min(minE1 + minO2, minE2 + minO1);
        }
    }


    private long[] computeCosts(int[] nums, int k, boolean isEven) {
        long[] c = new long[k];
        int n = 0;
        
        for (int i = isEven ? 0 : 1; i < nums.length; i += 2) {
            int rem = nums[i] % k;
            if (rem < 0) rem += k;
            c[rem]++;
            n++;
        }
        
        long[] f = new long[k];
        if (n == 0) return f;
        for (int d = 0; d < k; d++) {
            f[0] += c[d] * Math.min(d, k - d);
        }
        int m = k / 2;
        long S1 = 0; 
        for (int d = 1; d <= m; d++) {
            S1 += c[d];
        }
        if (k % 2 == 0) {
            for (int x = 0; x < k - 1; x++) {
                f[x + 1] = f[x] + n - 2L * S1;
                S1 = S1 - c[(x + 1) % k] + c[(x + 1 + m) % k];
            }
        } else {
            long S0 = c[m + 1];
            for (int x = 0; x < k - 1; x++) {
                f[x + 1] = f[x] + n - 2L * S1 - S0;
                S1 = S1 - c[(x + 1) % k] + S0;
                S0 = c[(x + 1 + m + 1) % k];
            }
        }
        
        return f;
    }
}