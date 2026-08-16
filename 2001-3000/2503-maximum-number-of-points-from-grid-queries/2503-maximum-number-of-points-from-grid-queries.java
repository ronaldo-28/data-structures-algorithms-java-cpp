class Solution {
    private static final int[] value = new int[100001], count = new int[100001];
    private final PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
    private boolean[][] seen;
    private int[][] grid;
    private int n, m, index = 0;
    public int[] maxPoints(int[][] grid, int[] queries) {
        this.n = grid.length;
        this.m = grid[0].length;
        this.seen = new boolean[n][m];
        this.grid = grid;

        value[0] = grid[0][0];
        count[0] = 0;
        pq.add(new int[] {0, 0, grid[0][0]});
        seen[0][0] = true;

        while(!pq.isEmpty()) {
            int[] current = pq.poll();
            int x = current[0], y = current[1], val = current[2];

            if(value[index] != val) {
                value[++index] = val;
                count[index] = count[index - 1];
            }
            count[index] += dfs(x, y, val);
        }
        //System.out.println(value[index] + ": " + count[index]);
        int l = queries.length;
        int[] ans = new int[l];
        for(int i = 0; i < l; i++) ans[i] = binarySearch(index, queries[i]);

        return ans;
    }
    private int dfs(int x, int y, int limit) {
        int ans = 1;
        if(x != 0 && !seen[x - 1][y]) {
            seen[x - 1][y] = true;
            if(grid[x - 1][y] > limit) pq.add(new int[] {x - 1, y, grid[x - 1][y]});
            else ans += dfs(x - 1, y, limit);
        }
        if(x != n - 1 && !seen[x + 1][y]) {
            seen[x + 1][y] = true;
            if(grid[x + 1][y] > limit) pq.add(new int[] {x + 1, y, grid[x + 1][y]});
            else ans += dfs(x + 1, y, limit);
        }
        if(y != 0 && !seen[x][y - 1]) {
            seen[x][y - 1] = true;
            if(grid[x][y - 1] > limit) pq.add(new int[] {x, y - 1, grid[x][y - 1]});
            else ans += dfs(x, y - 1, limit);
        }
        if(y != m - 1 && !seen[x][y + 1]) {
            seen[x][y + 1] = true;
            if(grid[x][y + 1] > limit) pq.add(new int[] {x, y + 1, grid[x][y + 1]});
            else ans += dfs(x, y + 1, limit);
        }
        return ans;
    }
    private int binarySearch(int right, int target) {
        if(target <= value[0]) return 0;
        if(target > value[right]) return count[right];
        int left = 0;
        while(left < right) {
            int mid = left + right + 1 >>> 1;
            if(value[mid] < target) left = mid;
            else right = mid - 1;
        }
        return count[left];
    }
}