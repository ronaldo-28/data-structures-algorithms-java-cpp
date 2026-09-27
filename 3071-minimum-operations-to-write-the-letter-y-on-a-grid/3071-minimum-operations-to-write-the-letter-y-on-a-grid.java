class Solution {
    public int minimumOperationsToWriteY(int[][] grid) {

        int[][] dirs = new int[][] { { -1, -1 },
                { -1, 1 },
                { 1, 0 } };

        int mid = grid.length / 2, m = grid.length;

        int[] ycounts = new int[3];
        int[] allcounts = new int[3];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                allcounts[grid[i][j]]++;
            }
        }

        ycounts[grid[mid][mid]]++;
        allcounts[grid[mid][mid]]--;
        for (int[] dir : dirs) {
            int x = mid + dir[0], y = mid + dir[1];
            while (x >= 0 && x < m && y >= 0 && y < m) {
                ycounts[grid[x][y]]++;
                allcounts[grid[x][y]]--;
                x += dir[0];
                y += dir[1];
            }
        }
        // System.out.println(Arrays.toString(ycounts));
        // System.out.println(Arrays.toString(allcounts));
        int ans = m*m;
        for (int val = 0; val < 3; val++) {
            switch (val) {
                case 0:
                    int count = ycounts[1] + ycounts[2] + allcounts[0];
                    count += Math.min(allcounts[1], allcounts[2]);
                    ans = Math.min(ans, count);
                    break;
                case 1:
                    count = ycounts[0] + ycounts[2] + allcounts[1];
                    count += Math.min(allcounts[0], allcounts[2]);
                    ans = Math.min(ans, count);
                    break;
                case 2:
                    count = ycounts[1] + ycounts[0] + allcounts[2];
                    count += Math.min(allcounts[1], allcounts[0]);
                    ans = Math.min(ans, count);
            }
        }

        return ans;

    }
}