class Solution {

    public static final int MOD = (int)1e9 + 7;
    public static final int[][] MATRIX = {
        {0, 0, 12, 15, 32, 18, 24},
        {1, 0, 0, 0, 0, 0, 0},
        {0, 1, 0, 0, 0, 0, 0},
        {0, 0, 1, 0, 0, 0, 0},
        {0, 0, 0, 1, 0, 0, 0},
        {0, 0, 0, 0, 1, 0, 0},
        {0, 0, 0, 0, 0, 1, 0}
    };
    public static final int MAT_DIM = 7;
    public static final int[] initial = new int[]{11672, 4136, 1472, 516, 184, 66, 22};
    int[][] dummy;

    public int distinctSequences(int n) {
        if(n-- == 1) return 6;
        dummy = new int[MAT_DIM][MAT_DIM];
        if(n <= MAT_DIM) return initial[MAT_DIM-n];
        int[][] expMat = matrixPow(MATRIX, n-MAT_DIM);
        int res = 0;
        //now do the start of matrix-vector multiplication to get F(N) from the F(N) through F(N-6) result vector
        for(int i = 0; i < MAT_DIM; i++) {
            res = (int)((res + (long)expMat[0][i]*initial[i]) % MOD);
        }

        return res;
    }

    private void matrixMultiply(int[][] A, int[][] B) {
        for(int i = 0; i < MAT_DIM; i++) {
            for(int j = 0; j < MAT_DIM; j++) {
                long temp = 0l;
                for(int k = 0; k < MAT_DIM; k++) {
                    temp = (temp + (long)A[i][k]*B[k][j]) % MOD;
                }
                dummy[i][j] = (int)temp;
            }
        }
        

        for(int i = 0; i < MAT_DIM; i++) {
            for(int j = 0; j < MAT_DIM; j++) {
                A[i][j] = dummy[i][j];
            }
        }
    }

    private int[][] matrixPow(int[][] matrix, int exp) {
        int[][] res = new int[MAT_DIM][MAT_DIM];
        for(int i = 0; i < MAT_DIM; i++) { //start res as identity
            res[i][i] = 1;
        }
        
        int[][] multiplier = new int[MAT_DIM][MAT_DIM];
        for(int i = 0; i < MAT_DIM; i++) { 
            for(int j = 0; j < MAT_DIM; j++) { 
                multiplier[i][j] = matrix[i][j];
            }
        }
        while(exp != 0) {
            if((exp & 1) != 0) {
                matrixMultiply(res, multiplier);
            }
            exp >>= 1;
            matrixMultiply(multiplier, multiplier);
        }
        return res;
    }
}