class Solution {
    public int oddCells(int m, int n, int[][] indices) {
        int rows[] = new int[m];
        int cols[] = new int[n];

        for(int[] index: indices){
            rows[index[0]]++;
            cols[index[1]]++;
        }
        
        int oddRows = 0, oddCols = 0;
        for (int i = 0; i < m; i++) {
            if (rows[i] % 2 != 0) {
                oddRows++;
            }
        }
        for (int j = 0; j < n; j++) {
            if (cols[j] % 2 != 0) {
                oddCols++;
            }
        }

        return (oddRows * (n - oddCols)) + (oddCols * (m - oddRows));
    }
}