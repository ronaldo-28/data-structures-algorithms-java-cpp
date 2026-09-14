class Solution {
    int[] direction = new int[]{1, 0, -1, 0, 1};

    public void wallsAndGates(int[][] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    dfs(rooms, i, j);
                }
            }
        }
    }

    void dfs(int[][] rooms, int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nextX = x + direction[i];
            int nextY = y + direction[i + 1];

            if (nextX < 0 || nextX >= rooms.length || 
                nextY < 0 || nextY >= rooms[0].length ||
                rooms[nextX][nextY] <= rooms[x][y] + 1) {
                continue;
            }
            rooms[nextX][nextY] = rooms[x][y] + 1;
            dfs(rooms, nextX, nextY);
        }
    }
}