class Solution {
    // public void markrow(int index,int[][] matrix)
    // {
    //     for(int i=0;i<matrix[index].length;i++)
    //     {
    //         matrix[index][i]=0;
    //     }
    // }
    // public void markcol(int index,int[][] matrix)
    // {
    //     for(int i=0;i<matrix.length;i++)
    //     {
    //         matrix[i][index]=0;
    //     }
    // }
    public void setZeroes(int[][] matrix) {
        int n = matrix.length;
    int m = matrix[0].length;
    int col0 = 1;

    // Step 1: Use the first row and first column as markers
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (matrix[i][j] == 0) {
                matrix[i][0] = 0;
                if (j != 0)
                    matrix[0][j] = 0;
                else
                    col0 = 0;
            }
        }
    }

    // Step 2: Update the rest of the matrix using the markers
    for (int i = 1; i < n; i++) {
        for (int j = 1; j < m; j++) {
            if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                matrix[i][j] = 0;
            }
        }
    }

    // Step 3: Update the first row if needed
    if (matrix[0][0] == 0) {
        for (int j = 0; j < m; j++) {
            matrix[0][j] = 0;
        }
    }

    // Step 4: Update the first column if needed
    if (col0 == 0) {
        for (int i = 0; i < n; i++) {
            matrix[i][0] = 0;
        }
    }
    }
}