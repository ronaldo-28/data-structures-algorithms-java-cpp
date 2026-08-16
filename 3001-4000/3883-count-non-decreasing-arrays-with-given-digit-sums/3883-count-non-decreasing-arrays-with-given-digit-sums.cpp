#pragma GCC optimize("O3,unroll-loops,fast-math")
#pragma GCC target("avx2,bmi,bmi2,lzcnt,popcnt")
static const auto fast_io = []() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    return 0;
}();
class Solution {
    static constexpr int MAX_VAL = 5000;
    static constexpr int MAX_SUM = 50;
    static constexpr int MOD = 1000000007;

    static int digit_sum(int x) {
        int s = 0;
        while (x > 0) {
            s += x % 10;
            x /= 10;
        }
        return s;
    }

public:
    int countArrays(vector<int>& digitSum) {
        int n = static_cast<int>(digitSum.size());

        static bool inited = false;
        static array<vector<int>, MAX_SUM + 1> vals;
        if (!inited) {
            for (int s = 0; s <= MAX_SUM; ++s) {
                vals[s].reserve(300); 
            }
            for (int x = 0; x <= MAX_VAL; ++x) {
                int s = digit_sum(x);
                if (s >= 0 && s <= MAX_SUM) {
                    vals[s].push_back(x);
                }
            }
            inited = true;
        }

        for (int s : digitSum) {
            if (s < 0 || s > MAX_SUM || vals[s].empty()) return 0;
        }

        int s0 = digitSum[0];
        const vector<int>& firstVals = vals[s0];
        int m0 = static_cast<int>(firstVals.size());
        
        vector<int> dp(m0, 1);
        vector<int> newDp;
        newDp.reserve(500); 

        for (int i = 1; i < n; ++i) {
            const vector<int>& prevVals = vals[digitSum[i - 1]];
            const vector<int>& curVals  = vals[digitSum[i]];

            int mp = static_cast<int>(prevVals.size());
            int mc = static_cast<int>(curVals.size());

            newDp.assign(mc, 0);

            int p = 0;
            int run = 0;
            for (int j = 0; j < mc; ++j) {
                int v = curVals[j];
                while (p < mp && prevVals[p] <= v) {
                    run += dp[p];
                    if (run >= MOD) run -= MOD;
                    p++;
                }
                newDp[j] = run;
            }

            dp.swap(newDp);
        }

        int ans = 0;
        for (int x : dp) {
            ans += x;
            if (ans >= MOD) ans -= MOD;
        }
        return ans;
    }
};