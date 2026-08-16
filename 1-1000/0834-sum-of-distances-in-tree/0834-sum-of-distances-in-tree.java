class Solution {
        public Edge[] edges;
    public int cnt;
    public int[] fir;
    public long[] dis;
    public boolean[] vis;
    public int ans;

    static class Edge {

        int u, v, w, next;
        boolean cut;
        int used;
        int num;
    }
        public void graphInit(int nodeSize, int edgeSize) {
        //分配内存，edges，fir，dis
        cnt = 0;
        edges = new Edge[edgeSize + 10];
        fir = new int[nodeSize + 10];
        dis = new long[nodeSize + 10];
        vis = new boolean[nodeSize + 10];
        Arrays.fill(fir, -1);
    }

    //构建邻接表，u代表起点，v代表终点，w代表之间路径
    void addEdge(int u, int v, int w) {
        edges[cnt] = new Edge();
        edges[cnt].u = u;
        edges[cnt].v = v;
        edges[cnt].w = w;
        edges[cnt].next = fir[u];
        edges[cnt].used = 0;
        fir[u] = cnt++;
    }
    int[] childCnt;
    int[] childSum;
    public void dfsSetChild(int now, int fa) {
        childCnt[now] ++;
        for (int i = fir[now]; i != -1; i = edges[i].next) {
            int v = edges[i].v;
            if (v != fa) {
                dfsSetChild(v, now);
                childCnt[now] += childCnt[v];
                childSum[now] += childCnt[v] + childSum[v];
            }
        }
    }
    public void dfsAns(int now, int fa, int n, int[] ans) {
        if (fa != -1) {
            ans[now] = childSum[now] + ans[fa] - (childSum[now] + childCnt[now]) + (n - childCnt[now]);
        }
        for (int i = fir[now]; i != -1; i = edges[i].next) {
            int v = edges[i].v;
            if (v != fa) {
                dfsAns(v, now, n, ans);
            }
        }
    }
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        graphInit(n, n * 2);
        for (int[] e : edges) {
            addEdge(e[0], e[1], 1);
            addEdge(e[1], e[0], 1);
        }
        childCnt = new int[n];
        childSum = new int[n];
        dfsSetChild(0, -1);
        ans[0] = childSum[0];
        dfsAns(0, -1, n, ans);
        return ans;
    }

}