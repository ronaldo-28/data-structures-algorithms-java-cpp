class Solution {
    public int minimumOR(int[][] grid) {
        int res = (1 << 17) - 1;
        for (int bit = 16; bit >= 0; bit--) {
            int mask = res & (~(1 << bit));
            if (possible(grid, mask)) {
                res = mask;
            }
        }
        return res;
    }
    public boolean possible(int grid[][], int mask) {
        for (int[] row : grid) {
            boolean found = false;
            for (int num : row) {
                if ((num | mask) == mask) {
                    found = true;
                    break;
                }
            }
            if (!found) return false;
        }
        return true;
    }
}