class Solution {
    private static final int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int minimumSeconds(List<List<String>> land) {
        Queue<int[]> queue = new LinkedList<>();
        int n = land.size(), m = land.get(0).size();
        boolean[][] seen = new boolean[n][m];
        int[] end = new int[2];
        int[] start = new int[3];
        //traverse grid and store the start and end pos, add all water cells to the queue, and mark all non-empty cells as unwalkable
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                seen[i][j] = true;
                switch(land.get(i).get(j)) {
                    case ".": seen[i][j] = false;
                    break;
                    case "S": start = new int[] {i, j, 1}; //the player cell is given a step count of 1
                    break;
                    case "D": end = new int[] {i, j};
                    break;
                    case "*": queue.offer(new int[] {i, j, 0}); //water cells are given a step count of 0
                    break;
                    default: break;
                }
            }
        }
        int count = 1; //we track how many player cells are in the queue, which is initially 1

        queue.offer(start); //we add the player pos to the end of the queue, this way the water is processed before the player is, so the player wont move onto cells that get flooded at the same time

        while(count != 0) { //BFS ends early if there are no more player cells in the queue
            int[] current = queue.poll();
            int x = current[0], y = current[1], step = current[2];
            if(step != 0) { //if the current item is a player
                if(Math.abs(x - end[0]) + Math.abs(y - end[1]) == 1) return step; //if the player is adjacent to the destination, return the current step count
                step++; //increment the current step count
                count--; //decrement the count of player cells in the queue
            }
            for(int i = 0; i < 4; i++) {
                int nx = x + dir[i][0], ny = y + dir[i][1];
                if(nx == n || ny == m || nx == -1 || ny == -1 || seen[nx][ny]) continue;
                seen[nx][ny] = true;
                queue.offer(new int[] {nx, ny, step});
                if(step != 0) count++; //if we add a new player cell, increment the player cell count
            }
        }
        return -1; //all possible paths have been flooded
    }
}