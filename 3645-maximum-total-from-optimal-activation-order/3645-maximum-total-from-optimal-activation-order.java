class Solution {
    public long maxTotal(int[] value, int[] limit) {
        int n = value.length;
        List<Integer>[] groups = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) groups[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) groups[limit[i]].add(value[i]);
        long ans = 0;
        for (int l = 1; l <= n; l++) {
            List<Integer> g = groups[l];
            g.sort(Collections.reverseOrder());
            for (int i = 0; i < Math.min(l, g.size()); i++) ans += g.get(i);
        }
        return ans;
    }
}