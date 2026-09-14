// Author: Alexander Picon
// GitHub: https://github.com/alexpicon
// LinkedIn: https://www.linkedin.com/in/alexpicon/
// Web: https://chaski.ai/


class Solution {
   public:
    // NOLINTNEXTLINE(readability-identifier-naming)
    static auto maxXor(int n, std::vector<std::vector<int>>& edges,
                       std::vector<int>& values) -> std::int64_t {
        Solver solver(n, edges, values);
        return solver.solve();
    }

   private:
    static constexpr int MAX_BIT = 46;
    static constexpr int SLOTS = 2;

    class Solver {
       public:
        Solver(int n, const std::vector<std::vector<int>>& edges,
               const std::vector<int>& values)
            : values_(values),
              adjacency_(static_cast<std::size_t>(n)),
              parent_(static_cast<std::size_t>(n), -1),
              subtree_sum_(static_cast<std::size_t>(n), 0) {
            trie_.assign(SLOTS, 0);
            for (const auto& edge : edges) {
                adjacency_[static_cast<std::size_t>(edge[0])].push_back(
                    edge[1]);
                adjacency_[static_cast<std::size_t>(edge[1])].push_back(
                    edge[0]);
            }
            root_tree();
        }

        auto solve() -> std::int64_t {
            dfs_sum(0);
            dfs_xor(0);
            return max_xor_;
        }

       private:
        auto root_tree() -> void {
            std::vector<int> stack = {0};
            while (!stack.empty()) {
                const int node = stack.back();
                stack.pop_back();
                for (const int child :
                     adjacency_[static_cast<std::size_t>(node)]) {
                    if (child != parent_[static_cast<std::size_t>(node)] &&
                        parent_[static_cast<std::size_t>(child)] == -1 &&
                        child != 0) {
                        parent_[static_cast<std::size_t>(child)] = node;
                        stack.push_back(child);
                    }
                }
            }
        }

        auto dfs_sum(int node) -> void {
            const auto index = static_cast<std::size_t>(node);
            subtree_sum_[index] = values_[index];
            for (const int child : adjacency_[index]) {
                if (child != parent_[index]) {
                    dfs_sum(child);
                    subtree_sum_[index] +=
                        subtree_sum_[static_cast<std::size_t>(child)];
                }
            }
        }

        auto dfs_xor(int node) -> void {
            const auto index = static_cast<std::size_t>(node);
            max_xor_ = std::max(max_xor_, trie_query(subtree_sum_[index]));
            for (const int child : adjacency_[index]) {
                if (child != parent_[index]) {
                    dfs_xor(child);
                }
            }
            trie_insert(subtree_sum_[index]);
        }

        auto trie_insert(std::int64_t num) -> void {
            int node = 0;
            for (int bit = MAX_BIT; bit >= 0; --bit) {
                const int child = static_cast<int>((num >> bit) & 1);
                const std::size_t slot =
                    (static_cast<std::size_t>(node) * SLOTS) +
                    static_cast<std::size_t>(child);
                if (trie_[slot] == 0) {
                    trie_[slot] = static_cast<int>(trie_.size() / SLOTS);
                    trie_.push_back(0);
                    trie_.push_back(0);
                }
                node = trie_[slot];
            }
        }

        [[nodiscard]] auto trie_query(std::int64_t num) const -> std::int64_t {
            if (trie_.size() <= SLOTS) {
                return 0;
            }
            int node = 0;
            std::int64_t result = 0;
            for (int bit = MAX_BIT; bit >= 0; --bit) {
                const int child = static_cast<int>((num >> bit) & 1);
                const int want = 1 - child;
                const std::size_t base = static_cast<std::size_t>(node) * SLOTS;
                if (trie_[base + static_cast<std::size_t>(want)] != 0) {
                    result |= (std::int64_t{1} << bit);
                    node = trie_[base + static_cast<std::size_t>(want)];
                } else if (trie_[base + static_cast<std::size_t>(child)] != 0) {
                    node = trie_[base + static_cast<std::size_t>(child)];
                } else {
                    return result;
                }
            }
            return result;
        }

        std::vector<int> values_;
        std::vector<std::vector<int>> adjacency_;
        std::vector<int> parent_;
        std::vector<std::int64_t> subtree_sum_;
        std::vector<int> trie_;
        std::int64_t max_xor_ = 0;
    };
};