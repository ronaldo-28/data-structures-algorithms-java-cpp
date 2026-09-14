/*
最小化旅行的价格总和(倍增方法求lca).
有n个节点形成一棵树, 每个节点上有点权, 再给定很多路径;
每条路径有开始点和结束点, 路径代价就是从开始点到结束点的点权和;
所有路径的代价总和就是旅行的价格总和;
你可以选择把某些点的点权减少一半, 来降低旅行的价格总和;
但是要求选择的点不能相邻;
返回旅行的价格总和最少能是多少.
左程云 算法讲解122【扩展】树上问题专题5-树上差分.
*/
/*
n = n, m = ts.length, height = the height of the input tree, if the tree is skewed, height == n:
    TC = O((n + m) * log2(n)).
    AS = O(n*log2(n)):
        Heap = O(n*log2(n)).
        Stack = O(height).
*/
class Solution {
    private static int MAXN = 51;

    private static int[] price = new int[MAXN];

    // 题目给定点的编号从0号点开始, 代码中调整成从1号点开始.
    public int minimumTotalPrice(int n, int[][] es, int[] ps, int[][] ts) {
        build(n);
        for (int i = 0, j = 1; i < n; i++, j++) {
            price[j] = ps[i];
        }
        for (int[] edge : es) {
            addEdge(edge[0] + 1, edge[1] + 1);
            addEdge(edge[1] + 1, edge[0] + 1);
        }
        dfs1(1, 0);
        int u, v, lca, lcafather;
        for (int[] trip : ts) {
            u = trip[0] + 1;
            v = trip[1] + 1;
            lca = lca(u, v);
            lcafather = stjump[lca][0];
            num[u]++;
            num[v]++;
            num[lca]--;
            num[lcafather]--;
        }
        dfs2(1, 0);
        dp(1, 0);
        return Math.min(no, yes);
    }

    private static int LIMIT = 6;

    private static int power;

    private int log2(int n) {
        int ans = 0;
        while ((1 << ans) <= (n >> 1)) {
            ans++;
        }
        return ans;
    }

    private static int[] num = new int[MAXN];

    private static int[] head = new int[MAXN];

    private static int[] next = new int[MAXN << 1];

    private static int[] to = new int[MAXN << 1];

    private static int cnt;

    private static int[] deep = new int[MAXN];

    private static int[][] stjump = new int[MAXN][LIMIT];

    private void build(int n) {
        power = log2(n);
        Arrays.fill(num, 1, n + 1, 0);
        cnt = 1;
        Arrays.fill(head, 1, n + 1, 0);
    }

    private void addEdge(int u, int v) {
        next[cnt] = head[u];
        to[cnt] = v;
        head[u] = cnt++;
    }

    private void dfs1(int u, int f) {
        deep[u] = deep[f] + 1;
        stjump[u][0] = f;
        for (int p = 1; p <= power; p++) {
            stjump[u][p] = stjump[stjump[u][p - 1]][p - 1];
        }
        for (int e = head[u]; e != 0; e = next[e]) {
            if (to[e] != f) {
                dfs1(to[e], u);
            }
        }
    }

    private int lca(int a, int b) {
        if (deep[a] < deep[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        for (int p = power; p >= 0; p--) {
            if (deep[stjump[a][p]] >= deep[b]) {
                a = stjump[a][p];
            }
        }
        if (a == b) {
            return a;
        }
        for (int p = power; p >= 0; p--) {
            if (stjump[a][p] != stjump[b][p]) {
                a = stjump[a][p];
                b = stjump[b][p];
            }
        }
        return stjump[a][0];
    }

    private void dfs2(int u, int f) {
        for (int e = head[u], v; e != 0; e = next[e]) {
            v = to[e];
            if (v != f) {
                dfs2(v, u);
            }
        }
        for (int e = head[u], v; e != 0; e = next[e]) {
            v = to[e];
            if (v != f) {
                num[u] += num[v];
            }
        }
    }

    private int no, yes;

    private void dp(int u, int f) {
        int n = price[u] * num[u];
        int y = (price[u] / 2) * num[u];
        for (int e = head[u], v; e != 0; e = next[e]) {
            v = to[e];
            if (v != f) {
                dp(v, u);
                n += Math.min(no, yes);
                y += no;
            }
        }
        no = n;
        yes = y;
    }
}

// /*
// 最小化旅行的价格总和(tarjan方法求lca).
// 有n个节点形成一棵树, 每个节点上有点权, 再给定很多路径;
// 每条路径有开始点和结束点, 路径代价就是从开始点到结束点的点权和;
// 所有路径的代价总和就是旅行的价格总和;
// 你可以选择把某些点的点权减少一半, 来降低旅行的价格总和;
// 但是要求选择的点不能相邻;
// 返回旅行的价格总和最少能是多少.
// 左程云 算法讲解122【扩展】树上问题专题5-树上差分.
// */
// /*
// n = n, m = ts.length, height = the height of the input tree, if the tree is skewed, height == n:
//     TC = O(n + m).
//     AS = O(n + m):
//         Heap = O(n + m).
//         Stack = O(n).
// */
// class Solution {
//     // 题目给定点的编号从0号点开始, 代码中调整成从1号点开始.
//     public int minimumTotalPrice(int n, int[][] es, int[] ps, int[][] ts) {
//         build(n);
//         for (int i = 0, j = 1; i < n; i++, j++) {
//             price[j] = ps[i];
//         }
//         for (int[] edge : es) {
//             addEdge(edge[0] + 1, edge[1] + 1);
//             addEdge(edge[1] + 1, edge[0] + 1);
//         }
//         int m = ts.length;
//         for (int i = 0, j = 1; i < m; i++, j++) {
//             addQuery(ts[i][0] + 1, ts[i][1] + 1, j);
//             addQuery(ts[i][1] + 1, ts[i][0] + 1, j);
//         }
//         tarjan(1, 0);
//         for (int i = 0, j = 1, u, v, lca, lcafather; i < m; i++, j++) {
//             u = ts[i][0] + 1;
//             v = ts[i][1] + 1;
//             lca = ans[j];
//             lcafather = father[lca];
//             num[u]++;
//             num[v]++;
//             num[lca]--;
//             num[lcafather]--;
//         }
//         dfs(1, 0);
//         dp(1, 0);
//         return Math.min(no, yes);
//     }

//     private static int MAXN = 51;

//     private static int MAXM = 101;

//     private static int[] price = new int[MAXN];

//     private static int[] num = new int[MAXN];

//     private static int[] headEdge = new int[MAXN];

//     private static int[] edgeNext = new int[MAXN << 1];

//     private static int[] edgeTo = new int[MAXN << 1];

//     private static int tcnt;

//     private static int[] headQuery = new int[MAXN];

//     private static int[] queryNext = new int[MAXM << 1];

//     private static int[] queryTo = new int[MAXM << 1];

//     private static int[] queryIndex = new int[MAXM << 1];

//     private static int qcnt;

//     private static boolean[] visited = new boolean[MAXN];

//     private static int[] unionfind = new int[MAXN];

//     private static int[] father = new int[MAXN];

//     private static int[] ans = new int[MAXM];

//     private void build(int n) {
//         Arrays.fill(num, 1, n + 1, 0);
//         tcnt = qcnt = 1;
//         Arrays.fill(headEdge, 1, n + 1, 0);
//         Arrays.fill(headQuery, 1, n + 1, 0);
//         Arrays.fill(visited, 1, n + 1, false);
//         for (int i = 1; i <= n; i++) {
//             unionfind[i] = i;
//         }
//     }

//     private void addEdge(int u, int v) {
//         edgeNext[tcnt] = headEdge[u];
//         edgeTo[tcnt] = v;
//         headEdge[u] = tcnt++;
//     }

//     private void addQuery(int u, int v, int i) {
//         queryNext[qcnt] = headQuery[u];
//         queryTo[qcnt] = v;
//         queryIndex[qcnt] = i;
//         headQuery[u] = qcnt++;
//     }

//     private int find(int i) {
//         if (i != unionfind[i]) {
//             unionfind[i] = find(unionfind[i]);
//         }
//         return unionfind[i];
//     }

//     private void tarjan(int u, int f) {
//         visited[u] = true;
//         for (int e = headEdge[u], v; e != 0; e = edgeNext[e]) {
//             v = edgeTo[e];
//             if (v != f) {
//                 tarjan(v, u);
//             }
//         }
//         for (int e = headQuery[u], v; e != 0; e = queryNext[e]) {
//             v = queryTo[e];
//             if (visited[v]) {
//                 ans[queryIndex[e]] = find(v);
//             }
//         }
//         unionfind[u] = f;
//         father[u] = f;
//     }

//     private void dfs(int u, int f) {
//         for (int e = headEdge[u], v; e != 0; e = edgeNext[e]) {
//             v = edgeTo[e];
//             if (v != f) {
//                 dfs(v, u);
//             }
//         }
//         for (int e = headEdge[u], v; e != 0; e = edgeNext[e]) {
//             v = edgeTo[e];
//             if (v != f) {
//                 num[u] += num[v];
//             }
//         }
//     }

//     private static int no, yes;

//     private void dp(int u, int f) {
//         int n = price[u] * num[u];
//         int y = (price[u] / 2) * num[u];
//         for (int e = headEdge[u], v; e != 0; e = edgeNext[e]) {
//             v = edgeTo[e];
//             if (v != f) {
//                 dp(v, u);
//                 n += Math.min(no, yes);
//                 y += no;
//             }
//         }
//         no = n;
//         yes = y;
//     }
// }