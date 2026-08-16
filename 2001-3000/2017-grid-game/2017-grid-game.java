class Solution {
    static {
        for (int i=0; i<400; i++) {
            gridGame(new int[][]{{},{}});
        }
    }
    public static long gridGame(int[][] grid) {
        int n = grid[0].length;
        long top = 0;
        long bottom = 0;
        for (int i=0; i<n; i++) top+=grid[0][i];
        long ans =Long.MAX_VALUE; 
        for (int i=0; i<n; i++) {
            top-=grid[0][i];
            ans = Math.min(ans, Math.max(bottom, top));
            bottom+=grid[1][i];
        }
        return ans;
    }
}