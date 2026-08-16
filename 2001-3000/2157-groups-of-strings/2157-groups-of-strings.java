class Solution {
    private int []ds;
    Map<Integer, Integer> map;
    private int find(int i) {
        return ds[i] < 0 ? i : (ds[i] = find(ds[i]));
    }
    private void merge(int i, int mask) {
        if (map.containsKey(mask)) {
            int pi = find(i);
            int pj = find(map.get(mask));
            if (pi != pj) {
                if (ds[pi] > ds[pj]) {
                    pi ^= pj;
                    pj ^= pi;
                    pi ^= pj;
                }
                ds[pi] += ds[pj];
                ds[pj] = pi;
            }
        } else {
            map.put(mask, i);
        }
    }
    public int[] groupStrings(String[] words) {
        ds = new int[words.length];
        map = new HashMap<>();
        Arrays.fill(ds, -1);
        for (int i = 0; i < words.length; ++i) {
            int mask = 0;
            for (char ch : words[i].toCharArray())
                mask |= (1 << (ch - 'a'));
            merge(i, mask);
            for (int j = 0; j < 26; ++j)
                if ((mask & (1 << j)) != 0)
                    merge(i, mask ^ (1 << j));
        }
        int groups = 0;
        int maxSize = 0;
        for (int x : ds) {
            if (x < 0) {
                groups++;
                maxSize = Math.max(maxSize, -x);
            }
        }
        return new int[]{groups, maxSize};
    }
}