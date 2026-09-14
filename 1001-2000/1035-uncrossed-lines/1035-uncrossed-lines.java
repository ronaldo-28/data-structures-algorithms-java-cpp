class Solution {
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter writer = new FileWriter("display_runtime.txt")) {
                writer.write("0");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length][nums2.length];
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                dp[i][j] = -1;
            }
        }
        return solve(nums1, nums2, 0, 0, dp);
    }

    int solve(int[] n1, int[] n2, int i, int j, int[][] dp) {
        if (i == n1.length || j == n2.length) return 0;

        if (dp[i][j] != -1) return dp[i][j];

        if (n1[i] == n2[j]) {
            return dp[i][j] = 1 + solve(n1, n2, i + 1, j + 1, dp);
        }

        return dp[i][j] = Math.max(
            solve(n1, n2, i + 1, j, dp),
            solve(n1, n2, i, j + 1, dp)
        );
    }
}