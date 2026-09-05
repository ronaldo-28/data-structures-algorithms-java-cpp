class Solution {
    using Pair = std::pair<int, int>;
    std::vector<std::vector<Pair>> m_graph;

    int dijkstra(int start, int stop, int hops, int N) {
        std::vector<int> dp(N * (hops + 1), INT_MAX); 
        dp[start * (hops + 1) + hops] = 0;

        using Node = std::tuple<int, int, int>;

        auto comparator = [](const Node& lhs, const Node& rhs)
        {
            const auto [lhs_cost, lhs_node, lhs_hops] = lhs;
            const auto [rhs_cost, rhs_node, rhs_hops] = rhs;

            return lhs_cost > rhs_cost
                || (lhs_cost == rhs_cost && lhs_hops < rhs_hops);
        };

        std::priority_queue<Node, std::vector<Node>, decltype(comparator)> queue(comparator);
        queue.push({0, start, hops});

        while (!queue.empty()) {
            const auto [cost, node, hops_left] = queue.top();
            queue.pop();

            if (node == stop)
                return cost;

            for (const auto& [next_node, next_cost] : m_graph[node]) {
                const auto new_cost = cost + next_cost;

                if (new_cost < dp[next_node * (hops + 1) + hops_left]) {
                    dp[next_node * (hops + 1) + hops_left] = new_cost;
                    queue.push({new_cost, next_node, hops_left});
                }

                if (hops_left > 0 && cost < dp[next_node * (hops + 1) + hops_left - 1]) {
                    dp[next_node * (hops + 1) + hops_left - 1] = cost;
                    queue.push({cost, next_node, hops_left - 1});
                }
            }
        }

        return 0;
    }

public:
    int shortestPathWithHops(int n, vector<vector<int>>& edges, int s, int d, int k) {
        m_graph = std::vector<std::vector<Pair>>(n);
        for (const auto& edge : edges) {
            m_graph[edge[0]].push_back({edge[1], edge[2]});
            m_graph[edge[1]].push_back({edge[0], edge[2]});
        }

        return dijkstra(s, d, k, n);
    }
};