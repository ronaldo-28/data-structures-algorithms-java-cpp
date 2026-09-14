class Solution {
    public long countSubmatrices(int[][] grid, int k) {
        //treat it like a funky histogram
        int n = grid.length, m = grid[0].length;
        int[] heights = new int[n];
        long ans = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) heights[j] = grid[j][i] > k ? 0 : i > 0 && grid[j][i - 1] >= grid[j][i] ? heights[j] + 1 : 1;
            ans += calc(heights, n);
        }
        return ans;
    }
    private long calc(int[] heights, int n) {
        long ans = 0;
        Deque<int[]> stack = new ArrayDeque<>();
        stack.offer(new int[] {-1, 0, -1});
        for(int j = 0; j < n; j++) {
            int height = heights[j];
            while(stack.peekLast()[2] >= height) stack.pollLast();
            int[] prev = stack.peekLast();
            int current = prev[1] + height * (j - prev[0]);
            ans += current;
            stack.offer(new int[] {j, current, height});
        }
        return ans;
    }
}