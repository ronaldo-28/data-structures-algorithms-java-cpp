class Solution {
    public int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
        int i1=startPos[0],i2=homePos[0];
        int j1=startPos[1],j2=homePos[1];
        int ans=0;
        if(i1<i2){
            for(int i=i1+1;i<=i2;i++){
                ans+=rowCosts[i];
            }
        }else{
            for(int i=i1-1;i>=i2;i--){
                ans+=rowCosts[i];
            }
        }
        if(j1<j2){
            for(int j=j1+1;j<=j2;j++){
                ans+=colCosts[j];
            }
        }else{
            for(int j=j1-1;j>=j2;j--){
                ans+=colCosts[j];
            }
        }
        return ans;
    }
}