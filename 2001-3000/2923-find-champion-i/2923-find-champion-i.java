class Solution {
    public int findChampion(int[][] grid) {
        int champ = 0;
        for(int i = 1; i < grid.length; i++){
            if(grid[i][champ] == 1){
                champ = i;
            }
        }
        return champ;
    }
}