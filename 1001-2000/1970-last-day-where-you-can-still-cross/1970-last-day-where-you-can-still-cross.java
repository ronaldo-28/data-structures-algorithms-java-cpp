class Solution {
    private int ind(int r,int c,int C){
        return (r*C+c+1);
    }
    private void join(int lt,int[] grid,int rt){
        grid[find(lt,grid)]=find(rt,grid);
    }
    private int find(int node,int[] dis){
        if(dis[node]!=node){
            dis[node] =find(dis[node],dis);
            return dis[node];
        }
        return node;
    }
    public int latestDayToCross(int R, int C, int[][] cells) {  
        int[] grid=new int[R*C+2]; 
        for(int i=0;i<R*C+2;i++)grid[i]=i;                                
        boolean[] is_water=new boolean[R*C];                    
        int[] row_dif={-1,-1,-1,0,1,1,1,0};                     
        int[] col_dif={-1,0,1,1,1,0,-1,-1};
        int days=0;                     
        for(int[] cell:cells){                                  
            int row=cell[0]-1;                                  
            int col=cell[1]-1;
            is_water[ind(row,col,C)-1]=true;                             
            for(int i=0;i<8;i++){                               
                int ar=row+row_dif[i];                          
                int ac=col+col_dif[i];                        
                if(ar>=0 && ac>=0 && ar<R && ac<C && is_water[ind(ar,ac,C)-1]){             
                    join(ind(ar,ac,C),grid,ind(row,col,C));
                }                                               
            }
            if(col==0){
                join(0,grid,ind(row,col,C));
            }    
            if(col==C-1){
                join(R*C+1,grid,ind(row,col,C));
            }   
            if(find(0,grid)==find(R*C+1,grid))return  days;
            days++;                                           
        } 
        return -1;                                                                   
    }
}