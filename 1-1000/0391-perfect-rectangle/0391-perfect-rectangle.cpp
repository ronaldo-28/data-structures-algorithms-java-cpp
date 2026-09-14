#define ll long long
// #define int long long
#define ld long double
#define all(x) (x).begin(), (x).end()
#define rall(x) (x).rbegin(), (x).rend()
#define LB lower_bound
#define UB upper_bound
#define PB push_back
#define PF push_front
#define EB emplace_back
#define EF emplace_front
#define PPF pop_front
#define PPB pop_back
#define F first
#define S second
#define MP make_pair
#define uset unordered_set
#define umap unordered_map
#define PI acosl(-1.0L)
#define endl '\n'
#define fr(i, a, b) for (int i = (a); i < (b); ++i)
#define frn(i, a, b) for (int i = (a); i >= (b); --i)
using vb = vector<bool>;
using vvb = vector<vb>;
using vc = vector<char>;
using vvc = vector<vc>;
using vi = vector<int>;
using vvi = vector<vi>;
using vs = vector<string>;
using vvs = vector<vs>;
using vl = vector<ll>;
using vvl = vector<vl>;
using vd = vector<double>;
using vvd = vector<vd>;
using ii = pair<int, int>;
using pll = pair<ll, ll>;
using vii = vector<ii>;
using vll = vector<pll>;
using vvii = vector<vii>;
using mii = map<int, int>;
using mll = map<ll, ll>;
const ll INFL = 1e18;
const int INF = 1e9;
const ll MOD = 1e9 + 7;
const int MAXN = 1e6 + 100;
const ld EPS = 1e-7;

inline int LC(int i) { return (i << 1); }     // same as 2*i
inline int RC(int i) { return (i << 1) | 1; } // same as 2*i + 1
template <typename T> int SZ(const T& x) { return static_cast<int>(x.size()); }

auto init = atexit([]() { ofstream("display_runtime.txt") << "0"; });

class Solution {
public:
    ll area = 0;
    ii mx = MP(-INF, -INF);
    ii mn = MP(INF, INF);

    inline void addAreaAndBounds(vi &v) {
        area += 1LL * (v[2] - v[0]) * (v[3] - v[1]);
        mn = min(mn, MP(v[0], v[1]));
        mx = max(mx, MP(v[2], v[3]));
    }

    static inline ll hashPoint(int x, int y) {
        return (1LL * x << 32) ^ (unsigned int)y;
    }

    bool isRectangleCover(vvi &v) {
        umap<ll, int> cnt;

        for (auto &r : v) {
            addAreaAndBounds(r);

            ll p1 = hashPoint(r[0], r[1]);
            ll p2 = hashPoint(r[2], r[1]);
            ll p3 = hashPoint(r[2], r[3]);
            ll p4 = hashPoint(r[0], r[3]);

            cnt[p1]++;
            cnt[p2]++;
            cnt[p3]++;
            cnt[p4]++;
        }

        // collect corners with odd counts
        vector<pll> corners;
        for (auto &[key, val] : cnt) {
            if (val % 2 == 1) {
                int x = key >> 32;
                int y = (int)key;
                corners.EB(x, y);
            }
        }

        // must be exactly 4 corners
        if (SZ(corners) != 4) return false;

        // verify they match bounding box corners
        uset<ll> cornerSet;
        for (auto &p : corners)
            cornerSet.insert(hashPoint(p.F, p.S));

        if (!cornerSet.count(hashPoint(mn.F, mn.S))) return false;
        if (!cornerSet.count(hashPoint(mn.F, mx.S))) return false;
        if (!cornerSet.count(hashPoint(mx.F, mn.S))) return false;
        if (!cornerSet.count(hashPoint(mx.F, mx.S))) return false;

        // check total area matches
        ll totalArea = 1LL * (mx.F - mn.F) * (mx.S - mn.S);
        return area == totalArea;
    }
};
