class Solution {
    static int LEFT = 0b0001, RIGHT = 0b0010, TOP = 0b0100, BOTTOM = 0b1000;
    static int[] directions = { 0, LEFT|RIGHT, TOP|BOTTOM, LEFT|BOTTOM, BOTTOM|RIGHT, LEFT|TOP, TOP|RIGHT };

    public boolean hasValidPath(int[][] grid) {
        int outDirection = directions[grid[0][0]];
        return check(grid, outDirection & BOTTOM) || check(grid, outDirection & RIGHT);
    }

    boolean check(int[][] grid, int outDirection){
        int m = grid.length, n = grid[0].length;
        int x = 0, y = 0, inDirection = 0;
        while(true){
            if(x == m -1 && y == n -1) return true;
            if(outDirection == LEFT){
                y--;
                inDirection = RIGHT;
            }else if(outDirection == RIGHT){
                y++;
                inDirection = LEFT;
            }else if(outDirection == TOP){
                x--;
                inDirection = BOTTOM;
            }else if(outDirection == BOTTOM){
                x++;
                inDirection = TOP;
            }else{
                return false;
            }
            
            if(x == 0 && y == 0) return false;
            if(x < 0 || x >= m || y < 0 || y >= n) return false;

            int nextDirection = directions[grid[x][y]];
            outDirection = nextDirection & (~inDirection);
            if(outDirection == nextDirection)
                return false;
        }
    }
}