class Solution {
    private static final int[][] directions = new int[][]{{-1,0}, {1,0}, {0,1}, {0, -1}};
    public int shortestBridge(int[][] grid) {
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == 1) {   
                    q.offer(new int[]{i, j});
                    grid[i][j] = 1000;
                    return bfs(grid, q, 1000, 2, false);
                }
            }
        }
        return -1;
    }

    int bfs(int[][] grid, Queue<int[]> q, int code, int newcode, boolean findIsland) {
        Queue<int[]> newq = new LinkedList<>();
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] item = q.poll();
                for (int[] direction : directions) {
                    int x1 = item[0] + direction[0];
                    int y1 = item[1] + direction[1];
                    
                    if (x1 >= 0 && y1 >= 0 && x1 < grid.length && y1 < grid.length) {
                        if (!findIsland && grid[x1][y1] == 1) {
                            grid[x1][y1] = code;
                            q.offer(new int[]{x1, y1});
                        } else if (grid[x1][y1] == 0) {
                            grid[x1][y1] = newcode;
                            newq.offer(new int[]{x1, y1});
                        } else if (findIsland && grid[x1][y1] == 1) {
                            return grid[item[0]][item[1]] - 1;    
                        }
                    }
                }
            }
        }
        return bfs(grid, newq, newcode, newcode + 1, true);
    }

    int visitedToNewIsland(int[][] grid, Set<List<Integer>> visited) {
        if (visited.isEmpty()) return -1;

        Set<List<Integer>> visitedNext = new HashSet<>();
        for(List<Integer> list : visited) {
            for (int[] direction : directions) {
                int x1 = list.get(0) + direction[0];
                int y1 = list.get(1) + direction[1];
                if (x1 >= 0 && y1 >= 0 && x1 < grid.length && y1 < grid.length) {
                    if (grid[x1][y1] == 1) {
                        return grid[list.get(0)][list.get(1)] - 1;
                    }
                    if (grid[x1][y1] == 0) {
                        grid[x1][y1] = grid[list.get(0)][list.get(1)] + 1;
                        visitedNext.add(Arrays.asList(x1, y1));
                    }
                }
            }
        }
        return visitedToNewIsland(grid, visitedNext);
    }
}