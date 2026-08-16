typedef long long ll;

constexpr ll MAXN = 1e5 + 5;
constexpr ll INF = 0x3f3f3f3f3f3f3f3f;

ll __M;
ll seg[MAXN << 2];
ll lazy[MAXN << 2];

inline void maintain(ll x) {
    seg[x] = min(seg[x << 1], seg[x << 1 | 1]);
}

inline void build(ll N) {
    for (__M = 1; __M < N + 2; __M <<= 1);
    for (ll i = __M; i <= __M * 2 + 1; i ++) {
        seg[i] = INF;
        lazy[i] = 0;
    }
    for (ll i = __M - 1; i >= 1; i --) {
        maintain(i);
        lazy[i] = 0;
    }
}

inline void update(ll l, ll r, ll v) {
    if (l > r) return;
    for (l += __M - 1, r += __M + 1; l ^ r ^ 1;) {
        if (~l & 1) {
            seg[l ^ 1] += v;
            lazy[l ^ 1] += v;
        }
        if (r & 1) {
            seg[r ^ 1] += v;
            lazy[r ^ 1] += v;
        }
        l >>= 1, r >>= 1;
        maintain(l);
        seg[l] += lazy[l];
        maintain(r);
        seg[r] += lazy[r];
    }
    for (l >>= 1; l; l >>= 1) maintain(l);
}

inline void initialize(ll x) {
    update(x, x, -INF);
}

inline ll query(ll l, ll r) {
    if (l > r) return 0;
    bool visl = false, visr = false;
    ll mnl = INF, mnr = INF;
    for (l += __M - 1, r += __M + 1; l ^ r ^ 1;) {
        if (~l & 1) {
            visl = true;
            mnl = min(mnl, seg[l ^ 1]);
        }
        if (r & 1) {
            visr = true;
            mnr = min(mnr, seg[r ^ 1]);
        }
        l >>= 1, r >>= 1;
        if (visl)
            mnl += lazy[l];
        if (visr)
            mnr += lazy[r];
    }
    mnl = min(mnl, mnr);
    for (l >>= 1; l; l >>= 1) {
        mnl += lazy[l];
    }
    return mnl;
}

class Solution {
public:

    int minCost(vector<int>& nums, int k) {
        int N = nums.size();
        nums.insert(nums.begin(), 0);

        // coordinate compression
        unordered_map<int, int> to;
        int on = 0;
        for (int i = 0; i <= N; i ++) {
            if (!to.count(nums[i])) {
                to[nums[i]] = on ++;
            }
        }
        for (int i = 1; i <= N; i ++) {
            nums[i] = to[nums[i]];
        }

        int M = to.size();

        // generate lst
        vector<int> vis(M, - 1);
        vector<int> lst(N + 1);
        for (int i = 1; i <= N; i ++) {
            if (vis[nums[i]] < 0) {
                vis[nums[i]] = i;
                lst[i] = -1;
            } else {
                lst[i] = vis[nums[i]];
                vis[nums[i]] = i;
            }
        }
        
        build(N + 1);

        // dp[0] = 0
        initialize(0 + 1);

        for (ll i = 1; i <= N; i ++) {
            ll pre = lst[i];
            // if the lst isn't -1
            if (pre != -1) {
                ll prepre = lst[pre];
                // update all between (prepre + 1) and (pre) by +2 (counting this one)
                // update all before prepre by +1
                if (prepre == -1) {
                    update(1, pre - 1 + 1, 2);
                } else {
                    update(prepre + 1, pre - 1 + 1, 2);
                    update(1, prepre - 1 + 1, 1);
                }
            }
            // this times' answer is all last times' answer largest + k
            ll ans = query(1, i) + k;
            initialize(i + 1);
            update(i + 1, i + 1, ans);
        }
        return query(N + 1, N + 1);
    }
};