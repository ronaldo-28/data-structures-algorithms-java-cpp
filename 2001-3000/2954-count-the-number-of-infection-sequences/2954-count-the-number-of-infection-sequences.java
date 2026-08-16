class Solution {
    private static final int MOD = 1_000_000_007;
    private static final int MAX = 100_005;

    // Shared global lookups precomputed exactly once for O(1) mathematical retrieval
    private static final long[] fact = new long[MAX];
    private static final long[] invFact = new long[MAX];
    private static final long[] pow2 = new long[MAX];
    private static boolean isInitialized = false;

    // Fast static precomputation hook triggered on initialization step
    private static void precompute() {
        fact[0] = 1;
        invFact[0] = 1;
        pow2[0] = 1;
        
        for (int i = 1; i < MAX; i++) {
            fact[i] = (fact[i - 1] * i) % MOD;
            pow2[i] = (pow2[i - 1] * 2) % MOD;
        }
        
        // Fast Modular Inverse calculation via Fermat's Little Theorem
        invFact[MAX - 1] = power(fact[MAX - 1], MOD - 2);
        for (int i = MAX - 2; i >= 1; i--) {
            invFact[i] = (invFact[i + 1] * (i + 1)) % MOD;
        }
        isInitialized = true;
    }

    private static long power(long base, long exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return res;
    }

    public int numberOfSequence(int n, int[] sick) {
        if (!isInitialized) {
            precompute();
        }

        int totalUninfected = n - sick.length;
        long totalCombinations = fact[totalUninfected];

        int prevSickIdx = -1;
        int m = sick.length;

        for (int i = 0; i < m; i++) {
            int currentSickIdx = sick[i];
            int groupLen = currentSickIdx - prevSickIdx - 1;
            prevSickIdx = currentSickIdx;

            if (groupLen <= 0) continue;

            // Divide out the factorial of the group length (multinomial denominator)
            totalCombinations = (totalCombinations * invFact[groupLen]) % MOD;

            // If it's a middle segment (not bounded by index 0), multiply by 2^(L-1) choices
            if (i > 0) {
                totalCombinations = (totalCombinations * pow2[groupLen - 1]) % MOD;
            }
        }

        // Process the final tail edge segment (from the last sick person to index n-1)
        int lastGroupLen = n - sick[m - 1] - 1;
        if (lastGroupLen > 0) {
            totalCombinations = (totalCombinations * invFact[lastGroupLen]) % MOD;
        }

        return (int) totalCombinations;
    }
}