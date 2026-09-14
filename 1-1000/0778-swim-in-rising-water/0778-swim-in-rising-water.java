class Solution {
  int len;
final static int[][] dirs = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};

public int swimInWater(int[][] grid) {
    len = grid.length;
    int left = Math.max(grid[0][0], grid[len - 1][len - 1]), right = len * len - 1, mid, res = 0;
    while (left <= right) {
        mid = (left + right) / 2;
        boolean[] seen = new boolean[len * len];
        if (dfs(0, 0, grid, mid, seen)) {
            res = mid;
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }
    return res;
}
public boolean dfs(int xn, int yn, int[][] grid, int mid, boolean[] seen) {
    int idx = xn * len + yn;
    if (seen[idx]) return true;
    seen[idx] = true;
    for (int i = 0; i < 4; i++) {
        int newx = xn + dirs[i][0], newy = yn + dirs[i][1];
        if (newx >= 0 && newx < len && newy >= 0
                && newy < len && !seen[newx * len + newy] && grid[newx][newy] <= mid) {
            if (newx == len - 1 && newy == len - 1) {
                return true;
            }
            if (dfs(newx, newy, grid, mid, seen)) {
                return true;
            }
        }
    }
    return false;
}
}