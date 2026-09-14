class Solution {
public:
    const int MOD = 1e9 + 7;
    vector<long long> fact, invFact;
    long long power(long long a, long long b) {
        long long res = 1;
        while (b > 0) {
            if (b & 1) res = (res * a) % MOD;
            a = (a * a) % MOD;
            b >>= 1;
        }
        return res;
    }

    void initNCR(int n) {
        fact.resize(n + 1);
        invFact.resize(n + 1);
        fact[0] = 1;
        for (int i = 1; i <= n; i++)
            fact[i] = (fact[i - 1] * i) % MOD;
        invFact[n] = power(fact[n], MOD - 2);
        for (int i = n; i >= 1; i--)
            invFact[i - 1] = (invFact[i] * i) % MOD;
    }

    long long nCr(int n, int r ,  int l) {
        if (r < 0 || r > n) return 0;
        return (((fact[n] * invFact[r]) % MOD) * invFact[l]) % MOD;
    }

    int numberOfWays(int start, int end, int k) {
        if((abs(end-start)%2) != (k%2)) return 0;
        if(abs(end-start) > k) return 0;

        initNCR(1005); 

        int r = abs((end-start)) + (k-abs(end-start))/2;

        int l = (k-abs(end-start))/2;

        return nCr(k,r,l);


    }
};