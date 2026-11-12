class Solution {
    public long subtreeInversionSum(int[][] edges, int[] nums, int k) {
        int n = nums.length;
            List<Integer>[] g = new ArrayList[n];
            Arrays.setAll(g, i -> new ArrayList<>());
            for (int[] e : edges) {
                int x = e[0], y = e[1];
                g[x].add(y);
                g[y].add(x);
            }

            List<long[]> f = new ArrayList<>();
            long[] res = dfs(0, -1, g, nums, k, f);
            return res[0] + res[1]; // 对于根节点来说，上面一定反转了偶数次（0 次）
        }

        private long[] dfs(int x, int fa, List<Integer>[] g, int[] nums, int k, List<long[]> f) {
            f.add(new long[]{0, 0}); // 用于刷表

            long s = nums[x]; // 子树和
            long notInv0 = 0; // 不反转 x 时的额外增量（0 表示上面反转了偶数次，1 表示上面反转了奇数次）
            long notInv1 = 0;
            for (int y : g[x]) {
                if (y == fa) {
                    continue;
                }
                long[] resY = dfs(y, x, g, nums, k, f);
                s += resY[0];
                // 不反转 x，反转次数的奇偶性不变
                notInv0 += resY[1];
                notInv1 += resY[2];
            }

//            long[] subRes = f.removeLast(); // 被刷表后的结果
            long[] subRes = f.remove(f.size() - 1);

            // 反转 x
            // x 上面反转了偶数次，反转 x 会带来 -2 倍子树和的增量，且对于 x 的 k 级后代来说，上面反转了奇数次（所以是 subRes1）
            long inv0 = subRes[1] - s * 2;
            // x 上面反转了奇数次，反转 x 会带来 2 倍子树和的增量，且对于 x 的 k 级后代来说，上面反转了偶数次（所以是 subRes0）
            long inv1 = subRes[0] + s * 2;

            long res0 = Math.max(notInv0, inv0);
            long res1 = Math.max(notInv1, inv1);

            // 刷表法：更新 x 的 k 级祖先的状态
            if (f.size() >= k) {
                long[] ancestor = f.get(f.size() - k);
                ancestor[0] += res0;
                ancestor[1] += res1;
            }

            return new long[]{s, res0, res1};
    }
}