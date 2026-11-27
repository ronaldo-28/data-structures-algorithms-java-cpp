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
// Proof: same state reduction as 3699: with m = r-l+1 values the vector U (arrays ending in v after an up-step) evolves
// by the fixed linear map U'[v] = sum_{w >= m-v} U[w] (D is the mirror of U), starting from U[v] = v at length 2;
// answer = 2 * sum U after n-2 steps. n is up to 1e9, so apply T^(n-2) by binary powering: square the m x m matrix
// T (T[v][w] = 1 iff w >= m-v) and multiply the vector by the squares whose bit is set. 64-bit accumulation with a
// reduction every 16 products (16 * (1e9+7)^2 < 2^64). O(m^3 log n), m <= 75; buffers static.
KN void mul3700(const unsigned* A, const unsigned* B, unsigned* C, int m) {
    const unsigned long long M = 1000000007;
    for (int i = 0; i < m; ++i) { unsigned long long acc[75]; for (int j = 0; j < m; ++j) acc[j] = 0;
        for (int k = 0; k < m; ++k) { unsigned long long a = A[i * m + k]; if (!a) continue; const unsigned* b = B + k * m; for (int j = 0; j < m; ++j) acc[j] += a * b[j];
            if ((k & 15) == 15) for (int j = 0; j < m; ++j) acc[j] %= M; }
        for (int j = 0; j < m; ++j) C[i * m + j] = (unsigned)(acc[j] % M); }
}
KN int kern3700(int n, int m, unsigned* P, unsigned* Q, unsigned* v, unsigned* w) {
    const unsigned long long M = 1000000007;
    for (int i = 0; i < m; ++i) for (int j = 0; j < m; ++j) P[i * m + j] = j >= m - i ? 1u : 0u;   // new[i] = sum_j P[i][j] old[j]
    for (int i = 0; i < m; ++i) v[i] = (unsigned)i;
    long long e = (long long)n - 2;
    while (e) { if (e & 1) { for (int i = 0; i < m; ++i) { unsigned long long acc = 0; for (int j = 0; j < m; ++j) { acc += (unsigned long long)P[i * m + j] * v[j]; if ((j & 15) == 15) acc %= M; } w[i] = (unsigned)(acc % M); }
            for (int i = 0; i < m; ++i) v[i] = w[i]; }
        e >>= 1; if (e) { mul3700(P, P, Q, m); for (int i = 0; i < m * m; ++i) P[i] = Q[i]; } }
    unsigned long long t = 0; for (int i = 0; i < m; ++i) t += v[i];
    return (int)(t % M * 2 % M);
}
class Solution { public: int zigZagArrays(int n, int l, int r) { static unsigned P[75 * 75], Q[75 * 75], v[75], w[75]; return kern3700(n, r - l + 1, P, Q, v, w); } };