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
// Proof: two numbers share no bit iff b is a submask of ~a. Let best[mask] = largest value whose bits are a submask of
// mask (SOS / subset-max DP over B = bit length of max(nums) bits). For every a the best partner is best[~a & full];
// a itself can never be its own partner (a & a = a != 0 since a >= 1), so distinct indices are automatic. Answer =
// max a * best[~a]. The SOS pass is block-wise (hi half = max(hi, lo)) so it vectorises; when the O(n^2) pair scan is
// cheaper than the table (n^2 / 2 <= B 2^B / 8) it is used instead.
KT long long kern3670(const int* a, int n, int* f) {
    int mx = 0; for (int i = 0; i < n; ++i) mx = a[i] > mx ? a[i] : mx;
    int B = 32 - __builtin_clz((unsigned)mx); int F = 1 << B;
    if ((long long)n * n <= ((long long)B << B) / 4) { long long b = 0; for (int i = 0; i < n; ++i) { long long x = a[i]; for (int j = i + 1; j < n; ++j) if (!(a[i] & a[j])) { long long p = x * a[j]; b = p > b ? p : b; } } return b; }
    for (int x = 0; x < F; ++x) f[x] = 0;
    for (int i = 0; i < n; ++i) f[a[i]] = a[i];
    for (int b = 0; b < B; ++b) { const int bit = 1 << b; for (int base = 0; base < F; base += bit << 1) { int* hi = f + base + bit; const int* lo = f + base; for (int x = 0; x < bit; ++x) hi[x] = hi[x] > lo[x] ? hi[x] : lo[x]; } }
    long long best = 0; int full = F - 1;
    for (int i = 0; i < n; ++i) { long long p = (long long)a[i] * f[~a[i] & full]; best = p > best ? p : best; }
    return best;
}
class Solution { public: long long maxProduct(vector<int>& nums) { static int f[1 << 20]; return kern3670(nums.data(), (int)nums.size(), f); } };