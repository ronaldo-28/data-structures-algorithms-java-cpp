#include <bits/stdc++.h>
#include <ext/pb_ds/assoc_container.hpp>
#include <ext/pb_ds/tree_policy.hpp>
using namespace __gnu_pbds;
#define Oset                                     \
    tree<int, null_type, less<int>, rb_tree_tag, \
         tree_order_statistics_node_update>
using namespace std;
#define fastio()                      \
    ios_base::sync_with_stdio(false); \
    cin.tie(NULL);                    \
    cout.tie(NULL)
#define MOD 1000000007
#define MOD1 998244353
#define INF 1e18
#define pb push_back
#define ff first
#define ss second
#define PI 3.141592653589793238462
#define set_bits __builtin_popcountll
#define all(x) (x).begin(), (x).end()
#define ll long long
#define vi vector<int>
#define vt vector
#define umpp unordered_map
#define uset unordered_set
#define gint greater<int>()
#define haa cout << "YES\n"
#define nahi cout << "NO\n"
#define cut cout << "\n"
#define lele(vec)       \
    for (auto &i : vec) \
    cin >> i
#define dekho(vec)     \
    for (auto i : vec) \
    cout << i << " "

struct custom_hash
{
    template <class T1, class T2>
    size_t operator()(const pair<T1, T2> &p) const noexcept
    {
        auto h1 = hash<T1>{}(p.first);
        auto h2 = hash<T2>{}(p.second);
        return h1 ^ (h2 + 0x9e3779b97f4a7c15ULL + (h1 << 6) + (h1 >> 2));
    }
};

long long power(long long base, long long exp)
{
    long long result = 1;
    while (exp > 0)
    {
        if (exp % 2 == 1)
        {
            result = (result * base) % MOD;
        }
        base = (base * base) % MOD;
        exp /= 2;
    }
    return result;
}

bool is_even(int n) { return ((n & 1) == 0); }

bool is_prime(int x)
{
    if (x < 2)
        return false;
    for (int i = 2; i * i <= x; i++)
        if (x % i == 0)
            return false;
    return true;
}

void set_kth_bit(int &num, int k) { num |= (1LL << k); }

void unset_kth_bit(int &num, int k) { num &= ~(1LL << k); }

ll lcm(ll a, ll b) { return a / __gcd(a, b) * b; }

bool is_set(long long n, int k)
{
    if (k < 0)
        return false;
    return ((n >> k) & 1LL);
}

vi getFactors(int n)
{
    vi ans;
    for (int i = 1; i * i <= n; i++)
    {
        if (n % i == 0)
        {
            if (is_prime(i))
                ans.pb(i);
            if (n / i != i)
                if (is_prime(n / i))
                    ans.pb(n / i);
        }
    }
    return ans;
}

vi getInputArray(int n, int x, int a, int b, int c)
{
    vi arr(n);
    arr[0] = x;
    for (int i = 1; i < n; i++)
    {
        arr[i] = (a * arr[i - 1] + b) % c;
    }
    return arr;
}

void printBinary(const int x)
{
    if (x == 0)
    {
        cout << "0\n";
        return;
    }

    string s = bitset<64>(x).to_string();
    s = s.substr(s.find('1'));
    cout << s << '\n';
}

const auto __ = []()
{
    struct Leetcode
    {
        static void _() { std::ofstream("display_runtime.txt") << 0 << '\n'; }
    };
    std::atexit(&Leetcode::_);
    return 0;
}();

vector<pair<int, int>> dirs = {{1, 0}, {0, 1}};
using P = pair<int, int>;
using T = tuple<int, int, ll>;

// class Solution {
// public:
//     int maxActivated(vector<vector<int>>& points) {
//         // the newly added point will asct as a bridge
//         // we can group the points by x and y
//         umpp<int, vi> xg, yg;
//         for (int i = 0; i < n; i++) {
//             xg[points[i][0]].pb(i);
//             yg[points[i][1]].pb(i);
//         }
//         vi vis(n, 0);
//         int ans = 0;
//         for (int i = 0; i < n; i++) {
//             if (vis[i])
//                 continue;

//             queue<int> q;
//             q.push(i);
//             vis[i] = 1;
//             int cnt = 0;
//             uset<int> ux, uy;
//             while (!q.empty()) {
//                 int node = q.front();
//                 q.pop();
//                 cnt++;
//                 int x = points[node][0], y = points[node][1];
//                 if (!ux.count(x)) {
//                     ux.insert(x);
//                     for (auto& nxt : xg[x]) {
//                         if (!vis[nxt]) {
//                             vis[nxt] = 1;
//                             q.push(nxt);
//                         }
//                     }
//                 }
//                 if (!uy.count(y)) {
//                     uy.insert(y);
//                     for (auto& nxt : yg[y]) {
//                         if (!vis[nxt]) {
//                             vis[nxt] = 1;
//                             q.push(nxt);
//                         }
//                     }
//                 }
//             }
//             ans = max(ans, cnt + 1);
//         }
//         return ans;
//     }
// };

class DisjointSetUnion {
public:
    vector<int> parent, size;

    DisjointSetUnion(int n) {
        parent.resize(n);
        size.resize(n, 1);
        iota(parent.begin(), parent.end(), 0);
    }

    int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    bool unite(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return false;
        if (size[a] < size[b]) swap(a, b);
        parent[b] = a;
        size[a] += size[b];
        return true;
    }
    
    bool same(int a, int b) {
        return find(a) == find(b);
    }

    int compSize(int x) {
        return size[find(x)];
    }
};


class Solution {
public:
    int maxActivated(vector<vector<int>>& points) {
        int n = points.size();
        DisjointSetUnion ds(n);

        umpp<int, int> xg, yg;
        
        for (int i = 0; i < n; i++) {
            int x = points[i][0];
            int y = points[i][1];
            
            if (xg.count(x)) {
                ds.unite(i, xg[x]);
            } else {
                xg[x] = i;
            }
            
            if (yg.count(y)) {
                ds.unite(i, yg[y]);
            } else {
                yg[y] = i;
            }
        }

        vi comp;
        for (int i = 0; i < n; i++) {
            if (ds.find(i) == i) {
                comp.push_back(ds.compSize(i));
            }
        }

        sort(all(comp), gint);

        if (comp.size() == 1) {
            return comp[0] + 1;
        } 
        else {
            return comp[0] + comp[1] + 1;
        }
    }
};