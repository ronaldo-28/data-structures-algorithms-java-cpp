class Solution {
public:
    using u16 = uint16_t;
    using u32 = uint32_t;
    using u64 = uint64_t;
    inline static u32 q[100'001];
    inline static u32 g[300'007];
    u32 countIslands(const vector<vector<int>>& ggg, u32 k) noexcept {
        auto& gg = reinterpret_cast<const vector<vector<u32>>&>(ggg);
        const u32 h = gg.size(), h1 = h - 1, h2 = h + 2;
        const u32 w = gg[0].size(), w1 = w - 1, w2 = w + 2;
        u32* out = fill_n(g, w2, 0);
        for (auto& row : gg) {
            *out = 0;
            ++out;
            out = ranges::copy(row, out).out;
            *out = 0;
            ++out;
        }
        out = fill_n(out, w2, 0);
        u32 qb{}, qe{}, ans{};
        u64 sum{};
        auto push = [&] [[gnu::always_inline]] (u32 i) {
            if (!!g[i]) {
                sum += exchange(g[i], 0);
                q[qe++] = i;
            }
        };

        auto bfs = [&] [[gnu::always_inline]] (u32 si) {
            sum = 0;
            qb = qe = 0;
            push(si);
            while (qb != qe) {
                u32 i = q[qb++];
                push(i - 1);
                push(i + 1);
                push(i - w2);
                push(i + w2);
            }
            ans += !(sum % k);
        };

        u32 i = w2 + 1;
        for (u32 y = 0; y != h; ++y) {
            for (u32 x = 0; x != w; ++x, ++i) {
                if (g[i]) {
                    bfs(i);
                }
            }
            i += 2;
        }
        return ans;
    }
};
auto init = []() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(nullptr);
    std::cout.tie(nullptr);
    return 'c';
}();