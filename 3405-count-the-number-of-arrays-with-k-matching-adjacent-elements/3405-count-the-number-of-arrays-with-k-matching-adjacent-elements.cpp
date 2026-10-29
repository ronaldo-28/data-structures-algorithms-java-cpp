#define MOD 1000000007
#define ll long long
#define MAXN 100005

ll fact[MAXN];
ll invFact[MAXN];
static bool done = false;

class Solution {
public:
    int countGoodArrays(int n, int m, int k) {
        if (!done) {
            precompute();
            done = true;
        }
        return nCr(n-1, k) * binpow(m-1, n-k-1) % MOD * m % MOD;

    }
private:
    ll binpow(ll x, ll n) {
        ll res = 1;
        ll curr = x;
        while (n>0) {
            if (n & 1) res = res * curr % MOD;
            curr = curr * curr % MOD;
            n >>= 1;
        }
        return res;
    }
    void precompute() {
        fact[0] = 1;
        invFact[0] = 1;

        for (int i=1; i < MAXN; i++) {
            fact[i] = (fact[i-1] * i) % MOD;
        }
        invFact[MAXN-1] = binpow(fact[MAXN-1], MOD-2);

        for (int i=MAXN-2; i>=1; i--) {
            invFact[i] = (invFact[i+1] * (i+1)) % MOD;
        }
    }

    ll nCr(int n, int r) {
        if (r<0 || r>n) return 0;

        ll ans = fact[n];
        ans = (ans * invFact[r]) % MOD;
        ans = (ans * invFact[n-r]) % MOD;

        return ans;
    }
};