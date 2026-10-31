class Solution {
    public int[] longestSpecialPath(int[][] edges, int[] nums) {
        int n = nums.length;
        int[] start = new int[n + 1];
        for (int[] e : edges) {
            start[e[0] + 1]++;
            start[e[1] + 1]++;
        }
        for (int i = 0; i < n; i++) {
            start[i + 1] += start[i];
        }
        int[] to = new int[2 * (n - 1)];
        int[] len = new int[2 * (n - 1)];
        int[] pos = Arrays.copyOf(start, n);
        for (int[] e : edges) {
            to[pos[e[0]]] = e[1];
            len[pos[e[0]]++] = e[2];
            to[pos[e[1]]] = e[0];
            len[pos[e[1]]++] = e[2];
        }
        int maxVal = 0;
        for (int v : nums) {
            maxVal = Math.max(maxVal, v);
        }
        int[] lastDepth = new int[maxVal + 1];
        Arrays.fill(lastDepth, -1);
        int[] stackNode = new int[n];
        int[] stackParent = new int[n];
        int[] stackEdge = new int[n];
        int[] stackWindow = new int[n];
        int[] savedLast = new int[n];
        int[] dist = new int[n];
        int bestLen = 0;
        int bestNodes = 1;
        int top = 0;
        stackNode[0] = 0;
        stackParent[0] = -1;
        stackEdge[0] = start[0];
        savedLast[0] = lastDepth[nums[0]];
        stackWindow[0] = 0;
        lastDepth[nums[0]] = 0;
        dist[0] = 0;
        while (top >= 0) {
            int u = stackNode[top];
            if (stackEdge[top] < start[u + 1]) {
                int e = stackEdge[top]++;
                int v = to[e];
                if (v == stackParent[top]) continue;
                int d = top + 1;
                stackNode[d] = v;
                stackParent[d] = u;
                stackEdge[d] = start[v];
                dist[d] = dist[top] + len[e];
                savedLast[d] = lastDepth[nums[v]];
                int w = Math.max(stackWindow[top], savedLast[d] + 1);
                stackWindow[d] = w;
                lastDepth[nums[v]] = d;
                int length = dist[d] - dist[w];
                int nodes = d - w + 1;
                if (length > bestLen || (length == bestLen && nodes < bestNodes)) {
                    bestLen = length;
                    bestNodes = nodes;
                }
                top = d;
            } else {
                lastDepth[nums[u]] = savedLast[top];
                top--;
            }
        }
        return new int[] {bestLen, bestNodes};
    }
}