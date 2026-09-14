class Solution {

    static int[] que = new int[2500];
    public int cutOffTree(List<List<Integer>> forest) {

        List<int[]> trees = new ArrayList<>();

        int[][] grid = new int[forest.size()][];
        for (int i = 0; i < grid.length; i++) {
            List<Integer> row = forest.get(i);
            grid[i] = new int[row.size()];
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = row.get(j);
                if (grid[i][j] > 1)
                    trees.add(new int[] { i, j, grid[i][j] });
            }
        }

        Collections.sort(trees, (a, b) -> a[2] - b[2]);
        int x = 0, y = 0;
        int result = 0;

        for (int i = 0; i < trees.size(); i++) {
            int nx = trees.get(i)[0], ny = trees.get(i)[1];
            int steps = bfs(grid, x, y, nx, ny);
            if (steps == -1)
                return -1;

            result += steps;

            x = nx;
            y = ny;
        }

        return result;
    }

    public int bfs(int[][] grid, int i, int j, int targetX, int targetY) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int write = 0, read = 0;
        
        que[write++] = i * n + j;
        visited[i][j] = true;
        
        int steps = 0;
        while (read < write) {
            int size = write - read;
            while (size-- > 0) {
                
                int cur = que[read++];
                int x = cur / n, y = cur % n;
                if (x == targetX && y == targetY)
                    return steps;
                    
                if (x > 0 && grid[x - 1][y] != 0 && !visited[x - 1][y]) {
                    visited[x - 1][y] = true;
                    que[write++] = (x - 1) * n + y;
                }
                
                if (x < m - 1 && grid[x + 1][y] != 0 && !visited[x + 1][y]) {
                    visited[x + 1][y] = true;
                    que[write++] = (x + 1) * n + y;
                }
                
                if (y > 0 && grid[x][y - 1] != 0 && !visited[x][y - 1]) {
                    visited[x][y - 1] = true;
                    que[write++] = x * n + y - 1;
                }
                
                if (y < n - 1 && grid[x][y + 1] != 0 && !visited[x][y + 1]) {
                    visited[x][y + 1] = true;
                    que[write++] = x * n + y + 1;
                }
            }
            steps++;
        }
        
        return -1;        
    }
}