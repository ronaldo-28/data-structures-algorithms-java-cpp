#pragma region TEMPLATE
#include<bits/stdc++.h>
using namespace std;
using ll = long long;
using uint = unsigned int;
using ull = unsigned long long;
using ld = long double;
using u32 = uint32_t;
using u64 = uint64_t;
using i128 = __int128;
using u128 = unsigned __int128;
using f128 = __float128;

template <class T> constexpr T infty = 0;
template <> constexpr int infty<int> = 1'010'000'000;
template <> constexpr ll infty<ll> = 2'020'000'000'000'000'000;
template <> constexpr u32 infty<u32> = infty<int>;
template <> constexpr u64 infty<u64> = infty<ll>;
template <> constexpr i128 infty<i128> = i128(infty<ll>) * 2'000'000'000'000'000'000;
template <> constexpr double infty<double> = numeric_limits<double>::infinity();
template <> constexpr long double infty<long double> = numeric_limits<long double>::infinity();
constexpr struct NegInfinity { template <class T, enable_if_t<is_arithmetic_v<T> || is_same_v<T, i128> || is_same_v<T, u128>, int> = 0>  constexpr operator T() const { return -infty<T>; } } NINF;
constexpr struct Infinity { template <class T, enable_if_t<is_arithmetic_v<T> || is_same_v<T, i128> || is_same_v<T, u128>, int> = 0> constexpr operator T() const { return infty<T>; }  constexpr NegInfinity operator-() const { return NINF; } } INF;

using pi = pair<ll, ll>;
using vi = vector<int>;
template<typename T = int> 
using P = pair<T, T>;
template <class T> 
using vc = vector<T>;
template <class T>
using vvc = vector<vc<T>>;
template <class T>
using vvvc = vector<vvc<T>>;
template <class T> 
using pq_max = priority_queue<T>;
template <class T, class Compare = greater<T>>
using pq_min = priority_queue<T, vector<T>, Compare>;

#define vv(type, name, h, ...)  \
    vector<vector<type>> name(h, vector<type>(__VA_ARGS__))
#define vvv(type, name, h, w, ...)  \
    vector<vector<vector<type>>> name(  \
        h, vector<vector<type>>(w, vector<type>(__VA_ARGS__)))
#define vvvv(type, name, a, b, c, ...)  \
    vector<vector<vector<vector<type>>>> name(  \
        a, vector<vector<vector<type>>>(    \
            b, vector<vector<type>>(c, vector<type>(__VA_ARGS__))))

#define pb push_back
#define eb emplace_back
#define fi first
#define se second
#define len(x) (int)(x.size())
#define all(x) (x).begin(), (x).end()
#define rall(x) (x).rbegin(), (x).rend()
#define elif else if

#define rep0(x) for (int _ = 0; _ < x; ++_)
#define rep1(i, x) for(int i = 0; i < x; i++)
#define rep2(i, x, y) for(int i = x; i < y; i++)
#define rep3(i, x, y, z) for(int i = x; i < y; i += z)
#define overload4(_1, _2, _3, _4, NAME, ...) NAME
#define rep(...) overload4(__VA_ARGS__, rep3, rep2, rep1, rep0)(__VA_ARGS__)
#define per1(i, y) for(int i = int(y)-1; i >= 0; i--)
#define per2(i, y, x) for(int i = int(y)-1; i >= int(x); i--)
#define per3(i, y, x, z) for(int i = int(y)-1; i >= int(x); i -= z)
#define per(...) overload4(__VA_ARGS__, per3, per2, per1)(__VA_ARGS__)
template<typename T, typename V> ostream& operator<<(ostream &os, const pair<T, V> &p);
template<typename T> ostream& operator<<(ostream &os, const vector<T> &v);
template<typename T, typename V> ostream& operator<<(ostream &os, const pair<T, V> &p) { return os << "(" << p.first << ", " << p.second << ")"; }
template<typename T> ostream& operator<<(ostream& os, const set<T> &v) { os << "["; bool f = false; for(auto &x : v) { if (f) os << ", "; os << x; f = true; } return os << "]"; }
template<typename T> ostream& operator<<(ostream& os, const multiset<T> &v) { os << "["; bool f = false; for(auto &x : v) { if (f) os << ", "; os << x; f = true; } return os << "]"; }
template<typename T> ostream& operator<<(ostream &os, const vector<T> &v) { os << "["; for (int i = 0; i < (int)v.size(); i++) { if (i > 0) os << ", "; os << v[i]; } return os << "]"; }
template<typename T> ostream& operator<<(ostream& os, const deque<T> &v) {os << "["; bool f=false; for (auto &x:v) { if(f)os<<", ";os<<x;f=true; } return os << "]"; }
template<typename T> ostream& operator<<(ostream& os, queue<T> q) { os << "["; bool f = false; while (!q.empty()) { if (f) os << ", "; os << q.front(); q.pop(); f = true; } return os << "]"; }
template<typename T> ostream& operator<<(ostream& os, stack<T> s) { os << "["; bool f = false; while (!s.empty()) { if (f) os << ", "; os << s.top(); s.pop(); f = true; } return os << "]"; }
template <class T, class Container, class Compare> ostream& operator<<(ostream& os, priority_queue<T, Container, Compare> pq) { os << "["; bool f = false; while (!pq.empty()) { if (f) os << ", "; os << pq.top(); pq.pop(); f = true; } return os << "]"; }
template<typename K, typename V> ostream& operator<<(ostream& os, const map<K, V>& m){ os << "{"; bool f = false; for(const auto& kv : m) { if (f) os << ", "; os << kv.first << ": " << kv.second; f = true; } return os << "}"; }
template<typename K, typename V> ostream& operator<<(ostream& os, const unordered_map<K, V>& m) { os << "{"; bool f = false; for (const auto& kv : m) { if (f) os << ", "; os << kv.first << ": " << kv.second; f = true; } return os << "}"; }
void print() { cout << "\n"; }
template<typename Head, typename... Tail> 
void print(const Head& head, const Tail&... tail) { cout << head; if (sizeof...(tail) > 0) cout << " "; print(tail...); }
template<typename... T> void prints(const T&... args) { ((cout  << args << ""), ...); }
template<typename T> struct U { const T& v; };
template<typename T> ostream& operator<<(ostream& os, const U<T>& u) { bool f = false; for (const auto& x : u.v) { if (f) os << " "; os << x; f = true; } return os; }
template <int Precision = 10 > void set_float() { cout << fixed << setprecision(Precision); }
void Yes(bool b = true) { print(b ? "Yes" : "No"); }
void YES(bool b = true) { print(b ? "YES" : "NO"); }
void No(bool b = true) { Yes(!b); }
void NO(bool b = true) { YES(!b); }

struct FastIO { FastIO() { ios::sync_with_stdio(false); cin.tie(NULL); } } fastio;
template<class T> void rd(T& x) { cin >> x; }
template<class T, class U> void rd(pair<T, U> &p) { rd(p.first); rd(p.second); }
template<class T> void rd(vector<T>& v) { for (auto& x:v) rd(x); }
void read() {}
template <class H, class ... T>
void read(H &h, T &... t) { rd(h); read(t...); }
#define INT(...)  int __VA_ARGS__; read(__VA_ARGS__)
#define LL(...)  ll __VA_ARGS__; read(__VA_ARGS__)
#define STR(...) string __VA_ARGS__; read(__VA_ARGS__)
#define CHAR(...) char __VA_ARGS__; read(__VA_ARGS__)
#define ULL(...) ull __VA_ARGS__; read(__VA_ARGS__)
#define VEC(type, name, size) vector<type> name(size); read(name)
#define VV(type, name, h, w) vector<vector<type>> name(h, vector<type>(w)); read(name)

template<class T> inline bool chmax(T &a, T b){if (a < b) {a = b;return 1;}return 0;}
template<class T> inline bool chmin(T &a, T b){if (a > b) {a = b;return 1;}return 0;}
#define UNIQUE(v) sort(all(v)), v.erase(unique(all(v)), v.end()), v.shrink_to_fit()
template<typename T> T kth_bit(int k) { return T(1) << k; }
template<typename T> bool has_kth_bit(T x, int k) { return x >> k & 1; }
template <typename T> T floor(T a, T b) { return a / b - (a % b && (a ^ b) < 0); }
template <typename T> T ceil(T a, T b) { return a / b + (a % b != 0 && (a ^ b) > 0); }
template <typename T> T bmod(T x, T y) { return x - y * floor(x, y); }
template <typename T> pair<T, T> divmod(T x, T y) { T q = floor(x, y); return {q, x - q * y}; }
template <typename T, typename U> T SUM(const U &v) { return std::accumulate(v.begin(), v.end(), T{}); }
#define MIN(v) *min_element(all(v));
#define MAX(v) *max_element(all(v));
template <class C, class T>
inline long long LB(const C &c, const T &x) {
    return lower_bound(c.begin(), c.end(), x) - c.begin();
}
template <class C, class T>
inline long long UB(const C &c, const T &x) {
    return upper_bound(c.begin(), c.end(), x) - c.begin();
}
template <typename T> T POP(stack<T> &stack) { T a = stack.top(); stack.pop(); return a; }
template <typename T> T POP(queue<T> &q) { T a = q.front(); q.pop(); return a; }
template <typename T>  T POP(deque<T> &dq) { T a = dq.front(); dq.pop_front(); return a; }
template <typename T> T POP(vc<T> &v) { T a = v.back(); v.pop_back(); return a; }
template <class T, class Container, class Compare>  T POP(priority_queue<T, Container, Compare> & pq) { T a = pq.top(); pq.pop(); return a; }
template <typename T> bool isVowel(T c) { return (0x208222 >> (static_cast<char>(c) & 0x1f)) & 1; }
template <typename T, typename U>
vc<T> cumsum(const vc<U> &A, int off = 1) {
    int N = A.size();
    vc<T> B(N+1);
    rep(i, N) { B[i+1] = B[i] + A[i]; }
    if (off == 0) B.erase(B.begin());
    return B;
}
template <typename T>
vc<T> rearrange(const vc<T> &A, const vc<int> &I) {
    vc<T> B(len(I));
    rep(i, len(I)) B[i] = A[I[i]];
    return B;
}
template <typename T, typename... Vectors>
void concat(vc<T> &first, const Vectors &...others) {
    vc<T> &res = first;
    (res.insert(res.end(), others.begin(), others.end()), ...);
}
template <typename T> 
map<T, int> Counter(const vc<T> &A) {
    map<T, int> counter; 
    for(const auto &a:A) { counter[a]++; } 
    return counter; 
}
template <typename STRING>
bool is_subseq(STRING &S, STRING &T, int repeat = 1) {
    if (repeat <= 0) return 0;
    ll p = 0;
    rep(i, repeat) {
        for (auto &&s:S) {
            while (p < len(T) && T[p] != s) p++;
            if (p == len(T)) return false;
            p++;
        }
    }
    return true;
}
template<typename T> T gcd(T a, T b) { return b ? gcd(b, a % b) : a; }
template<typename T = long long> T lcm(T a, T b) { 
    if (a == 0 || b == 0) return 0;
    return (T)((unsigned __int128)a / gcd(a, b) * b); 
}
// (0, 1, 2, 3, 4) -> (-1, 0, 1, 1, 2)
inline int topbit(int x) { return (x == 0 ? -1 : 31 - __builtin_clz(x)); }
inline int topbit(ll x) { return (x == 0 ? -1 : 63 - __builtin_clzll(x)); }
inline int topbit(u32 x) { return x == 0 ? -1 : 31 - __builtin_clz(x); }
inline int topbit(u64 x) { return x == 0 ? -1 : 63 - __builtin_clzll(x); }
// (0, 1, 2, 3, 4) -> (-1, 0, 1, 0, 2)
inline int lowbit(int x) { return (x == 0 ? -1 : __builtin_ctz(x)); }
inline int lowbit(u32 x) { return (x == 0 ? -1 : __builtin_ctz(x)); }
inline int lowbit(ll x) { return (x == 0 ? -1 : __builtin_ctzll(x)); }
inline int lowbit(u64 x) { return (x == 0 ? -1 : __builtin_ctzll(x)); }

int dx[] = {-1, 0, 1, 0};
int dy[] = {0, 1, 0, -1};
#pragma endregion TEMPLATE

class Solution {
public:
    int componentValue(vector<int>& nums, vector<vector<int>>& edges) {
        int n = len(nums);
        vvc<int> to(n);
        for (auto &e:edges) {
            int u = e[0], v = e[1];
            to[u].pb(v);
            to[v].pb(u);
        }
        int sum = 0, mx_val = 0;
        for (int x:nums) {
            sum += x;
            chmax(mx_val, x);
        }

        vc<int> order, parent(n, -1);
        order.reserve(n);
        order.pb(0);
        parent[0] = 0;
        int head = 0;
        while (head < n) {
            int u = order[head++];
            for(int v : to[u]) {
                if (parent[v] != -1) continue;
                parent[v] = u;
                order.pb(v);
            }
        }

        reverse(all(order));
        int MAXK = sum/mx_val;
        per(k, MAXK+1, 1) {
            if (sum % k != 0) continue;
            int target = sum/k;
            vc<int> vals = nums;
            bool ok = true;
            for (int u:order) {
                if (vals[u] > target) {
                    ok = false;
                    break;
                }
                if (vals[u] < target) {
                    if (u != 0) vals[parent[u]] += vals[u];
                    else ok = false;
                }
            }
            if (ok) return k-1;
        }
        return 0;
    }
};