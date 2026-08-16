class Solution {
    public int maximumWidth(int[] planks) {
        Map<Long, Long> freq = new HashMap<>();
        for (int x : planks) {
            freq.put((long) x, freq.getOrDefault((long) x, 0L) + 1);
        }

        List<Long> vals = new ArrayList<>(freq.keySet());
        Map<Long, Long> pairs = new HashMap<>();

        int m = vals.size();

        for (int i = 0; i < m; i++) {
            for (int j = i; j < m; j++) {
                long a = vals.get(i);
                long b = vals.get(j);
                long sum = a + b;

                if (i == j) {
                    pairs.put(sum, pairs.getOrDefault(sum, 0L) + freq.get(a) / 2);
                } else {
                    pairs.put(sum, pairs.getOrDefault(sum, 0L) + Math.min(freq.get(a), freq.get(b)));
                }
            }
        }

        long ans = 0;

        for (Map.Entry<Long, Long> entry : pairs.entrySet()) {
            long h = entry.getKey();
            long cnt = entry.getValue();
            ans = Math.max(ans, cnt + freq.getOrDefault(h, 0L));
        }

        for (long cnt : freq.values()) {
            ans = Math.max(ans, cnt);
        }

        return (int) ans;
    }
}