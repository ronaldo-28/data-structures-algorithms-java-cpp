class Solution {
public:
    using ll = long long;

    struct BIT {
        int n;
        vector<ll> bit;

        BIT(int n) : n(n), bit(n + 1, 0) {}

        void add(int idx, ll val) {
            for (; idx <= n; idx += idx & -idx)
                bit[idx] += val;
        }

        ll sum(int idx) {
            ll res = 0;
            for (; idx > 0; idx -= idx & -idx)
                res += bit[idx];
            return res;
        }

        // Smallest index whose prefix sum >= k
        int kth(ll k) {
            int idx = 0;
            ll cur = 0;

            for (int step = 1 << (31 - __builtin_clz(n)); step; step >>= 1) {
                int nxt = idx + step;

                if (nxt <= n && cur + bit[nxt] < k) {
                    idx = nxt;
                    cur += bit[nxt];
                }
            }

            return idx + 1;
        }
    };

    long long minOperations(vector<int>& nums, int x, int k) {
        int n = nums.size();

        // Coordinate compression
        vector<int> vals = nums;
        sort(vals.begin(), vals.end());
        vals.erase(unique(vals.begin(), vals.end()), vals.end());

        int m = vals.size();

        BIT cnt(m), sum(m);

        auto getId = [&](int v) {
            return lower_bound(vals.begin(), vals.end(), v) - vals.begin() + 1;
        };

        auto add = [&](int v, int delta) {
            int id = getId(v);
            cnt.add(id, delta);
            sum.add(id, 1LL * v * delta);
        };

        // Cost of current window
        auto getCost = [&]() -> ll {
            int sz = x;

            // lower median
            int medianId = cnt.kth((sz + 1) / 2);
            ll median = vals[medianId - 1];

            ll leftCnt = cnt.sum(medianId);
            ll leftSum = sum.sum(medianId);

            ll totalSum = sum.sum(m);
            ll rightCnt = sz - leftCnt;
            ll rightSum = totalSum - leftSum;

            return median * leftCnt - leftSum
                 + rightSum - median * rightCnt;
        };

        // Cost for every length-x window
        vector<ll> cost(n - x + 1);

        for (int i = 0; i < x; i++)
            add(nums[i], 1);

        cost[0] = getCost();

        for (int r = x; r < n; r++) {
            add(nums[r - x], -1);
            add(nums[r], 1);

            cost[r - x + 1] = getCost();
        }

        // DP
        const ll INF = (1LL << 62);

        // prev[i] = minimum cost to select 'used' windows
        // from first i elements
        vector<ll> prev(n + 1, 0), cur(n + 1, INF);

        for (int used = 1; used <= k; used++) {
            fill(cur.begin(), cur.end(), INF);

            for (int i = 1; i <= n; i++) {

                // Don't start a window at i-1
                cur[i] = cur[i - 1];

                // Take window [i-x, i-1]
                if (i >= x && prev[i - x] != INF) {
                    cur[i] = min(
                        cur[i],
                        prev[i - x] + cost[i - x]
                    );
                }
            }

            prev.swap(cur);
        }

        return prev[n];
    }
};