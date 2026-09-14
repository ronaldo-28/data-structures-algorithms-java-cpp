class Solution {
    record Cell(int i, int w, int k) {}

    int k;
    List<Node>[] g;
    public int maximumCost(int n, int[][] edges, int k) {
        if(k >= n) return -1;
        this.k = k;

        // create adjacency list graph
        g = createGraph(n, edges, new List[n]);

        int res = -1;
        for (int i = 0; i < n; i++)
            res = Math.max(res, dijkstra(i, new boolean[n], new PriorityQueue<>((a, b) -> b.w - a.w)));

        return res;
    }

    private int dijkstra(int src, boolean[] visisted, Queue<Cell> q) {
        q.offer(new Cell(src, 0, k));
        visisted[src] = true;

        while (!q.isEmpty()) {
            var cur = q.poll();
            if (cur.k == 0)
                return cur.w;
            visisted[cur.i] = true;

            for (var nei : g[cur.i]) {
                if (visisted[nei.i]) continue;
                q.offer(new Cell(nei.i, nei.w + cur.w, cur.k-1));
            }
        }

        return -1;
    }

    record Node(int i, int w) {}
    private List<Node>[] createGraph(int n, int[][] edges, List<Node>[] g) {
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for (var e : edges) {
            g[e[0]].add(new Node(e[1], e[2]));
            g[e[1]].add(new Node(e[0], e[2]));
        }
        return g;
    }
}