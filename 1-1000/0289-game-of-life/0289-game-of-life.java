class Solution {
    public void gameOfLife(int[][] board) {
        ArrayList<Integer> list = new ArrayList();
        int m = board.length;
        int n=board[0].length;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(willLive(board, i, j)!=board[i][j])
                    list.add(i*n+j);
            }
        }
        for(int index:list){
            int i=index/n;
            int j=index%n;
            board[i][j]=board[i][j]==0?1:0;
        }
    }

    public int willLive(int[][] board, int i, int j){
        int liveCount=0;
        int m=board.length;
        int n=board[0].length;
        if(j-1>=0 && board[i][j-1]==1)
            liveCount++;
        if(j+1<n && board[i][j+1]==1)
            liveCount++;
        if(i-1>=0 && board[i-1][j]==1)
            liveCount++;
        if(i+1<m && board[i+1][j]==1)
            liveCount++;
        if(i-1>=0 && j-1>=0 && board[i-1][j-1]==1)
            liveCount++;
        if(i-1>=0 && j+1<n && board[i-1][j+1]==1)
            liveCount++;
        if(i+1<m && j-1>=0 && board[i+1][j-1]==1)
            liveCount++;
        if(i+1<m && j+1<n && board[i+1][j+1]==1)
            liveCount++;
        return board[i][j]==0?(liveCount==3?1:0):(liveCount>=2 && liveCount<=3 ? 1 : 0);
    }
}