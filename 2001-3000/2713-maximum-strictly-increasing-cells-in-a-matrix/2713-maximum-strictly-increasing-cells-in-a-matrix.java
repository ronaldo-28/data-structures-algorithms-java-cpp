class Solution {
    public int maxIncreasingCells(int[][] mat) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int[] row : mat) {
            for (int val : row) {
                min = Math.min(min, val);
                max = Math.max(max, val);
            }
        }
        List<int[]>[] cells = new List[max - min + 1];
        Arrays.setAll(cells, i -> new ArrayList<>());
        int m = mat.length, n = mat[0].length;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                cells[mat[r][c] - min].add(new int[]{r, c});
            }
        }
        int[] rowMax = new int[m], colMax = new int[n];
        Arrays.fill(rowMax, 0); Arrays.fill(colMax, 0);
        int ans = 0;
        for (List<int[]> list : cells) {
            if (list.isEmpty()) continue;
            if (list.size() == 1) {
                int[] cell = list.get(0);
                int r = cell[0], c = cell[1], mx = Math.max(rowMax[r] + 1, colMax[c] + 1);
                rowMax[r] = mx; colMax[c] = mx;
                ans = Math.max(ans, mx);
                continue;
            }
            List<int[]> maxList = new ArrayList<>();
            for (int[] cell : list) {
                int r = cell[0], c = cell[1], mx = Math.max(rowMax[r] + 1, colMax[c] + 1);
                maxList.add(new int[]{r, c, mx});
            }
            for (int[] cell : maxList) {
                int r = cell[0], c = cell[1], mx = cell[2];
                rowMax[r] = Math.max(rowMax[r], mx);
                colMax[c] = Math.max(colMax[c], mx);
                ans = Math.max(ans, mx);
            }
        }
        return ans;
    }

}