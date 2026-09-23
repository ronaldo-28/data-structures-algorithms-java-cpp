class Solution {
    public int[][] resultGrid(int[][] image, int threshold) {
        int n=image.length;
        int m=image[0].length;
        boolean status[][]=new boolean[n][m];
        int intensity[][]=new int[n][m];
        int count[][]=new int[n][m];
        
    
        
        for(int i=1;i<n-1;i++){
            for(int j=1;j<m-1;j++){
                
                int curri=i;
                int currj=j;
                //System.out.println(curri+" "+currj);
                
                boolean regionPossible=true;
                int regionSum=0;
                
                int r0c0=image[curri-1][currj-1];
                int r0c1=image[curri-1][currj];
                int r0c2=image[curri-1][currj+1];
                int r1c0=image[curri][currj-1];
                int r1c1=image[curri][currj];
                int r1c2=image[curri][currj+1];
                int r2c0=image[curri+1][currj-1];
                int r2c1=image[curri+1][currj];
                int r2c2=image[curri+1][currj+1];
                
                regionSum+=(r0c0+r0c1+r0c2+r1c0+r1c1+r1c2+r2c0+r2c1+r2c2);
                
                if(Math.abs(r0c0-r0c1)>threshold ||
                   Math.abs(r0c0-r1c0)>threshold ||
                   Math.abs(r0c1-r0c0)>threshold ||
                   Math.abs(r0c1-r1c1)>threshold ||
                   Math.abs(r0c1-r0c2)>threshold ||
                   Math.abs(r0c2-r0c1)>threshold ||
                   Math.abs(r0c2-r1c2)>threshold ||
                   
                   Math.abs(r1c0-r1c1)>threshold ||
                   
                   Math.abs(r1c2-r1c1)>threshold ||
                  
                   Math.abs(r2c0-r2c1)>threshold ||
                   Math.abs(r2c0-r1c0)>threshold ||
                   Math.abs(r2c1-r2c0)>threshold ||
                   Math.abs(r2c1-r1c1)>threshold ||
                   Math.abs(r2c1-r2c2)>threshold ||
                   Math.abs(r2c2-r2c1)>threshold ||
                   Math.abs(r2c2-r1c2)>threshold 
                   )
                    regionPossible=false;
                
                if(regionPossible){
                    //System.out.println(curri+" "+currj);
                    regionSum/=9;
                    for(int k=-1;k<=1;k++){
                        for(int l=-1;l<=1;l++){
                            intensity[curri+k][currj+l]+=regionSum;
                            count[curri+k][currj+l]++;
                        }
                    }
                }
                
            }
        }
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(count[i][j]==0){
                    intensity[i][j]=image[i][j];
                }
                else{
                    intensity[i][j]=intensity[i][j]/count[i][j];
                }
            }
        }
        return intensity;
        
    }
}