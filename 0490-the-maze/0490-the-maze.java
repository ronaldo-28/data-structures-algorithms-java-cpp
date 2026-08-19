class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int rows = maze.length;
        int cols = maze[0].length;

        boolean[][] visited = new boolean[rows][cols];
        
        return dfs(maze, start[0], start[1], destination, visited);
    }
    
    private boolean dfs(int[][] maze, int row, int col, int[] destination, boolean[][] visited) {
	    // we have arrived at the destination
        if (row == destination[0] && col == destination[1]) {
            return true;
        }
        if (visited[row][col]) {
            return false;
        }
        
		// mark visited so that we don't visit again.
        visited[row][col] = true;

        // try to move all four directions
        return moveThenDFS(maze, row, col, destination, 1, 0, visited) ||
               moveThenDFS(maze, row, col, destination, -1, 0, visited) ||
               moveThenDFS(maze, row, col, destination, 0, 1, visited) ||
               moveThenDFS(maze, row, col, destination, 0, -1, visited);
    }
    
	// moveThenDFS tries to move as far as it can to the given direciton, then does DFS.
    private boolean moveThenDFS(int[][] maze, int row, int col, int[] destination, int drow, int dcol, boolean[][] visited) { 
        int rows = maze.length;
        int cols = maze[0].length;

        int r = row;
        int c = col;
        while (r + drow >= 0 && r + drow <= rows - 1 && c + dcol >= 0 && c + dcol <= cols - 1 && maze[r + drow][c + dcol] == 0) {
            r = r + drow;
            c = c + dcol;
        }
        
        return dfs(maze, r, c, destination, visited);
    }
}