class Solution {
    public long nthSmallest(long n, int k) {
        long[][] nCr = new long[51][51];
        for(int i=0;i<51;i++){
            nCr[i][0]=nCr[i][i]=1;
            for(int j=1;j<i;j++){
                nCr[i][j]=nCr[i-1][j-1]+nCr[i-1][j];
            }
        }
        long ans=0;
        int remaining=k;       
        for(int pos=50;pos>=0;pos--){
            if(remaining==0)break;
            long cnt=nCr[pos][remaining];
            if(cnt<n){
                n-=cnt;
                ans|=(1L<<pos);
                remaining--;
            }
        }
        return ans;
    }
}