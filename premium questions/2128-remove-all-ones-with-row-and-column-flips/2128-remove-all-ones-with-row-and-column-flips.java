class Solution {
    public boolean removeOnes(int[][] grid) {
        
        // we will check that all subsequent rows match this pattern
        int[] check = grid[0];
        
        for (int[] row : grid) {
            
            // bits are switched
            boolean invert = row[0] != check[0];
            
            for (int i = 0; i < row.length; i++) {
                if (row[i] != check[i] && !invert ||
                   row[i] == check[i] && invert) {
                    return false;
                }
            }
        }
        
        return true;
    }
}