class Solution {
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] fa = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            fa[i] = i;
        }

        int[] ans = new int[n];
        Map<Integer, Integer> fullDay = new HashMap<>(); // lake -> 装满日
        for (int i = 0; i < n; i++) {
            int lake = rains[i];
            if (lake == 0) {
                ans[i] = 1; // 先随便选一个湖抽干
                continue;
            }
            Integer j = fullDay.get(lake);
            if (j != null) {
                // 必须在 j 之后，i 之前把 lake 抽干
                // 选一个最早的未被使用的抽水日，如果选晚的，可能会导致其他湖没有可用的抽水日
                int dryDay = find(j + 1, fa);
                if (dryDay >= i) {
                    return new int[]{}; // 无法阻止洪水
                }
                ans[dryDay] = lake;
                fa[dryDay] = find(dryDay + 1, fa); // 删除 dryDay
            }
            ans[i] = -1;
            fa[i] = i + 1; // 删除 i
            fullDay.put(lake, i); // 插入或更新装满日
        }
        return ans;
    }

    private int find(int x, int[] fa) {
        if (fa[x] != x) {
            fa[x] = find(fa[x], fa);
        }
        return fa[x];
    }
}