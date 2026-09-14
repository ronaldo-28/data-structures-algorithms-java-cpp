class Solution {
    using ull = unsigned long long;
    struct RH {
        vector<ull> h, p;
        ull base;
        RH() {}
        RH(const string& s, ull b = 1315423911ULL) : base(b) {
            int n = (int)s.size();
            h.assign(n + 1, 0);
            p.assign(n + 1, 1);
            for (int i = 0; i < n; ++i) {
                h[i + 1] = h[i] * base + (ull)(s[i] - 'a' + 1);
                p[i + 1] = p[i] * base;
            }
        }
        ull get(int l, int r) const {
            if (l > r) return 0;
            return h[r + 1] - h[l] * p[r - l + 1];
        }
    };
    
    int n;
    string s, rs;
    RH hs, hrs;
    vector<ull> pw;
    
    ull segHash(int type, int l, int r) {
        if (l > r) return 0;
        if (type == 0) return hs.get(l, r);
        return hrs.get(l, r);
    }
    
    ull candidateHash(bool pref, int k, int len) {
        if (len <= 0) return 0;
        if (pref) {
            if (len <= k) {
                int rl = n - k;
                return hrs.get(rl, rl + len - 1);
            } else {
                ull a = hrs.get(n - k, n - 1);
                ull b = hs.get(k, k + (len - k) - 1);
                return a * pw[len - k] + b;
            }
        } else {
            int left = n - k;
            if (len <= left) {
                return hs.get(0, len - 1);
            } else {
                ull a = hs.get(0, left - 1);
                ull b = hrs.get(0, len - left - 1);
                return a * pw[len - left] + b;
            }
        }
    }
    
    char candidateChar(bool pref, int k, int idx) {
        if (pref) {
            if (idx < k) return s[k - 1 - idx];
            return s[idx];
        } else {
            int left = n - k;
            if (idx < left) return s[idx];
            return s[n - 1 - (idx - left)];
        }
    }
    
    int lcp(bool p1, int k1, bool p2, int k2) {
        int lo = 0, hi = n;
        while (lo < hi) {
            int mid = (lo + hi + 1) >> 1;
            if (candidateHash(p1, k1, mid) == candidateHash(p2, k2, mid)) lo = mid;
            else hi = mid - 1;
        }
        return lo;
    }
    
    bool better(bool p1, int k1, bool p2, int k2) {
        int common = lcp(p1, k1, p2, k2);
        if (common == n) return false;
        return candidateChar(p1, k1, common) < candidateChar(p2, k2, common);
    }
    
    string build(bool pref, int k) {
        string t = s;
        if (pref) reverse(t.begin(), t.begin() + k);
        else reverse(t.end() - k, t.end());
        return t;
    }
public:
    string lexSmallest(string s_) {
        s = s_;
        n = (int)s.size();
        rs = s;
        reverse(rs.begin(), rs.end());
        hs = RH(s);
        hrs = RH(rs, hs.base);
        pw = hs.p;
        
        bool bestPref = true;
        int bestK = 1;
        
        for (int k = 1; k <= n; ++k) {
            if (better(true, k, bestPref, bestK)) {
                bestPref = true;
                bestK = k;
            }
            if (better(false, k, bestPref, bestK)) {
                bestPref = false;
                bestK = k;
            }
        }
        return build(bestPref, bestK);
    }
};