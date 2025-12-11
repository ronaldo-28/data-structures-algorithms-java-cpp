class Solution {
    int mod = 1000000007;
    public int checkRecord(int n) {
        // AiLj[len]: length len, i 'A' in the string, and end with j 'L'
        // A0L0[len] = A0L0[len-1](append 1 'P')+A0L1[len-1](append 1 'P')+A0L2[len-1](append 1 'P')
        // A0L1[len] = A0L0[len-1](append 1 'L')
        // A0L2[len] = A0L1[len-1](append 1 'L')
        // A1L0[len] = A0L0[len-1](append 1 'A')+A0L1[len-1](append 1 'A')+A0L2[len-1](append 1 'A')
        //            +A1L0[len-1](append 1 'P')+A1L1[len-1](append 1 'P')+A1L2[len-1](append 1 'P')
        // A1L1[len] = A1L0[len-1](append 1 'L')
        // A1L2[len] = A1L1[len-1](append 1 'L')
        // target sum of AiLj[len]
        // base case: A0L0[1]=1, A0L1[1]=1, A0L2[1]=0, A1L0[1]=1, A1L1[1]=0, A10L2[1]=0
        // matrix expression: (mat^(n-1))*dp[len 0]
        int[][] dp = new int[][]{{1},{1},{0},{1},{0},{0}};
        int[][] mat = new int[][]{{1,1,1,0,0,0},{1,0,0,0,0,0},{0,1,0,0,0,0},{1,1,1,1,1,1},{0,0,0,1,0,0},{0,0,0,0,1,0}};
        n--;
        while( n>0 ){
            if( (n&1)!=0 ) dp = mul(mat, dp);
            n >>= 1;
            mat = mul(mat, mat);
        }
        return modSum(dp, 0, 5);
    }
    
    public int[][] mul(int[][] A, int[][] B){
        int[][] C = new int[A.length][B[0].length];
        for(int i=0; i<C.length; i++){
            for(int j=0; j<C[0].length; j++){
                for(int k=0; k<A[0].length; k++){
                    C[i][j] = (int)((C[i][j]+(long)A[i][k]*B[k][j])%mod);
                }            
            }
        }
        return C;
    }
    
    public int modSum(int[][] arr, int i, int j){
        int res = 0;
        for(int k=i; k<=j; k++) res = (res+arr[k][0])%mod;
        return res;
    }
}