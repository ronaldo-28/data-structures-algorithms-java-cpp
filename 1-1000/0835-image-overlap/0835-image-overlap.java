class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
      int[][] count = new int[img1.length * 2][img1.length * 2];
      for (int i = 0; i < img1.length; i++) {
        for (int j = 0; j < img1.length; j++) {
            if (img1[i][j] == 1) {
                for (int r = 0; r < img2.length; r++) {
                    for (int t = 0; t < img2.length; t++) {
                        if (img2[r][t] == 1) {
                            count[i - r + img1.length][j - t + img1.length]++;
                        }
                    }
                }
            }
        }
      }
      int ans = 0;
      for (int i = 0; i < img1.length * 2; i++) {
        for (int j = 0; j < img1.length * 2; j++) {
            ans = Math.max(ans, count[i][j]);
        }
      }
      return ans;
    }
}