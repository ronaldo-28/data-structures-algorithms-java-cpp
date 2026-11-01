#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    int minActivationCost(vector<vector<int>>& edges, vector<int>& values) {
        int n = values.size();
        vector<int> P(n);
        
        // Calculate the popcount parity for each node
        for (int i = 0; i < n; i++) {
            P[i] = __builtin_popcount(values[i]) % 2;
        }
        
        long long min_cost = -1; // Acts as our global minimum
        
        // Track the two minimum edge costs to P=1 neighbors for each node
        vector<long long> min1(n, -1);
        vector<long long> min2(n, -1);
        
        // Helper to update the two cheapest edges for a given node
        auto update = [&](int node, long long cost) {
            if (min1[node] == -1 || cost < min1[node]) {
                min2[node] = min1[node];
                min1[node] = cost;
            } else if (min2[node] == -1 || cost < min2[node]) {
                min2[node] = cost;
            }
        };
        
        // Process all edges
        for (const auto& edge : edges) {
            int u = edge[0];
            int v = edge[1];
            long long c = edge[2];
            
            if (P[u] == P[v]) {
                // Valid path of length 1
                if (min_cost == -1 || c < min_cost) {
                    min_cost = c;
                }
            } else {
                // Graph is locally bipartite regarding parity
                // Track costs for potential 1-0-1 paths
                if (P[u] == 1 && P[v] == 0) {
                    update(v, c);
                } else if (P[v] == 1 && P[u] == 0) {
                    update(u, c);
                }
            }
        }
        
        // Check all potential 1-0-1 length 2 paths
        for (int v = 0; v < n; v++) {
            if (P[v] == 0 && min1[v] != -1 && min2[v] != -1) {
                long long current_cost = min1[v] + min2[v];
                if (min_cost == -1 || current_cost < min_cost) {
                    min_cost = current_cost;
                }
            }
        }
        
        return min_cost;
    }
};