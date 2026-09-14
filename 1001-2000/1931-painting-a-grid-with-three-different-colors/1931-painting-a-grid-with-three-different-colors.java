import java.util.Arrays;

final class Solution {
    private static final long MODULUS = 1_000_000_007L;
    private static final int BASE = 3;
    private static final int MAXIMUM_HEIGHT = 5;
    private static final int MAXIMUM_WIDTH = 1000;
    private static final int[][] COUNTS = new int[Solution.MAXIMUM_HEIGHT][Solution.MAXIMUM_WIDTH];

    static {
        int d0 = Solution.BASE;

        for (int i = 0; i < Solution.MAXIMUM_HEIGHT; ++i) {
            final int d1 = i + 1;
            final int[] pattern = new int[d1];
            final int[][] table = new int[d0][d1];
            int index = d0;

            while (true) {
                int j = 1;

                for (; j < d1; ++j) {
                    if (pattern[j - 1] == pattern[j]) {
                        break;
                    }
                }

                if (j == d1) {
                    table[--index] = Arrays.copyOf(pattern, d1);
                }

                for (j = 0; j < d1; ++j) {
                    if (++pattern[j] != Solution.BASE) {
                        break;
                    }

                    pattern[j] = 0;
                }

                if (j == d1) {
                    break;
                }
            }

            final boolean[][] matrix = new boolean[d0][d0];

            for (int j = 0; j < d0; ++j) {
                final int[] p0 = table[j];

                for (int k = 0; k < d0; ++k) {
                    if (k == j) {
                        continue;
                    }

                    final int[] p1 = table[k];
                    int l = 0;

                    for (; l < d1; ++l) {
                        if (p0[l] == p1[l]) {
                            break;
                        }
                    }

                    if (l != d1) {
                        continue;
                    }

                    matrix[j][k] = true;
                    matrix[k][j] = true;
                }
            }

            long[] s0 = new long[d0];
            long[] s1 = new long[d0];
            final int[] row = Solution.COUNTS[i];

            Arrays.fill(s0, 1L);
            row[0] = d0;

            for (int j = 1; j < Solution.MAXIMUM_WIDTH; ++j) {
                long count = 0L;

                for (int k = 0; k < d0; ++k) {
                    for (int l = 0; l < d0; ++l) {
                        if (matrix[l][k]) {
                            s1[k] += s0[l];
                        }
                    }

                    s1[k] %= Solution.MODULUS;
                    count += s1[k];
                }

                s0 = s1;
                s1 = new long[d0];
                row[j] = (int) (count % Solution.MODULUS);
            }

            d0 *= (Solution.BASE - 1);
        }
    }

    static final int colorTheGrid(final int height, final int width) {
        return Solution.COUNTS[height - 1][width - 1];
    }
}