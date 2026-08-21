class Solution {
    public int numDistinctIslands(int[][] grid) {

        Set<List<Pair<Integer, Integer>>> masterSet = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    List<Pair<Integer, Integer>> islandmap = new ArrayList<>();
                    int rowOrigin = i;
                    int colorigin = j;
                    dfs(i, j, grid, islandmap, rowOrigin, colorigin);
                    masterSet.add(islandmap);
                }
            }
        }
        return masterSet.size();
    }

    public void dfs(int i, int j, int[][]grid, List<Pair<Integer, Integer>> islandmap, int rowOrigin, int colorigin){
        if (i<0 || i>= grid.length || j<0 || j>= grid[0].length)
            return;
        if (grid[i][j] != 1)
            return;
        islandmap.add(new Pair<Integer, Integer>(i- rowOrigin,j-colorigin));
        grid[i][j] = 2;
        dfs(i+1, j, grid,islandmap, rowOrigin, colorigin );
        dfs(i-1, j, grid,islandmap, rowOrigin, colorigin  );
        dfs(i, j+1, grid,islandmap, rowOrigin, colorigin  );
        dfs(i, j-1, grid,islandmap, rowOrigin, colorigin  );

    }
}