class Solution {
private:
    const int MOD = 1e9 + 7;

    // Modular exponentiation to compute modular inverse
    long long power(long long base, long long exp) {
        long long res = 1;
        base %= MOD;
        while (exp > 0) {
            if (exp % 2 == 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp /= 2;
        }
        return res;
    }

    // Modular inverse using Fermat's Little Theorem
    long long modInverse(long long n) {
        return power(n, MOD - 2);
    }

    // Computes nCr % MOD in O(r) time
    long long nCr(int n, int r) {
        if (r < 0 || r > n) return 0;
        if (r == 0 || r == n) return 1;
        if (r > n / 2) r = n - r;

        long long num = 1, den = 1;
        for (int i = 0; i < r; ++i) {
            num = (num * (n - i)) % MOD;
            den = (den * (i + 1)) % MOD;
        }

        return (num * modInverse(den)) % MOD;
    }

public:
    int countOfPairs(vector<int>& nums) {
        int n = nums.size();
        
        // Calculate total forced increase S
        int S = 0;
        for (int i = 1; i < n; ++i) {
            S += max(0, nums[i] - nums[i - 1]);
        }

        int K = nums.back() - S;
        if (K < 0) return 0;

        // Answer is (K + n) C n
        return nCr(K + n, n);
    }
};