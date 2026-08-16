class Solution {
    long long ans = 0;

    struct Node {
        vector<int> reverse_edges;
        long long sum_to_cycle = 0;
        int dist_to_cycle = 0;
        pair<int, int> cycle_attach;
        bool visited = false;
    };

    vector<Node> state;

    vector<vector<long long>> cycles;

    vector<long long> path;

    span<int> nxt;

    int K;

    void dfs(int i) {
        path.push_back(path.back() + i);

        if (path.size() >= K + 2) {
            ans = max(ans, path.back() - path[path.size() - K - 2]);
        }

        for (int j : state[i].reverse_edges) {
            dfs(j);
        }

        path.pop_back();
    }

    long long cycle_sum(int i, int j, long long jumps) {
        auto& c = cycles[i];

        int cycle_len = c.size();

        long long result = jumps / cycle_len * c.back();

        jumps %= cycle_len;

        if (j + jumps >= cycle_len) {
            jumps -= cycle_len - j - 1;
            result += c.back() - c[j];
            result += c[jumps - 1];
        } else {
            result += c[j + jumps] - c[j];
        }

        return result;
    }

    void initialize_cycle(int cycle_start) {
        int i = cycle_start;

        cycles.emplace_back();

        do {
            cycles.back().push_back(i);

            state[i].visited = true;
            state[i].sum_to_cycle = i;
            state[i].cycle_attach = {
                cycles.size() - 1,
                cycles.back().size() - 1
            };

            i = nxt[i];
        } while (i != cycle_start);

        partial_sum(
            cycles.back().begin(),
            cycles.back().end(),
            cycles.back().begin()
        );
    }
public:
    long long getMaxFunctionValue(vector<int>& receiver, long long k) {
        const int N = receiver.size();

        state.resize(N);

        nxt = receiver;

        K = k;

        for (int i = 0; i < N; i++) {
            if (state[i].visited) {
                continue;
            }

            int slow = i, fast = i;

            do {
                fast = nxt[nxt[fast]];
                slow = nxt[slow];
            } while (fast != slow && !state[slow].visited);

            if (!state[slow].visited) {
                // We've found a new cycle/weakly connected component.
                slow = i;

                while (fast != slow) {
                    slow = nxt[slow];
                    fast = nxt[fast];
                }

                initialize_cycle(slow);
            }

            int len = state[slow].dist_to_cycle;

            long long s = state[slow].sum_to_cycle;

            fast = i;

            while (fast != slow) {
                len++;
                s += fast;
                fast = nxt[fast];
            }

            fast = i;

            while (fast != slow) {
                assert(!state[fast].visited);
                state[fast].sum_to_cycle = s;
                state[fast].dist_to_cycle = len;
                state[fast].cycle_attach = state[slow].cycle_attach;
                state[fast].visited = true;
                state[nxt[fast]].reverse_edges.push_back(fast);
                s -= fast;
                len--;
                fast = nxt[fast];
            }
        }

        vector<int> roots;

        for (auto [i, s] : views::enumerate(state)) {
            if (s.dist_to_cycle <= k) {
                // Nodes which reach a cycle in k steps or less.
                ans = max(
                    ans,
                    s.sum_to_cycle + cycle_sum(
                        s.cycle_attach.first,
                        s.cycle_attach.second,
                        k - s.dist_to_cycle
                    )
                );
            }

            if (s.dist_to_cycle == 0 && !s.reverse_edges.empty()) {
                roots.push_back(i);
            }
        }

        path.push_back(0);

        // This accounts for nodes which do not reach a cycle in k steps or less.
        for (int r : roots) {
            dfs(r);
        }

        return ans;
    }
};