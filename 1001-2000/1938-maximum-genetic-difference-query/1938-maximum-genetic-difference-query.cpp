
class Trie {

    array<vector<int>, 18> seen;

    public:

    Trie() {
        int sz = 1<<18;
        for (int i=0; i<18; i++) {
            seen[i].assign(sz, 0);
            sz >>= 1;
        }
    }

    void add_word(int val, int diff) {
        for (int i=17; i>=0; i--) {
            seen[i][val>>i]+=diff;
        }
    }

    int query_best(int val) {
        int tgt = 0;
        for (int i=17; i>=0; i--) {
            tgt = (tgt<<1)+1; 
            if (!seen[i][tgt^(val>>i)]) tgt--;
        }
        return tgt;
    }

};


class Solution {
public:
    vector<int> maxGeneticDifference(vector<int>& parents, vector<vector<int>>& queries) {
        int n = parents.size(), root = -1;
        vector<vector<int>> adj(n);
        for (int i{}; i<n; i++) {
            if (parents[i] == -1) root = i;
            else adj[parents[i]].push_back(i);
        }
        vector<vector<pair<int, int>>> query_vals(n);
        for (int i{}; i<queries.size(); i++) query_vals[queries[i][0]].push_back({queries[i][1], i});
        vector<int> res(queries.size());
        auto dfs = [&](auto &&self, int node, Trie &trie) -> void {
            trie.add_word(node, 1);
            for (auto &q : query_vals[node]) {
                res[q.second] = trie.query_best(q.first);
            }
            for (auto &i : adj[node]) {
                self(self, i, trie);
            }
            trie.add_word(node, -1);
        };
        Trie trie;
        dfs(dfs, root, trie);
        return res;
    } 
};