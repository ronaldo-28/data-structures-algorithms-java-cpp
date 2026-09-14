class Solution {
    public int[] countRectangles(int[][] rect, int[][] points) {
        int m = rect.length, n = points.length;
        int[] freq = new int[101];
        Arrays.sort(rect, (a, b) -> b[0] - a[0]);
        int[][] pts = new int[n][];
        for (int i = 0; i < n; i++) {
            pts[i] = new int[]{points[i][0], points[i][1], i};
        }
        Arrays.sort(pts, (a, b) -> b[0] - a[0]);
        int[] ans = new int[n];
        for (int i = 0, j = 0; i < n; i++) {
            int x = pts[i][0], y = pts[i][1], idx = pts[i][2];
            while (j < m && x <= rect[j][0]) {
                freq[rect[j][1]]++;
                j++;
            }
            int cnt = 0;
            for (int k = y; k <= 100; k++) {
                cnt += freq[k];
            }
            ans[idx] = cnt;
        }
        return ans;
    }

}