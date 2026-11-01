#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
#include <set>
#include <cmath>
#include <numeric>
#include <queue>
#include <stack>
#include <iomanip>
#include <array>
#include <functional>
#include <bitset>
using namespace std;

void baseIO(string s = "") {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    if (s.size()) {
        freopen((s + ".in").c_str(), "r", stdin);
        freopen((s + ".out").c_str(), "w", stdout);
    }
}

int subcount = 326;

#define ll long long
#define db long double
#define all(x) (x).begin(), (x).end()

class Solution {
public:
    int minActivationCost(vector<vector<int>>& edges, vector<int>& a) {
        int ans = INT_MAX;
        int n = a.size();
        for (auto v : edges) {
            if (__builtin_popcount(a[v[0]]) % 2 == __builtin_popcount(a[v[1]]) % 2) {
                ans = min(ans, v[2]);
            }
        }

        vector<vector<int>> adj(n);
        for (auto v : edges) {
            if (__builtin_popcount(a[v[0]]) % 2 == 0 && __builtin_popcount(a[v[1]]) % 2 == 1) adj[v[0]].push_back(v[2]);
            else if (__builtin_popcount(a[v[0]]) % 2 == 1 && __builtin_popcount(a[v[1]]) % 2 == 0) adj[v[1]].push_back(v[2]);
        }

        for (int r = 0; r < n; r++) {
            if (adj[r].size() >= 2) {
                sort(all(adj[r]));
                ans = min(ans, adj[r][0] + adj[r][1]);
            }
        }

        if (ans == INT_MAX) return -1;
        return ans;
    }
};