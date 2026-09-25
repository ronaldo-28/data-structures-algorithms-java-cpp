class Solution {
private:
    static constexpr uint8_t P = 13;
    static constexpr uint16_t A = 1u << P;

    static char precomp[A][P+1];

    static void copy_adj(const char *s, uint8_t l, const uint8_t a, char *d) noexcept __attribute__((always_inline, hot)) {
        *d = *s + a;
        d += !!l; s += !!l; l -= !!l;
        *d = *s + a;
        d += !!l; s += !!l; l -= !!l;
        *d = *s + a;
        d += !!l; s += !!l; l -= !!l;
        *d = *s + a;
        d += !!l; s += !!l; l -= !!l;
        *d = *s + a;
        d += !!l; s += !!l; l -= !!l;
        *d = *s + a;
        d += !!l; s += !!l; l -= !!l;
        *d = *s + a;
        d += !!l; s += !!l; l -= !!l;
        *d = *s + a;
        d += !!l; s += !!l; l -= !!l;
        *d = *s + a;
        d += !!l; s += !!l; l -= !!l;
        *d = *s + a;
        d += !!l; s += !!l; l -= !!l;
        *d = *s + a;
        d += !!l; s += !!l; l -= !!l;
        *d = *s + a;
        d += !!l; s += !!l; l -= !!l;
        *d = *s + a;
        d += !!l; s += !!l; l -= !!l;
    }

public:
    static vector<string> largestString(const vector<int> &nums) noexcept __attribute__((hot)) {
        vector<string> r;
        r.reserve(nums.size());
        for (uint v : nums) {
            const uint8_t b = 31 - __builtin_clz(v);
            char d[26];
            *(uint16_t*)d = 0x7A7A & -(b == 26); // "zz"
            uint8_t k = (b == 26) * 2;
            const auto h = precomp[v>>13&A-1];
            const uint8_t hl = h[0];
            copy_adj(h + 1, hl, 0xD, d + k);
            k += hl;
            const auto l = precomp[v&A-1];
            const uint8_t ll = l[0];
            copy_adj(l + 1, ll, 0, d + k);
            r.emplace_back(d, d + (k + ll));
        }
        return r;
    }

    static void init() noexcept __attribute__((hot, target("sse4"))) {
        for (uint i = 1; i < A; i++) {
            const auto p = precomp[i];
            p[0] = __builtin_popcount(i);
            uint8_t l = 1;
            for (uint v = i; v; ) {
                const uint8_t b = 31 - __builtin_clz(v);
                p[l++] = 'a' + b;
                v &= ~(1u << b);
            }
        }
    }
};

char Solution::precomp[A][P+1];

static const auto init = []() noexcept {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    Solution::init();
    return 'c';
}();