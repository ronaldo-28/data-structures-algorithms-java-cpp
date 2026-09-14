class Solution {
    public int nearestExit(char[][] maze, int[] entrance) {
        int m = maze.length;
        int n = maze[0].length;
        int[] q = new int[m*n*2];
        int queue=0, index=0, res=0;
        q[queue++]=entrance[0];
        q[queue++]=entrance[1];
        maze[entrance[0]][entrance[1]]='+';
        while (index<queue){
            res++;
            int len = (queue-index)/2;
            while (len-->0){
                int row = q[index++];
                int col = q[index++];
                if (row>0 && maze[row-1][col]!='+'){
                    if (row-1==0 || col==0 || col==n-1){return res;}
                    q[queue++]=row-1;
                    q[queue++]=col;
                    maze[row-1][col]='+';
                }
                if (row<m-1 && maze[row+1][col]!='+'){
                    if (row+1==m-1 || col==0 || col==n-1){return res;}
                    q[queue++]=row+1;
                    q[queue++]=col;
                    maze[row+1][col]='+';
                }
                if (col>0 && maze[row][col-1]!='+'){
                    if (row==m-1 || col-1==0 || row==0){return res;}
                    q[queue++]=row;
                    q[queue++]=col-1;
                    maze[row][col-1]='+';
                }
                if (col<n-1 && maze[row][col+1]!='+'){
                    if (row==m-1 || col+1==n-1 || row==0){return res;}
                    q[queue++]=row;
                    q[queue++]=col+1;
                    maze[row][col+1]='+';
                }
            }
        }
        return -1;
    }
}