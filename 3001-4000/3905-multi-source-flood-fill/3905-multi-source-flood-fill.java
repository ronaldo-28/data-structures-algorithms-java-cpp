import java.util.*;
class Solution {
    public int[][] colorGrid(int n, int m, int[][] sources) {
        
        int mat[][] = new int[n][m];
        int time[][] = new int[n][m];
        for (int[] row : time) {
            Arrays.fill(row, -1);
        }

        Queue<int[]> q = new LinkedList<>();

        for(int source[]:sources){
            int i = source[0];
            int j = source[1];
            mat[i][j] = source[2];
            time[i][j] = 0;
            q.add(new int[]{i,j,source[2],0});
        }
        
        while(!q.isEmpty()){
            int info[] = q.poll();
            int i = info[0];
            int j = info[1];
            int c = info[2];
            int t = info[3];

            if(i!=0 && (time[i-1][j]==-1 || (time[i-1][j] == t+1 && mat[i-1][j] < c))){
                mat[i-1][j] = c;
                time[i-1][j] = t+1;
                q.add(new int[]{i-1,j,c,t+1});
            }if(i!=n-1 && (time[i+1][j]==-1 || (time[i+1][j] == t+1 && mat[i+1][j] < c))){
                mat[i+1][j] = c;
                time[i+1][j] = t+1;
                q.add(new int[]{i+1,j,c,t+1});     
            }if(j!=0 && (time[i][j-1]==-1 || (time[i][j-1] == t+1 && mat[i][j-1] < c))){      
                mat[i][j-1] = c;
                time[i][j-1] = t+1;
                q.add(new int[]{i,j-1,c,t+1});
            }if(j!=m-1 && (time[i][j+1]==-1 || (time[i][j+1]==t+1 && mat[i][j+1] < c))){
                mat[i][j+1] = c;
                time[i][j+1] = t+1;                    
                q.add(new int[]{i,j+1,c,t+1});
            }

        }
        return mat;
    }
}