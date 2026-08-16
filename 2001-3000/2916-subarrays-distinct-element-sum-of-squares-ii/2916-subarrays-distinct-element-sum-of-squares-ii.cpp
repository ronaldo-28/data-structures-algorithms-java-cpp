class FenwickTree {
    vector<long long> t;
public:
    FenwickTree(size_t n) : t(n) {}

    void update(int i, int x) {
        while (i < t.size()) {
            t[i] += x;
            i |= (i + 1);
        }
    }

    long long query(int i) {
        long long ans = 0;

        while (i >= 0) {
            ans += t[i];
            i = (i & (i + 1)) - 1;
        }

        return ans;
    }
};

class RFenwick {
    FenwickTree a, b;

    long long query(int i) {
        long long x = a.query(i) * i;
        long long y = b.query(i);
        return x - y;
    }
public:
    RFenwick(size_t n) : a(n), b(n) {}

    void update(int l, int r, int x) {
        a.update(l, x);
        a.update(r + 1, -x);
        b.update(l, 1LL * x * (l - 1));
        b.update(r + 1, 1LL * x * -r);
    }

    long long query(int l, int r) {
        long long x = query(r);
        long long y = (l == 0) ? 0 : query(l - 1);
        return x - y;
    }
};

class Solution {
public:
    int sumCounts(vector<int>& nums) {
        constexpr int M = 1e9 + 7;

        const int N = nums.size();

        RFenwick t(N + 3);

        unordered_map<int, int> last;

        long long ans = 0;

        long long s = 0;

        for (int i = 0; i < N; i++) {
            auto [it, is_new] = last.try_emplace(nums[i], i);

            int l = 0;

            if (!is_new) {
                l = it->second + 1;
                it->second = i;
            }

            long long q = t.query(l, i);

            t.update(l, i, 1);

            s = (s + 2*q + (i - l + 1)) % M;

            ans += s;
        }

        ans %= M;

        return ans;
    }
};