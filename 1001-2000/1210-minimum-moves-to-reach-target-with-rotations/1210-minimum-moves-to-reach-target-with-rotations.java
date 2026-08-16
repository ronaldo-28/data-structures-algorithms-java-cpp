// consider butt of snake as location
// if vertical at vdp[0][0] then head is at [1][0];
// if horizontal at vdp[0][0] then head is at [0][1];

// to be able to rotate horizontal to vertical r+1, c and r+1, c+1 need to be clear
// to be able to rotate vertical to horizontal r, c+1 and r+1, c+1 need to be clear

// vertical is calculated from min vertical of dp, above and left, with (min horizontal + 1) of same position if clear
// horizontal same way

class Solution {
    int n;
    int[][] hdp, vdp;
    public int minimumMoves(int[][] grid){
        this.n = grid.length;
        hdp = new int[n][n];
        vdp = new int[n][n];
        for(int i = 0; i < n; i++){
            Arrays.fill(vdp[i], 1000);
            Arrays.fill(hdp[i], 1000);
        }
        hdp[0][0] = 0;
        // fill for horizontal row
        for(int i = 1; i + 1 < n && grid[0][i] != 1 && grid[0][i+1] == 0; i++)
            hdp[0][i] = hdp[0][i-1] + 1;
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                calc(i, j, grid);
        return hdp[n-1][n-2] >= 1000 ? -1 : hdp[n-1][n-2];
        
    }
    void calc(int r, int c, int[][] grid){
        if(grid[r][c] == 1) return;

        // Check horizontal above and left
        // min Horizontal
        int minh = hdp[r][c];
        //check h above
        if(r - 1 >= 0 && c + 1 < n && grid[r][c+1] == 0){
            minh = Math.min(minh, hdp[r-1][c]);
        }
        // check h to side
        if(c - 1 >= 0 && c + 1 < n && grid[r][c+1] == 0){
            minh = Math.min(minh, hdp[r][c-1]);
        }
        // assign minh + 1
        if(minh < 1_000_000);
            hdp[r][c] = Math.min(hdp[r][c], minh+1);


        // Check vertical above and left
        int minv = vdp[r][c];
        // check v above
        if(r - 1 >= 0 && r + 1 < n && grid[r+1][c] == 0){
            minv = Math.min(minv, vdp[r-1][c]);
        }
        // check v to side
        if(c - 1 >= 0 && r + 1 < n && grid[r+1][c] == 0){
            minv = Math.min(minv, vdp[r][c-1]);
        }
        // assign minv + 1
        if(minv < 1_000_000)
            vdp[r][c] = Math.min(minv+1, vdp[r][c]);


        // check if vertical to horizontal rotation is possible
        if(c + 1 < n && r+1 < n && grid[r][c+1] == 0 && grid[r+1][c+1] == 0){
            hdp[r][c] = Math.min(hdp[r][c], vdp[r][c] >= 1000 ? 1000 : vdp[r][c] + 1);
        }
        
        // check if horizontal to vertical rotation is possible
        if(c + 1 < n && r+1 < n && grid[r+1][c+1] == 0 && grid[r+1][c] == 0){
            vdp[r][c] = Math.min(vdp[r][c], hdp[r][c] >= 1000 ? 1000 : hdp[r][c] + 1);
        }
    }
}