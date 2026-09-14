#include <bits/stdc++.h>
using namespace std;

class Solution {
private:
    bool isAncestor(
        int a,
        int b,
        const vector<int>& tin,
        const vector<int>& tout
    ) {
        return tin[a] <= tin[b] &&
               tout[b] <= tout[a];
    }

    int lca(
        int a,
        int b,
        const vector<int>& tin,
        const vector<int>& tout,
        const vector<int>& parent,
        const vector<vector<int>>& up
    ) {
        if (isAncestor(a, b, tin, tout)) {
            return a;
        }

        if (isAncestor(b, a, tin, tout)) {
            return b;
        }

        for (int level = (int)up.size() - 1;
             level >= 0;
             --level) {

            int ancestor = up[level][a];

            if (!isAncestor(ancestor, b, tin, tout)) {
                a = ancestor;
            }
        }

        return parent[a];
    }

public:
    long long interactionCosts(
        int n,
        vector<vector<int>>& edges,
        vector<int>& group
    ) {
        if (n <= 1) {
            return 0LL;
        }

        int edgeCount = (n - 1) * 2;

        vector<int> head(n, -1);
        vector<int> to(edgeCount);
        vector<int> next(edgeCount);

        int edgeIndex = 0;

        for (const auto& edge : edges) {
            int u = edge[0];
            int v = edge[1];

            to[edgeIndex] = v;
            next[edgeIndex] = head[u];
            head[u] = edgeIndex++;

            to[edgeIndex] = u;
            next[edgeIndex] = head[v];
            head[v] = edgeIndex++;
        }

        vector<int> parent(n);
        vector<int> depth(n);
        vector<int> tin(n);
        vector<int> tout(n);
        vector<int> nodeAtTin(n);

        vector<int> iterator(n);
        vector<int> stack(n);

        parent[0] = 0;

        int timer = 0;
        int top = 0;

        stack[0] = 0;

        tin[0] = timer;
        nodeAtTin[timer++] = 0;

        iterator[0] = head[0];

        /*
         * Iterative DFS.
         */
        while (top >= 0) {
            int u = stack[top];

            int e = iterator[u];

            if (e == -1) {
                tout[u] = timer - 1;
                --top;
                continue;
            }

            iterator[u] = next[e];

            int v = to[e];

            if (v == parent[u]) {
                continue;
            }

            parent[v] = u;
            depth[v] = depth[u] + 1;

            tin[v] = timer;
            nodeAtTin[timer++] = v;

            iterator[v] = head[v];

            stack[++top] = v;
        }

        /*
         * Binary lifting.
         */
        int LOG = 1;

        while ((1 << LOG) <= n) {
            ++LOG;
        }

        vector<vector<int>> up(
            LOG,
            vector<int>(n)
        );

        up[0] = parent;

        for (int level = 1; level < LOG; ++level) {
            for (int node = 0; node < n; ++node) {
                up[level][node] =
                    up[level - 1][
                        up[level - 1][node]
                    ];
            }
        }

        /*
         * Sort all nodes once by:
         *
         *   1. group
         *   2. tin
         *
         * Equivalent to the Java packed long.
         */
        vector<pair<int, int>> order(n);

        for (int node = 0; node < n; ++node) {
            order[node] = {
                group[node],
                tin[node]
            };
        }

        sort(order.begin(), order.end());

        vector<int> virtualStack(n);
        vector<int> subtreeCount(n);

        long long answer = 0;

        int start = 0;

        while (start < n) {
            int label = order[start].first;

            int end = start + 1;

            while (end < n &&
                   order[end].first == label) {
                ++end;
            }

            int groupSize = end - start;

            if (groupSize >= 2) {

                int stackTop = 0;

                /*
                 * First node of this group.
                 */
                virtualStack[0] =
                    nodeAtTin[order[start].second];

                subtreeCount[0] = 1;

                for (int i = start + 1; i < end; ++i) {

                    int node =
                        nodeAtTin[order[i].second];

                    int currentLca = lca(
                        virtualStack[stackTop],
                        node,
                        tin,
                        tout,
                        parent,
                        up
                    );

                    /*
                     * Pop virtual-tree nodes that are
                     * below the current LCA.
                     */
                    while (
                        stackTop > 0 &&
                        depth[
                            virtualStack[stackTop - 1]
                        ] >= depth[currentLca]
                    ) {
                        int child =
                            virtualStack[stackTop];

                        int parentNode =
                            virtualStack[stackTop - 1];

                        int count =
                            subtreeCount[stackTop];

                        answer +=
                            (long long)count
                            * (groupSize - count)
                            * (
                                depth[child]
                                - depth[parentNode]
                            );

                        subtreeCount[stackTop - 1] += count;

                        --stackTop;
                    }

                    /*
                     * If the current top isn't already
                     * the LCA, replace it with the LCA.
                     */
                    if (virtualStack[stackTop] != currentLca) {

                        int child =
                            virtualStack[stackTop];

                        int count =
                            subtreeCount[stackTop];

                        answer +=
                            (long long)count
                            * (groupSize - count)
                            * (
                                depth[child]
                                - depth[currentLca]
                            );

                        virtualStack[stackTop] =
                            currentLca;

                        subtreeCount[stackTop] =
                            count;
                    }

                    /*
                     * Push the current node.
                     */
                    virtualStack[++stackTop] = node;
                    subtreeCount[stackTop] = 1;
                }

                /*
                 * Finish remaining virtual-tree edges.
                 */
                while (stackTop > 0) {

                    int child =
                        virtualStack[stackTop];

                    int parentNode =
                        virtualStack[stackTop - 1];

                    int count =
                        subtreeCount[stackTop];

                    answer +=
                        (long long)count
                        * (groupSize - count)
                        * (
                            depth[child]
                            - depth[parentNode]
                        );

                    subtreeCount[stackTop - 1] += count;

                    --stackTop;
                }
            }

            start = end;
        }

        return answer;
    }
};
