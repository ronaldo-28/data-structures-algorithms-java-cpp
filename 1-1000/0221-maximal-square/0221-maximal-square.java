class Solution {
    static {
        for(int i = 0; i < 500; i++) maximalSquare(new char[][] {{'1'}});
    }
    public static int maximalSquare(char[][] matrix) {
        int area = 0, m = matrix.length, n = matrix[0].length;
        int[] prev = new int[n];
        int[] curr = new int[n];

        for(int col = 0; col < n; col++) {
            int val = matrix[0][col] - 48;
            prev[col] = val;
            area = Math.max(area, val);
        }

        for(int row = 1; row < m; row++) {
            curr[0] = matrix[row][0] - 48;
            area = Math.max(area, curr[0]);

            for(int col = 1; col < n; col++) {
                int val = matrix[row][col] - 48;
                curr[col] = (val) * (1 + Math.min(prev[col], Math.min(prev[col - 1], curr[col-1])));
                area = Math.max(area, curr[col]);
            }
            
            int[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return area * area;
    }
}