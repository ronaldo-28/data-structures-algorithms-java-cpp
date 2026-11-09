class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int n = arrival.length;
        int[] cnt = new int[k];
        long[] busy = new long[Math.max(n, k) + 1];
        int busySize = 0;
        int[] avail = new int[k + n + 1];
        int availSize = 0;
        for (int i = 0; i < k; i++) avail[availSize++] = i;
        for (int i = 0; i < n; i++) {
            int t = arrival[i];
            while (busySize > 0 && busy[0] / k <= t) {
                int id = (int) (busy[0] % k);
                busySize = popL(busy, busySize);
                int v = i + ((id - i) % k + k) % k;
                availSize = pushI(avail, availSize, v);
            }
            if (availSize == 0) continue;
            int id = avail[0] % k;
            availSize = popI(avail, availSize);
            cnt[id]++;
            busySize = pushL(busy, busySize, (long) (t + load[i]) * k + id);
        }
        int max = 0;
        for (int c : cnt) max = Math.max(max, c);
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++) if (cnt[i] == max) res.add(i);
        return res;
    }

    private int pushI(int[] h, int size, int v) {
        int i = size++;
        while (i > 0) {
            int p = (i - 1) >> 1;
            if (h[p] <= v) break;
            h[i] = h[p];
            i = p;
        }
        h[i] = v;
        return size;
    }

    private int popI(int[] h, int size) {
        int v = h[--size], i = 0;
        while (true) {
            int c = 2 * i + 1;
            if (c >= size) break;
            if (c + 1 < size && h[c + 1] < h[c]) c++;
            if (h[c] >= v) break;
            h[i] = h[c];
            i = c;
        }
        h[i] = v;
        return size;
    }

    private int pushL(long[] h, int size, long v) {
        int i = size++;
        while (i > 0) {
            int p = (i - 1) >> 1;
            if (h[p] <= v) break;
            h[i] = h[p];
            i = p;
        }
        h[i] = v;
        return size;
    }

    private int popL(long[] h, int size) {
        long v = h[--size];
        int i = 0;
        while (true) {
            int c = 2 * i + 1;
            if (c >= size) break;
            if (c + 1 < size && h[c + 1] < h[c]) c++;
            if (h[c] >= v) break;
            h[i] = h[c];
            i = c;
        }
        h[i] = v;
        return size;
    }
}