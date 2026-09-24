class Solution {
    public int maximum(int [][]matrix,int rows, int j)
    {int max= Integer.MIN_VALUE;
        for(int i=0;i<rows;i++)
        {
            if(matrix[i][j] >max)
                max= matrix[i][j];
        }
        return max;
    }
    public int[][] modifiedMatrix(int[][] matrix) {
        int m= matrix.length;
        int n = matrix[0].length;
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(matrix[i][j]==-1)
                {
                    matrix[i][j]= maximum(matrix,m,j);
                }
            }
        }
        return matrix;
    }
}