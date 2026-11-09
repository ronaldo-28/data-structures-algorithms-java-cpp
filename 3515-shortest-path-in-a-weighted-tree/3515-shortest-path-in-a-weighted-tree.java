class Solution {
    static class Edge {
        int to;
        int weight;
        Edge next;

        Edge(int to, int weight, Edge next) {
            this.to = to;
            this.weight = weight;
            this.next = next;
        }
    }

    static class Fenwick {
        long[] tree;

        Fenwick(int n) {
            tree = new long[n + 2];
        }

        void add(int index, long value) {
            while (index < tree.length) {
                tree[index] += value;
                index += index & -index;
            }
        }

        long sum(int index) {
            long result = 0;

            while (index > 0) {
                result += tree[index];
                index -= index & -index;
            }

            return result;
        }

        // Add value to range [left, right]
        void rangeAdd(int left, int right, long value) {
            add(left, value);
            add(right + 1, -value);
        }
    }

    public int[] treeQueries(int n, int[][] edges, int[][] queries) {
        Edge[] graph = new Edge[n + 1];

        // Store the original edge information.
        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int w = e[2];

            graph[u] = new Edge(v, w, graph[u]);
            graph[v] = new Edge(u, w, graph[v]);
        }

        int[] parent = new int[n + 1];
        int[] parentWeight = new int[n + 1];
        int[] tin = new int[n + 1];
        int[] tout = new int[n + 1];
        long[] dist = new long[n + 1];

        // Iterative DFS to avoid stack overflow.
        int[] stack = new int[2 * n];
        int[] state = new int[2 * n];
        int top = 0;
        int timer = 0;

        stack[top] = 1;
        state[top] = 0;
        top++;

        parent[1] = 0;

        while (top > 0) {
            top--;

            int u = stack[top];
            int type = state[top];

            if (type == 0) {
                tin[u] = ++timer;

                // Exit marker
                stack[top] = u;
                state[top] = 1;
                top++;

                for (Edge e = graph[u]; e != null; e = e.next) {
                    int v = e.to;

                    if (v == parent[u]) {
                        continue;
                    }

                    parent[v] = u;
                    parentWeight[v] = e.weight;
                    dist[v] = dist[u] + e.weight;

                    stack[top] = v;
                    state[top] = 0;
                    top++;
                }
            } else {
                tout[u] = timer;
            }
        }

        /*
         * For an edge parent -> child:
         * changing its weight by delta changes the distance
         * of every node in child's subtree by delta.
         *
         * Euler tour turns every subtree into a contiguous range.
         */
        Fenwick bit = new Fenwick(n + 1);

        int answerCount = 0;

        for (int[] query : queries) {
            if (query[0] == 2) {
                answerCount++;
            }
        }

        int[] answer = new int[answerCount];
        int answerIndex = 0;

        for (int[] query : queries) {
            if (query[0] == 1) {
                int u = query[1];
                int v = query[2];
                int newWeight = query[3];

                int child;

                if (parent[u] == v) {
                    child = u;
                } else {
                    child = v;
                }

                int oldWeight = parentWeight[child];
                long delta = (long) newWeight - oldWeight;

                parentWeight[child] = newWeight;

                bit.rangeAdd(
                    tin[child],
                    tout[child],
                    delta
                );

            } else {
                int x = query[1];

                long currentDistance =
                    dist[x] + bit.sum(tin[x]);

                answer[answerIndex++] = (int) currentDistance;
            }
        }

        return answer;
    }
}