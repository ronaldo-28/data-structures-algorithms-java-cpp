/*
This problem is the "online" query version of LeetCode 1697.
link: https://leetcode.com/problems/checking-existence-of-edge-length-limited-paths/description/

To solve this efficiently, we need a data structure that remembers the state of connectivity at any given "weight limit." We can achieve this using a variation of a Persistent Disjoint Set Union (DSU).

The Approach
When we build a Minimum Spanning Tree (MST) using Kruskal's algorithm, we process edges from smallest to largest. If we build a DSU this way, the edge weight used to merge two components represents the exact bottleneck (the maximum weight on the minimax path) between them.

why we cannot use Path Compression in this problem. Path compression flattens the tree (making a node point directly to the ultimate root), which would destroy the step-by-step history of edge weights saved in the weight vector.

For any node x, weight[x] stores exactly one thing: the weight of the edge that forced node x (which was a root at the time) to merge under its new parent.

Think of the parent pointer as a bridge to another island, and weight[x] as the toll required to cross that specific bridge.
*/

class DistanceLimitedPathsExist {
    vector<int> parent;
    vector<int> weight;
    vector<int> size; // Changed from rank to size

    int find(int x) {
        while (x != parent[x]) {
            x = parent[x];
        }
        return x;
    }

    int find(int x, int limit) {
        while (x != parent[x] && weight[x] < limit) {
            x = parent[x];
        }
        return x;
    }

public:
    DistanceLimitedPathsExist(int n, vector<vector<int>>& edgeList) {
        parent.resize(n);
        weight.resize(n, 0);
        size.resize(n, 1); // Initialize every component with size 1
        
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        sort(edgeList.begin(), edgeList.end(), [](const vector<int>& a, const vector<int>& b) {
            return a[2] < b[2];
        });
        
        for (const auto& edge : edgeList) {
            int u = edge[0], v = edge[1], w = edge[2];
            int rootU = find(u);
            int rootV = find(v);
            
            if (rootU != rootV) {
                // Attach the smaller tree to the larger tree
                if (size[rootU] < size[rootV]) {
                    parent[rootU] = rootV;
                    weight[rootU] = w;
                    size[rootV] += size[rootU]; // Update the size of the new root
                } else {
                    parent[rootV] = rootU;
                    weight[rootV] = w;
                    size[rootU] += size[rootV]; // Update the size of the new root
                }
            }
        }
    }
    
    bool query(int p, int q, int limit) {
        return find(p, limit) == find(q, limit);
    }
};
/*
Time (Constructor) O(E log E + E log V)
Sorting the edges takes $O(E \log E)$ where $E$ is the number of edges. Processing each edge takes $O(\log V)$ because Union by Rank guarantees a maximum tree height of $\log V$.

Time (Query) O(log V)
For every query, we walk up the parent pointers. Because we preserved the Union by size structure, the maximum depth we can traverse is $\log V$.
*/

/*
Persistent Disjoint Set Union (DSU)

Every time we merge two trees (by connecting rootA to rootB), we record a "timestamp" on that connection. In code, we usually maintain an extra array (e.g., weight[x] or time[x]) that stores the exact time or edge weight that forced node x to point to its new parent.

Querying the Past (Time Travel)
When you want to know if two nodes were connected at time T, you run a modified find function.

Instead of just walking up the parent pointers blindly, you check the timestamp on the connection before you cross it.

If the timestamp is ≤T, the connection existed back then. You step up to the parent.

If the timestamp is >T, that connection was built after the time you are asking about. It doesn't exist in your timeline yet. You stop and pretend the current node is the root.

If the "time-traveling" find function for both nodes stops at the exact same ancestor, they were connected at time T.

Summary: A Persistent DSU is just a standard DSU that trades the extreme O(1) speed of Path Compression for a slightly slower O(logN) speed, gaining the superpower of historical memory.
*/


/**
 * Your DistanceLimitedPathsExist object will be instantiated and called as such:
 * DistanceLimitedPathsExist* obj = new DistanceLimitedPathsExist(n, edgeList);
 * bool param_1 = obj->query(p,q,limit);
 */