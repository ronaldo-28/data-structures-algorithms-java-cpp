class Solution {
        public long[] countBlackBlocks(int m, int n, int[][] coordinates) {

            Map<Long, Integer> blocks = new HashMap<>();


            long totalBlokcs = (long) (n - 1) * (m - 1);
            long[] ans = new long[5];
            ans[0] = totalBlokcs;
            int[][] affectBlocks = new int[][]{{-1, -1}, {-1, 0}, {0, -1}, {0, 0}};
            for (int[] c : coordinates) {
                int x = c[0];
                int y = c[1];

                for (int [] d : affectBlocks) {
                    int dx = x + d[0];
                    int dy = y + d[1];
                    if (dx >= 0 && dx < m - 1 && dy >= 0 && dy < n - 1) {
                        long blockNo = ((long) dx * n) + dy;
                        int totalBefore = blocks.getOrDefault(blockNo, 0);
                        ans[totalBefore]--;
                        blocks.put(blockNo, totalBefore + 1);
                        ans[totalBefore + 1]++;
                    }
                }

            }

            // System.out.println("size = " + blocks.size());
            return ans;
        }
    }