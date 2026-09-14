#pragma GCC optimize("O3,unroll-loops")
#pragma GCC target("avx2,bmi,bmi2,lzcnt,popcnt")
#pragma clang optimize on
#pragma clang diagnostic ignored "-Wc++20-extensions"
static const auto _io = []() noexcept {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    return 0;
}();

namespace wyhash_impl {

[[clang::always_inline]] inline
uint64_t wymix(uint64_t a, uint64_t b) noexcept {
    __uint128_t r = static_cast<__uint128_t>(a) * b;
    return static_cast<uint64_t>(r) ^ static_cast<uint64_t>(r >> 64);
}

static constexpr uint64_t SECRET0 = 0xa0761d6478bd642fULL;
static constexpr uint64_t SECRET1 = 0xe7037ed1a0b428dbULL;
static constexpr uint64_t SECRET2 = 0x8ebc6af09c88c6e3ULL;

[[clang::always_inline]] inline
uint64_t hash_sv(const char* p, size_t len) noexcept {
    uint64_t seed = SECRET0 ^ static_cast<uint64_t>(len);

    while (len >= 16) {
        uint64_t a, b;
        __builtin_memcpy(&a, p,     8);   
        __builtin_memcpy(&b, p + 8, 8);
        seed = wymix(seed ^ a, SECRET1 ^ b);
        p   += 16;
        len -= 16;
    }

    uint64_t a = 0, b = 0;
    if (len >= 8) {
        __builtin_memcpy(&a, p,          8);
        __builtin_memcpy(&b, p + len - 8, 8); 
    } else if (len >= 4) {
        uint32_t lo, hi;
        __builtin_memcpy(&lo, p,          4);
        __builtin_memcpy(&hi, p + len - 4, 4);
        a = (static_cast<uint64_t>(hi) << 32) | lo;
    } else if (len > 0) {
        a = (static_cast<uint64_t>(static_cast<uint8_t>(p[0])) << 16)
          | (static_cast<uint64_t>(static_cast<uint8_t>(p[len >> 1])) << 8)
          |  static_cast<uint64_t>(static_cast<uint8_t>(p[len - 1]));
    }
    return wymix(seed ^ a, SECRET2 ^ b);
}

} 

struct FlatWordMap {
    struct Entry {
        uint32_t offset;   
        uint32_t len;      
        int32_t  cnt;      
    };
    static_assert(sizeof(Entry) == 12, "Entry must be 12 bytes");

    Entry*   table    = nullptr;
    uint32_t mask     = 0;     
    uint32_t used     = 0;
    const char* base  = nullptr; 

    void init(const char* s_data, uint32_t cap) {
        __builtin_assume((cap & (cap - 1)) == 0);
        base  = s_data;
        mask  = cap - 1;
        table = static_cast<Entry*>(
                    ::calloc(cap, sizeof(Entry)));  
    }
    FlatWordMap() noexcept = default;
    ~FlatWordMap() { ::free(table); }

    FlatWordMap(const FlatWordMap&) = delete;
    FlatWordMap& operator=(const FlatWordMap&) = delete;

    [[clang::always_inline]]
    void increment(uint32_t word_offset, uint32_t word_len, uint64_t h) noexcept {
        uint32_t slot = static_cast<uint32_t>(h) & mask;

        while (true) [[likely]] {
            Entry& e = table[slot];

            if (__builtin_expect(e.cnt == 0, 0)) {
                e.offset = word_offset;
                e.len    = word_len;
                e.cnt    = 1;
                ++used;
                return;
            }

            if (e.len == word_len &&
                __builtin_expect(
                    ::memcmp(base + e.offset, base + word_offset, word_len) == 0,
                    1)) {
                ++e.cnt;
                return;
            }

            slot = (slot + 1) & mask;
        }
    }

    [[clang::always_inline]]
    int find(const char* q_data, uint32_t q_len, uint64_t h) const noexcept {
        uint32_t slot = static_cast<uint32_t>(h) & mask;

        while (true) [[likely]] {
            const Entry& e = table[slot];

            if (__builtin_expect(e.cnt == 0, 0))
                return 0;   

            if (e.len == q_len &&
                __builtin_expect(
                    ::memcmp(base + e.offset, q_data, q_len) == 0,
                    1)) {
                return e.cnt;
            }

            slot = (slot + 1) & mask;
        }
    }
};

class Solution {
public:
    [[gnu::hot, gnu::flatten]]
    vector<int> countWordOccurrences(vector<string>& chunks,
                                     vector<string>& queries) {

        uint32_t total_len = 0;
        for (const auto& c : chunks)
            total_len += static_cast<uint32_t>(c.size());

        string s;
        s.reserve(total_len);
        for (const auto& c : chunks) s += c;

        const char* const s_data = s.data();

        uint32_t cap = 16;
        while (cap < total_len)   
            cap <<= 1;

        FlatWordMap wmap;
        wmap.init(s_data, cap);

        const char* __restrict__ curr = s_data;
        const char* const         end  = s_data + total_len;

        while (curr < end) {
            if (__builtin_expect(
                    static_cast<unsigned char>(*curr - 'a') > 25u, 0)) {
                ++curr;
                continue;
            }

            const char* const word_start = curr;

            #pragma clang loop vectorize(enable) unroll(enable)
            while (curr < end &&
                   static_cast<unsigned char>(*curr - 'a') <= 25u) {
                ++curr;
            }

            while (curr < end && *curr == '-') {
                const char* hyp = curr;
                if (hyp + 1 < end &&
                    static_cast<unsigned char>(hyp[1] - 'a') <= 25u) {
                    curr += 2;  
                    #pragma clang loop vectorize(enable)
                    while (curr < end &&
                           static_cast<unsigned char>(*curr - 'a') <= 25u) {
                        ++curr;
                    }
                } else {
                    break;   
                }
            }

            uint32_t off = static_cast<uint32_t>(word_start - s_data);
            uint32_t len = static_cast<uint32_t>(curr - word_start);
            uint64_t h   = wyhash_impl::hash_sv(word_start, len);
            wmap.increment(off, len, h);
        }

        const int Q = static_cast<int>(queries.size());
        vector<int> answer;
        answer.reserve(Q);

        const char* last_data = nullptr;
        uint32_t     last_len  = 0;
        uint64_t     last_hash = 0;
        int          last_ans  = 0;

        for (int qi = 0; qi < Q; ++qi) {
            const string& q    = queries[qi];
            const char*    qd   = q.data();
            uint32_t       qlen = static_cast<uint32_t>(q.size());

            if (__builtin_expect(qlen == last_len && qd[0] == last_data[0], 0)) {
                if (__builtin_expect(
                        qd == last_data ||
                        ::memcmp(qd, last_data, qlen) == 0, 1)) {
                    answer.push_back(last_ans);
                    continue;
                }
            }

            last_data = qd;
            last_len  = qlen;
            last_hash = wyhash_impl::hash_sv(qd, qlen);
            last_ans  = wmap.find(qd, qlen, last_hash);
            answer.push_back(last_ans);
        }

        return answer;
    }
};