class Solution {
    public int minDeletionSize(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();

        boolean[] sorted = new boolean[n - 1];
        int deletions = 0;

        for (int col = 0; col < m; col++) {
            boolean needDelete = false;

            // Check if this column breaks order
            for (int row = 0; row < n - 1; row++) {
                if (!sorted[row]) {
                    if (strs[row].charAt(col) > strs[row + 1].charAt(col)) {
                        needDelete = true;
                        break;
                    }
                }
            }

            // If invalid, delete this column
            if (needDelete) {
                deletions++;
                continue;
            }

            // Update sorted status
            for (int row = 0; row < n - 1; row++) {
                if (!sorted[row] &&
                    strs[row].charAt(col) < strs[row + 1].charAt(col)) {
                    sorted[row] = true;
                }
            }
        }

        return deletions;
    }
}