class Solution {
    public int countLatticePoints(int[][] circles) {
        boolean arr[][]=new boolean[201][201];
        for(int c[]:circles){
            helper(arr,c[0],c[1],c[2]);
        }
        int c=0;
        for(int i=0;i<201;i++){
            for(int j=0;j<201;j++){
                if(arr[i][j])c++;
            }
        }
        return c;
    }
    public void helper(boolean arr[][],int x,int y,int r){
        for(int i=y+r;i>=y-r;i--){
            for(int j=0;j<=r;j++){

                if(((j)*(j)+(i-y)*(i-y))>r*r){
                    break;
                }
                arr[i][x+j]=true;
                arr[i][x-j]=true;
            }
        }
    }
}