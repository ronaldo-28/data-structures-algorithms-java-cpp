class Solution {
    private static final int MOD = 1_000_000_007;
    private static final int INV2 = 500_000_004; // Modular inverse of 2

    public int subsequencesWithMiddleMode(int[] nums) {
        int n = nums.length;
        
        // 1. Coordinate Compression
        // Maps large values to [0, m-1] for array indexing
        Map<Integer, Integer> map = new HashMap<>();
        int m = 0;
        for (int x : nums) {
            if (!map.containsKey(x)) {
                map.put(x, m++);
            }
        }
        
        int[] compressed = new int[n];
        for (int i = 0; i < n; i++) {
            compressed[i] = map.get(nums[i]);
        }

        // 2. Frequency Arrays
        long[] pre = new long[m]; // Prefix counts
        long[] suf = new long[m]; // Suffix counts
        
        for (int x : compressed) {
            suf[x]++;
        }

        // 3. Precompute Initial Sums (Suf only, Pre is empty)
        long sumSufSq = 0;
        long sumPreSq = 0;
        long sumPreSuf = 0;
        long sumPreSqSuf = 0;
        long sumSufSqPre = 0;

        for (long c : suf) {
            sumSufSq = (sumSufSq + c * c) % MOD;
        }

        long total = 0;

        // 4. Iterate
        for (int i = 0; i < n; i++) {
            int x = compressed[i];
            long cPre = pre[x]; // current count of x in prefix
            long cSuf = suf[x]; // current count of x in suffix

            // --- STEP 1: EXCLUDE x from Global Sums ---
            // The logic relies on these sums representing "other" elements (y != x).
            // We temporarily remove x's contribution.
            long sqPre = (cPre * cPre) % MOD;
            long sqSuf = (cSuf * cSuf) % MOD;
            
            sumPreSq = (sumPreSq - sqPre + MOD) % MOD;
            sumSufSq = (sumSufSq - sqSuf + MOD) % MOD;
            sumPreSuf = (sumPreSuf - (cPre * cSuf) % MOD + MOD) % MOD;
            sumPreSqSuf = (sumPreSqSuf - (sqPre * cSuf) % MOD + MOD) % MOD;
            sumSufSqPre = (sumSufSqPre - (sqSuf * cPre) % MOD + MOD) % MOD;

            // --- STEP 2: Update Local Suffix State ---
            // We use the element at i as center, so it is removed from suffix
            long newSuf = cSuf - 1;
            suf[x] = newSuf;

            // --- STEP 3: Calculate Subsequences ---
            
            int left = i;
            int right = n - 1 - i;
            
            // A. All ways to pick 2 left, 2 right
            long ways = (comb2(left) * comb2(right)) % MOD;
            total = (total + ways) % MOD;
            
            // B. Subtract base bad case: x not in flanks
            // This counts cases where only "others" are picked in the flanks.
            long leftOver = left - cPre;   // slots on left not filled by x
            long rightOver = right - newSuf; // slots on right not filled by x
            
            long badBase = (comb2(leftOver) * comb2(rightOver)) % MOD;
            total = (total - badBase + MOD) % MOD;
            
            // C. Subtract Pattern 1: x in right flank, "others" form a pair in left
            // Formula derived from complement counting: (SumSqP_others - SumP_others) / 2 * ...
            long term1 = (sumPreSq - leftOver + MOD) % MOD;
            term1 = term1 * newSuf % MOD;
            term1 = term1 * rightOver % MOD;
            term1 = term1 * INV2 % MOD;
            total = (total - term1 + MOD) % MOD;
            
            // D. Subtract Pattern 2: x in left flank, "others" form a pair in right
            long term2 = (sumSufSq - rightOver + MOD) % MOD;
            term2 = term2 * cPre % MOD;
            term2 = term2 * leftOver % MOD;
            term2 = term2 * INV2 % MOD;
            total = (total - term2 + MOD) % MOD;
            
            // E. Subtract Pattern 3: "others" form pair y..y crossing the center
            // Logic: Sum(pre[y]*suf[y]) * (ways to fill remaining spots with x involved)
            // Bracket simplifies to: pre[x]*right + suf[x]*left - 2*pre[x]*suf[x]
            long bracket = (cPre * right) % MOD;
            bracket = (bracket + newSuf * left) % MOD;
            long sub = (2 * cPre) % MOD * newSuf % MOD;
            bracket = (bracket - sub + MOD) % MOD;
            
            long term3 = (sumPreSuf * bracket) % MOD;
            total = (total - term3 + MOD) % MOD;
            
            // F. Add back Edge Cases (Complement adjustments)
            // These add back specific configurations involving pairs of 'y' and 'x' 
            // that were over-subtracted.
            long adj1 = (sumPreSqSuf * newSuf) % MOD;
            total = (total + adj1) % MOD;
            
            long adj2 = (sumSufSqPre * cPre) % MOD;
            total = (total + adj2) % MOD;
            
            // --- STEP 4: Update Local Prefix State ---
            long newPre = cPre + 1;
            pre[x] = newPre;
            
            // --- STEP 5: INCLUDE x back into Global Sums ---
            // Add x back using its NEW prefix count and NEW suffix count
            long newSqPre = (newPre * newPre) % MOD;
            long newSqSuf = (newSuf * newSuf) % MOD;
            
            sumPreSq = (sumPreSq + newSqPre) % MOD;
            sumSufSq = (sumSufSq + newSqSuf) % MOD;
            sumPreSuf = (sumPreSuf + newPre * newSuf) % MOD;
            sumPreSqSuf = (sumPreSqSuf + newSqPre * newSuf) % MOD;
            sumSufSqPre = (sumSufSqPre + newSqSuf * newPre) % MOD;
        }
        
        return (int) total;
    }
    
    private long comb2(long n) {
        if (n < 2) return 0;
        // n * (n-1) / 2 % MOD
        long res = (n * (n - 1)) % MOD;
        return (res * INV2) % MOD;
    }
}