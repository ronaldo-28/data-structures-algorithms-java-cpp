class Solution {
    static {
        for (int i = 0; i < 300; i++)
            findDiagonalOrder(new int[][] { { 1, 2 }, { 3, 4 } });
    }

    public static int[] findDiagonalOrder(int[][] mat) {
        boolean up = true;
        int i = 0;
        int j = 0;
        int idx = 0;
        int[] result = new int[mat.length * mat[0].length];
        while (idx < result.length) {
            while (idx < result.length && i >= 0 && j < mat[i].length) {
                result[idx] = mat[i][j];
                i--;
                j++;
                idx++;
            }
            i += 1 + (j == mat[0].length ? 1 : 0);
            j = Math.min(mat[0].length - 1, j);
            while (idx < result.length && j >= 0 && i < mat.length) {
                result[idx] = mat[i][j];
                i++;
                j--;
                idx++;
            }
            j += 1 + (i == mat.length ? 1 : 0);
            i = Math.min(mat.length - 1, i);
        }
        result[result.length - 1] = mat[mat.length - 1][mat[0].length - 1];
        return result;
    }
}