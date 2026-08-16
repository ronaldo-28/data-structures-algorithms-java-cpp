class Solution {
    private int hashRow(int[] row){
        int hash=0;
        for (int n : row){
            hash = n+hash*5;
        }
        return hash;
    }
    private int hashCol(int[][] grid, int col){
        int hash=0;
        for (int i=0;i<grid.length;i++){
            hash = grid[i][col]+hash*5;
        }
        return hash;
    }
    public int equalPairs(int[][] grid) {
        int n = grid.length;
        Map<Integer, Integer> row = new HashMap<>();
        for (int i=0;i<n;i++){
            Integer hash = hashRow(grid[i]);
            row.put(hash, 1+row.getOrDefault(hash,0));
        }
        int res=0;
        for (int i=0;i<n;i++){
            Integer hash = hashCol(grid, i);
            if (row.containsKey(hash)){res+=row.get(hash);}
        }
        
        return res;
    }
}