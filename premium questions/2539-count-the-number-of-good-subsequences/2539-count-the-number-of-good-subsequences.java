class Solution {
    private static final long MOD = 1_000_000_007L;

    public int countGoodSubsequences(String s) {
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        int maxFreq = 0;
        for (int f : freq) {
            maxFreq = Math.max(maxFreq, f);
        }

        // Precompute factorials and inverse factorials
        long[] fact = new long[maxFreq + 1];
        long[] invFact = new long[maxFreq + 1];

        fact[0] = 1;

        for (int i = 1; i <= maxFreq; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }

        invFact[maxFreq] = modPow(fact[maxFreq], MOD - 2);

        for (int i = maxFreq; i >= 1; i--) {
            invFact[i - 1] = invFact[i] * i % MOD;
        }

        long answer = 0;

        // f = frequency that every selected character appears
        for (int f = 1; f <= maxFreq; f++) {
            long ways = 1;

            for (int count : freq) {
                if (count >= f) {
                    long combinations = choose(count, f, fact, invFact);

                    // Either don't include this character,
                    // or include it f times.
                    ways = ways * (1 + combinations) % MOD;
                }
            }

            // Remove the case where we choose no characters.
            ways = (ways - 1 + MOD) % MOD;

            answer = (answer + ways) % MOD;
        }

        return (int) answer;
    }

    private long choose(
        int n,
        int k,
        long[] fact,
        long[] invFact
    ) {
        return fact[n]
            * invFact[k] % MOD
            * invFact[n - k] % MOD;
    }

    private long modPow(long base, long exponent) {
        long result = 1;

        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                result = result * base % MOD;
            }

            base = base * base % MOD;
            exponent /= 2;
        }

        return result;
    }
}