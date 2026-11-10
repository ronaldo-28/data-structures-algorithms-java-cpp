#include <bits/stdc++.h>
#include <immintrin.h>
using namespace std;

class Solution {
    static constexpr uint32_t MASK = (1u << 17) - 1;

    // k == 2:
    // dynamic set of positions where nums[i] is even.
    // 3-level bitset => successor query essentially O(1).
    struct NextSet {
        static constexpr int W0 = (100000 + 63) >> 6;
        static constexpr int W1 = (W0 + 63) >> 6;

        uint64_t a[W0]{}, b[W1]{}, top = 0;
        int n;

        explicit NextSet(int n) : n(n) {}

        void build(const vector<int>& nums) {
            for (int i = 0; i < n; ++i)
                if (!(nums[i] & 1))
                    a[i >> 6] |= 1ull << (i & 63);

            int nw = (n + 63) >> 6;

            for (int i = 0; i < nw; ++i)
                if (a[i])
                    b[i >> 6] |= 1ull << (i & 63);

            int nb = (nw + 63) >> 6;

            for (int i = 0; i < nb; ++i)
                if (b[i])
                    top |= 1ull << i;
        }

        inline void set(int i, bool on) {
            int x = i >> 6;

            uint64_t old = a[x];
            uint64_t m = 1ull << (i & 63);
            uint64_t now = on ? old | m : old & ~m;

            if (old == now) return;

            a[x] = now;

            // word remained nonempty / empty
            if ((old == 0) == (now == 0))
                return;

            int y = x >> 6;

            old = b[y];
            m = 1ull << (x & 63);
            now = a[x] ? old | m : old & ~m;

            b[y] = now;

            if ((old == 0) == (now == 0))
                return;

            m = 1ull << y;

            if (now) top |= m;
            else top &= ~m;
        }

        inline int next(int p) const {
            int x = p >> 6;
            int s = p & 63;

            uint64_t z = a[x] & (~0ull << s);

            if (z)
                return (x << 6) + __builtin_ctzll(z);

            int y = x >> 6;
            int t = (x & 63) + 1;

            z = t < 64
                ? b[y] & (~0ull << t)
                : 0;

            if (z) {
                x = (y << 6) + __builtin_ctzll(z);
                return min(
                    n,
                    (x << 6) + __builtin_ctzll(a[x])
                );
            }

            ++y;

            z = y < 64
                ? top & (~0ull << y)
                : 0;

            if (!z)
                return n;

            y = __builtin_ctzll(z);
            x = (y << 6) + __builtin_ctzll(b[y]);

            return min(
                n,
                (x << 6) + __builtin_ctzll(a[x])
            );
        }
    };

    template<int K>
    struct Node {
        // a[0]:
        // low 17 bits = cnt[0]
        // bits 17.. = product
        uint32_t a[K];
    };

    template<int K>
    struct Seg {
        int n, S;
        Node<K>* t;

        static inline int prod(const Node<K>& z) {
            return z.a[0] >> 17;
        }

        static inline int cnt(const Node<K>& z, int r) {
            return r ? z.a[r] : z.a[0] & MASK;
        }

        static inline int mul(int x, int y) {
            if constexpr (K == 4)
                return (x * y) & 3;
            else
                return x * y % K;
        }

        static inline Node<K> id() {
            Node<K> z{};
            z.a[0] = 1u << 17;
            return z;
        }

        static inline Node<K> leaf(int v) {
            Node<K> z{};

            z.a[0] = (uint32_t)v << 17;

            if (v)
                z.a[v] = 1;
            else
                z.a[0] |= 1;

            return z;
        }

        static inline Node<K>
        merge(const Node<K>& L, const Node<K>& R) {
            Node<K> o;

            int p = prod(L);
            int q = prod(R);
            int np = mul(p, q);

            uint32_t l0 = L.a[0] & MASK;
            uint32_t r0 = R.a[0] & MASK;

            if constexpr (K == 3) {
                uint32_t o0;
                uint32_t o1 = L.a[1];
                uint32_t o2 = L.a[2];

                if (!p) {
                    o0 = l0 + r0 + R.a[1] + R.a[2];
                }
                else if (p == 1) {
                    o0 = l0 + r0;
                    o1 += R.a[1];
                    o2 += R.a[2];
                }
                else {
                    o0 = l0 + r0;
                    o1 += R.a[2];
                    o2 += R.a[1];
                }

                o.a[0] = o0 | ((uint32_t)np << 17);
                o.a[1] = o1;
                o.a[2] = o2;
            }

            else if constexpr (K == 4) {
                uint32_t o0;
                uint32_t o1 = L.a[1];
                uint32_t o2 = L.a[2];
                uint32_t o3 = L.a[3];

                if (!p) {
                    o0 =
                        l0 + r0 +
                        R.a[1] + R.a[2] + R.a[3];
                }

                else if (p == 1) {
                    o0 = l0 + r0;

                    o1 += R.a[1];
                    o2 += R.a[2];
                    o3 += R.a[3];
                }

                else if (p == 2) {
                    // 2*0,2 -> 0
                    // 2*1,3 -> 2
                    o0 = l0 + r0 + R.a[2];
                    o2 += R.a[1] + R.a[3];
                }

                else {
                    // multiply by 3:
                    // 1 <-> 3, 2 -> 2
                    o0 = l0 + r0;

                    o1 += R.a[3];
                    o2 += R.a[2];
                    o3 += R.a[1];
                }

                o.a[0] = o0 | ((uint32_t)np << 17);
                o.a[1] = o1;
                o.a[2] = o2;
                o.a[3] = o3;
            }

            else {  // K == 5
                uint32_t o0 = l0 + r0;

                // cnt[1..4] = exactly 4 x 32 bit.
                // SSE2 is enough; AVX2 gives nothing here.
                __m128i lv =
                    _mm_loadu_si128(
                        (const __m128i*)&L.a[1]
                    );

                if (!p) {
                    o0 +=
                        R.a[1] + R.a[2] +
                        R.a[3] + R.a[4];

                    _mm_storeu_si128(
                        (__m128i*)&o.a[1],
                        lv
                    );
                }
                else {
                    __m128i rv =
                        _mm_loadu_si128(
                            (const __m128i*)&R.a[1]
                        );

                    // destination contributions to
                    // residues 1,2,3,4

                    if (p == 2)
                        rv = _mm_shuffle_epi32(
                            rv,
                            _MM_SHUFFLE(1,3,0,2)
                        );

                    else if (p == 3)
                        rv = _mm_shuffle_epi32(
                            rv,
                            _MM_SHUFFLE(2,0,3,1)
                        );

                    else if (p == 4)
                        rv = _mm_shuffle_epi32(
                            rv,
                            _MM_SHUFFLE(0,1,2,3)
                        );

                    _mm_storeu_si128(
                        (__m128i*)&o.a[1],
                        _mm_add_epi32(lv, rv)
                    );
                }

                o.a[0] =
                    o0 | ((uint32_t)np << 17);
            }

            return o;
        }

        explicit Seg(const vector<int>& nums)
            : n(nums.size()), S(1) {

            while (S < n)
                S <<= 1;

            // malloc: avoids vector value-initializing
            // several MB that are immediately overwritten.
            t = (Node<K>*)malloc(
                sizeof(Node<K>) * 2 * S
            );

            for (int i = 0; i < n; ++i)
                t[S + i] = leaf(nums[i] % K);

            for (int i = S + n; i < 2 * S; ++i)
                t[i] = id();

            for (int i = S - 1; i; --i)
                t[i] = merge(
                    t[i << 1],
                    t[i << 1 | 1]
                );
        }

        ~Seg() {
            free(t);
        }

        inline void update(int i, int v) {
            v %= K;

            int c = S + i;

            // Semantic no-op.
            if (prod(t[c]) == v)
                return;

            t[c] = leaf(v);

            while (c > 1) {
                int p = c >> 1;

                // Changed child is right.
                //
                // If left product is already 0,
                // every right prefix maps to 0.
                // Since right segment length is fixed,
                // this parent cannot change at all.
                if (
                    (c & 1) &&
                    prod(t[p << 1]) == 0
                )
                    break;

                t[p] = merge(
                    t[p << 1],
                    t[p << 1 | 1]
                );

                c = p;
            }
        }

        // Contribution of one whole segment,
        // given product p before entering it.
        static inline int
        hit(const Node<K>& z, int p, int x) {
            if constexpr (K == 3) {
                if (!p)
                    return x
                        ? 0
                        : cnt(z,0) +
                          z.a[1] + z.a[2];

                static constexpr uint8_t div[3][3] = {
                    {0,0,0},
                    {0,1,2},
                    {0,2,1}
                };

                return cnt(z, div[p][x]);
            }

            else if constexpr (K == 4) {
                if (!p)
                    return x
                        ? 0
                        : cnt(z,0) +
                          z.a[1] +
                          z.a[2] +
                          z.a[3];

                if (p == 1)
                    return cnt(z, x);

                if (p == 2) {
                    if (x == 0)
                        return cnt(z,0) + z.a[2];

                    if (x == 2)
                        return z.a[1] + z.a[3];

                    return 0;
                }

                // inverse of 3 mod 4 is 3
                return cnt(z, (3 * x) & 3);
            }

            else {
                if (!p)
                    return x
                        ? 0
                        : cnt(z,0) +
                          z.a[1] +
                          z.a[2] +
                          z.a[3] +
                          z.a[4];

                // r = x / p mod 5
                static constexpr uint8_t div[5][5] = {
                    {0,0,0,0,0},
                    {0,1,2,3,4},
                    {0,3,1,4,2},
                    {0,2,4,1,3},
                    {0,4,3,2,1}
                };

                return cnt(z, div[p][x]);
            }
        }

        inline int query(int start, int x) const {
            int p = 1;
            int ans = 0;

            int l = S + start;
            int r = S << 1;

            // Hot path: x != 0.
            //
            // No need to track current array position,
            // because once product becomes 0 there can
            // never be another nonzero answer.
            if (x) {
                while (l < r) {
                    if (l & 1) {
                        const Node<K>& z = t[l++];

                        ans += hit(z, p, x);
                        p = mul(p, prod(z));

                        if (!p)
                            break;

                        if constexpr (K == 4) {
                            // Once product == 2, it can
                            // only remain 2 or become 0.
                            if ((x & 1) && p == 2)
                                break;
                        }
                    }

                    l >>= 1;
                    r >>= 1;
                }

                return ans;
            }

            // x == 0:
            // once accumulated product reaches zero,
            // every remaining endpoint contributes.
            int level = 0;
            int pos = start;

            while (l < r) {
                if (l & 1) {
                    const Node<K>& z = t[l++];

                    ans += hit(z, p, 0);
                    p = mul(p, prod(z));

                    pos += 1 << level;

                    if (!p) {
                        if (pos < n)
                            ans += n - pos;
                        break;
                    }
                }

                l >>= 1;
                r >>= 1;
                ++level;
            }

            return ans;
        }
    };

    template<int K>
    static vector<int>
    solveK(
        vector<int>& nums,
        vector<vector<int>>& queries
    ) {
        Seg<K> st(nums);

        vector<int> ans;
        ans.reserve(queries.size());

        for (auto& q : queries) {
            st.update(q[0], q[1]);
            ans.push_back(
                st.query(q[2], q[3])
            );
        }

        return ans;
    }

public:
    vector<int> resultArray(
        vector<int>& nums,
        int k,
        vector<vector<int>>& queries
    ) {
        const int n = nums.size();

        if (k == 1) {
            vector<int> ans;
            ans.reserve(queries.size());

            for (auto& q : queries)
                ans.push_back(n - q[2]);

            return ans;
        }

        // Complete specialization.
        if (k == 2) {
            NextSet zero(n);
            zero.build(nums);

            vector<int> ans;
            ans.reserve(queries.size());

            for (auto& q : queries) {
                zero.set(
                    q[0],
                    !(q[1] & 1)
                );

                int z = zero.next(q[2]);

                int ones = z - q[2];
                int len  = n - q[2];

                ans.push_back(
                    q[3]
                        ? ones
                        : len - ones
                );
            }

            return ans;
        }

        if (k == 3)
            return solveK<3>(nums, queries);

        if (k == 4)
            return solveK<4>(nums, queries);

        return solveK<5>(nums, queries);
    }
};