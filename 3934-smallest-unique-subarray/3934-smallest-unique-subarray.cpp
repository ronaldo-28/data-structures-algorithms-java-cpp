// v3 kernel pattern: hot code in raw-pointer functions exempt from ASan/UBSan (KN/KT only on those functions).
// No ASan option overrides, no pre-main work, plain % for modular reduction. Tables: constexpr or lazy on first call.
#ifdef LC_DEBUG
#define KN static
#define KT static
#else
#define KN __attribute__((noinline, no_sanitize("address", "undefined"))) static
#define KT __attribute__((noinline, no_sanitize("address", "undefined"), target("popcnt,bmi,bmi2,lzcnt,avx2"))) static
#endif
#ifdef LC_DEBUG
#define KI static inline
#else
#define KI __attribute__((always_inline, no_sanitize("address", "undefined"))) static inline
#endif  // KI: helpers inlined into kernels (lambdas/plain helpers would stay out-of-line and ASan-instrumented)
// Proof: a subarray starting at i of length L is unique iff L exceeds the longest common prefix of suffix i with every
// other suffix, i.e. L > h(i) = max(lcp with its suffix-array neighbours); the shortest unique one starting at i has
// length h(i) + 1 if that fits (h(i) + 1 <= n - i). The answer is the minimum over i (the whole array always works).
// Exact (no hashing): suffix array by prefix doubling with LSD radix passes, Kasai LCP. O(n log n).
KN int kern3934(const int* a, int n, int* sa, int* rk, int* tmp, int* cnt, int* lcp, int* sec) {
    if (n == 1) return 1;
    int V = 0; for (int i = 0; i < n; ++i) if (a[i] > V) V = a[i];   // counting sort range = max value
    for (int i = 0; i <= V; ++i) cnt[i] = 0; for (int i = 0; i < n; ++i) ++cnt[a[i]];
    for (int i = 1; i <= V; ++i) cnt[i] += cnt[i - 1]; for (int i = n - 1; i >= 0; --i) sa[--cnt[a[i]]] = i;
    rk[sa[0]] = 0; int cls = 1; for (int i = 1; i < n; ++i) { if (a[sa[i]] != a[sa[i - 1]]) ++cls; rk[sa[i]] = cls - 1; }
    for (int h = 1; cls < n; h <<= 1) {
        int p = 0; for (int i = n - h; i < n; ++i) sec[p++] = i; for (int i = 0; i < n; ++i) if (sa[i] >= h) sec[p++] = sa[i] - h;
        for (int i = 0; i < cls; ++i) cnt[i] = 0; for (int i = 0; i < n; ++i) ++cnt[rk[i]]; for (int i = 1; i < cls; ++i) cnt[i] += cnt[i - 1];
        for (int i = n - 1; i >= 0; --i) sa[--cnt[rk[sec[i]]]] = sec[i];
        tmp[sa[0]] = 0; cls = 1;
        for (int i = 1; i < n; ++i) { int x = sa[i], y = sa[i - 1]; int rx = x + h < n ? rk[x + h] : -1, ry = y + h < n ? rk[y + h] : -1;
            if (rk[x] != rk[y] || rx != ry) ++cls; tmp[x] = cls - 1; }
        for (int i = 0; i < n; ++i) rk[i] = tmp[i]; }
    int k = 0; for (int i = 0; i < n; ++i) { if (rk[i] == 0) { k = 0; lcp[0] = 0; continue; } int j = sa[rk[i] - 1];
        while (i + k < n && j + k < n && a[i + k] == a[j + k]) ++k; lcp[rk[i]] = k; if (k) --k; }   // lcp[r] = lcp(sa[r-1], sa[r])
    int best = n;
    for (int r = 0; r < n; ++r) { int h = r ? lcp[r] : 0; if (r + 1 < n && lcp[r + 1] > h) h = lcp[r + 1]; if (h + 1 <= n - sa[r] && h + 1 < best) best = h + 1; }
    return best;
}
class Solution { public: int smallestUniqueSubarray(vector<int>& nums) {
    static int sa[100005], rk[100005], tmp[100005], cnt[100005], lcp[100005], sec[100005]; return kern3934(nums.data(), (int)nums.size(), sa, rk, tmp, cnt, lcp, sec); } };