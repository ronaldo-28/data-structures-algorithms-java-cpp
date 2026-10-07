class Solution {
    private static final int MOD = 1_000_000_007;

    public int valueAfterKSeconds(int n, int k) {
        if (n == 1) return 1;
        
        int totalElements = n - 1 + k;
        int r = n - 1;
        
        long numerator = 1;
        long denominator = 1;
        
        // Compute nCr natively bounded to the exact modulo
        for (int i = 1; i <= r; i++) {
            numerator = (numerator * (totalElements - i + 1)) % MOD;
            denominator = (denominator * i) % MOD;
        }
        
        return (int) ((numerator * modInverse(denominator, MOD)) % MOD);
    }
    
    // Fermat's Little Theorem: a^(m-2) % m is the modular inverse of a % m
    private long modInverse(long base, int mod) {
        long res = 1;
        long power = mod - 2;
        
        while (power > 0) {
            if ((power & 1) == 1) {
                res = (res * base) % mod;
            }
            base = (base * base) % mod;
            power >>= 1;
        }
        
        return res;
    }
}