class Solution {
public:
    int minCost(vector<vector<int>>& grid) {
        const int m = static_cast<int>(grid.size());
        const int n = static_cast<int>(grid[0].size());
        constexpr int K = 1024;  // values are 0..1023, so XORs stay in range

        // prev = reachable XOR sets for row i-1, cur = for row i.
        std::vector<std::bitset<K>> prev(n), cur(n);

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                cur[j].reset();
                const int cell = grid[i][j];

                if (i == 0 && j == 0) {
                    cur[0].set(cell);     // the only path so far is the start
                    continue;
                }

                // Union of the sets arriving from above and from the left.
                std::bitset<K> incoming;
                if (i > 0) incoming |= prev[j];      // came down
                if (j > 0) incoming |= cur[j - 1];   // came right

                // XOR the current cell into every incoming value.
                for (int v = 0; v < K; ++v) {
                    if (incoming.test(static_cast<size_t>(v))) {
                        cur[j].set(static_cast<size_t>(v ^ cell));
                    }
                }
            }
            prev.swap(cur);   // prev now holds row i
        }

        // After the final swap, prev holds the last row.
        const std::bitset<K>& dest = prev[n - 1];
        for (int v = 0; v < K; ++v) {
            if (dest.test(static_cast<size_t>(v))) return v;  // smallest value
        }
        return 0;  // unreachable: the destination always has >= 1 path
    }
};