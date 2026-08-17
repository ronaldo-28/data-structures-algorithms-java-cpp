class Solution {
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }
        
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] distanceGrid = new int[rows][cols]; 
        int buildingNumber = 0; 
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    bfs(i, j, grid, distanceGrid, buildingNumber);
                    buildingNumber--; // 下一个建筑要找的空地标记为更小的值
                }
            }
        }
        
        int globalMin = Integer.MAX_VALUE;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 【修复2】：只统计那些成功被"所有建筑"遍历到的空地
                if (grid[i][j] == buildingNumber) {
                    globalMin = Math.min(globalMin, distanceGrid[i][j]);
                }
            }
        }
        
        return globalMin == Integer.MAX_VALUE ? -1 : globalMin;
    }

    private void bfs(int startRow, int startCol, int[][] grid, int[][] distanceGrid, int buildingNumber) {
        int rows = grid.length;
        int cols = grid[0].length;
        // 【优化】：等权重网格寻路，使用 ArrayDeque 按层遍历性能远超 PriorityQueue
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startRow, startCol});
        
        int[] dirs = {-1, 0, 1, 0, -1}; // 经典的上下左右方向数组技巧
        int distance = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            distance++; // 每往外扩散一层，距离 + 1
            
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int r = curr[0];
                int c = curr[1];
                
                // 遍历 4 个方向
                for (int k = 0; k < 4; k++) {
                    int nr = r + dirs[k];
                    int nc = c + dirs[k + 1];
                    
                    // 只有边界合法，且当前空地的值等于 expected buildingNumber 时才走
                    if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == buildingNumber) {
                        // 【修复1】：在 offer 进队列的瞬间，立刻将其 --，防止被其他邻居重复添加！
                        grid[nr][nc]--;
                        
                        // 累加距离
                        distanceGrid[nr][nc] += distance;
                        
                        // 加入下一层队列
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }
        }
    }
}