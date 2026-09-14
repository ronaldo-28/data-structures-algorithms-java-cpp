#include <vector>
#include <algorithm>
#include <array>

using namespace std;

class Solution {
public:
    int numberOfGoodPaths(vector<int>& vals, vector<vector<int>>& edges) {
        int n = vals.size();
        
        // Initialize DSU arrays
        vector<int> parent(n);
        vector<int> count(n, 1);
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        // OPTIMIZATION 1: Pre-compute max values and use std::array for cache locality.
        // Array structure: {max_val, node_u, node_v}
        vector<array<int, 3>> enriched_edges(edges.size());
        for (int i = 0; i < edges.size(); ++i) {
            int u = edges[i][0];
            int v = edges[i][1];
            enriched_edges[i] = {max(vals[u], vals[v]), u, v};
        }
        
        // Sorts by the first element (max_val) automatically
        sort(enriched_edges.begin(), enriched_edges.end());
        
        int ans = n; // Every node forms a good path with itself
        
        for (const auto& edge : enriched_edges) {
            int u = edge[1];
            int v = edge[2];
            
            // OPTIMIZATION 2: Iterative find with path halving
            while (u != parent[u]) {
                parent[u] = parent[parent[u]];
                u = parent[u];
            }
            while (v != parent[v]) {
                parent[v] = parent[parent[v]];
                v = parent[v];
            }
            
            if (u != v) {
                // If both components have the same maximum value
                if (vals[u] == vals[v]) {
                    ans += count[u] * count[v];
                    parent[u] = v;
                    count[v] += count[u];
                } 
                // If u's component has a strictly greater maximum value
                else if (vals[u] > vals[v]) {
                    parent[v] = u;
                } 
                // If v's component has a strictly greater maximum value
                else {
                    parent[u] = v;
                }
            }
        }
        
        return ans;
    }
};