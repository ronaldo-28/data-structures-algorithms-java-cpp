class Solution {
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
          int visit[][]=new int[m][n];
          int count=0;
          int gr=guards.length;
          int wr=walls.length;
          for(int[] wall:walls)
          {
            visit[wall[0]][wall[1]]=2;
          }
          for(int gu[]:guards)
        {
            visit[gu[0]][gu[1]]=2;
        }
          int[][] dxdy={{-1,0},{1,0},{0,-1},{0,1}};
          for(int row=0;row<gr;row++){
                int dx=guards[row][0];
                int dy=guards[row][1];
                for(int i=dx+1;i<m;i++)
                {
                    int newdx=i;
                    int newdy=dy;
                    if(visit[newdx][newdy]==2)
                    {
                        break;
                    } 
                    if(visit[newdx][newdy]==1)
                    {
                        continue;
                    }
                    visit[newdx][newdy]=1;
                    count++;
                }
                for(int i=dy+1;i<n;i++)
                {
                    int newdx=dx;
                    int newdy=i;
                    if(visit[newdx][newdy]==2)
                    {
                        break;
                    }
                    if(visit[newdx][newdy]==1){
                        continue;
                    }
                    visit[newdx][newdy]=1;
                    count++;

                }
                for(int i=dy-1;i>=0;i--)
                {
                    int newdx=dx;
                    int newdy=i;
                    if(visit[newdx][newdy]==2)
                    {
                        break;
                    }
                        
                    if(visit[newdx][newdy]==1){
                        continue;
                    }
                    visit[newdx][newdy]=1;
                    count++;

                }
                for(int i=dx-1;i>=0;i--)
                {
                    int newdx=i;
                    int newdy=dy;
                    if(visit[newdx][newdy]==2)
                    {
                        break;
                    }
                        
                    if(visit[newdx][newdy]==1){
                        continue;
                    }
                    visit[newdx][newdy]=1;
                    count++;
                }
            
          }
          
          int sum=(m*n)-(gr+wr+count);
          return sum;

    }
   

}