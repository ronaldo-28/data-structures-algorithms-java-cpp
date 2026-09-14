/**
 * // This is the GridMaster's API interface.
 * // You should not implement it, or speculate about its implementation
 * class GridMaster {
 *     boolean canMove(char direction);
 *     void move(char direction);
 *     boolean isTarget();
 * }
 */

class Solution {
    // Direction offsets and characters aligned by index
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // U, D, L, R
    private static final char[] directions = {'U', 'D', 'L', 'R'};
    private static final char[] rev_directions = {'D', 'U', 'R', 'L'};

    private static final int offset = 500;
    private static int[][] grid = new int[1001][1001];
    private static int WALKABLE = 1;
    private static int BLOCKED = 0;
    private static int TARGET = 2;

    public int findShortestPath(GridMaster master) {
        // step 1 is to find target cell location
        boolean[][] visitedDfs = new boolean[1001][1001];
        grid[offset][offset] = WALKABLE;
        visitedDfs[offset][offset] = true;
        findTargetCell(offset, offset, visitedDfs, master);

        //step 2 is to do a bfs from start to target
        int result = bfs();
        return result;
    }

    private int bfs() {
        boolean[][] visited = new boolean[1001][1001];

        visited[offset][offset] = true;

        Queue<int[]> queue = new LinkedList<>(); 
        queue.offer(new int[]{offset, offset});
        int distance = 0;

        while(!queue.isEmpty()) {
            int size = queue.size();

            while(size > 0) {
                size--;
                int[] curr = queue.poll();
                int currRow = curr[0];
                int currCol = curr[1];

                if(grid[currRow][currCol] == TARGET){
                    return distance;
                }

                for(int i = 0; i < 4; i++) {
                    int nextRow = currRow + DIRS[i][0];
                    int nextCol = currCol + DIRS[i][1];

                    if(!visited[nextRow][nextCol] && grid[nextRow][nextCol] != BLOCKED ) {
                        queue.offer(new int[]{nextRow, nextCol});
                        visited[nextRow][nextCol] = true;
                    }
                }
            }
            
            distance++;
        }

        return -1;
    }

    private void findTargetCell(int row, int col, boolean[][] visited, GridMaster master) {

        if(master.isTarget()) {
            grid[row][col] = TARGET; 
        }

        for(int i = 0; i < 4; i++) {
            char dir = directions[i];
            int nextRow = row + DIRS[i][0];
            int nextCol = col + DIRS[i][1];
            if(master.canMove(dir)) {
                if(!visited[nextRow][nextCol]) {
                    grid[nextRow][nextCol] = WALKABLE; 
                    master.move(dir);
                    visited[nextRow][nextCol] = true;
                    findTargetCell(nextRow, nextCol, visited, master);
                    master.move(rev_directions[i]);
                }
            }
            else {
                grid[nextRow][nextCol] = BLOCKED;
            }
        }
    }
}