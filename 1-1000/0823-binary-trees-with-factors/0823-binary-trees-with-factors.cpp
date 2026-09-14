#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int numFactoredBinaryTrees(vector<int>& arr) {
        sort(arr.begin(), arr.end());
        unordered_map<int,long long> dp;
        long long mod = 1000000007, ans = 0;
        for (int x : arr) {
            dp[x] = 1;
            for (int y : arr) {
                if (y * 1LL * y > x) break;
                if (x % y == 0) {
                    int z = x / y;
                    if (dp.count(z)) {
                        if (y == z) dp[x] = (dp[x] + dp[y] * dp[z]) % mod;
                        else dp[x] = (dp[x] + dp[y] * dp[z] * 2) % mod;
                    }
                }
            }
            ans = (ans + dp[x]) % mod;
        }
        return ans;
    }
};
