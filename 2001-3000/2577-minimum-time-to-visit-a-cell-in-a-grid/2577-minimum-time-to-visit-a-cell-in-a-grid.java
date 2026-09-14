class Solution {

    private static final int[] DR = {1, -1, 0, 0};
    private static final int[] DC = {0, 0, 1, -1};

    public int minimumTime(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        if (m > 1 && n > 1 &&
            grid[0][1] > 1 &&
            grid[1][0] > 1) {
            return -1;
        }

        int total = m * n;

        int[] dist = new int[total];
        java.util.Arrays.fill(dist, Integer.MAX_VALUE);

        MinHeap heap = new MinHeap(total * 4);

        dist[0] = 0;

        heap.offer(encode(0, 0));

        while (!heap.isEmpty()) {

            long cur = heap.poll();

            int time = (int)(cur >>> 20);
            int pos = (int)(cur & ((1 << 20) - 1));

            if (time != dist[pos]) {
                continue;
            }

            if (pos == total - 1) {
                return time;
            }

            int r = pos / n;
            int c = pos % n;

            for (int k = 0; k < 4; k++) {

                int nr = r + DR[k];
                int nc = c + DC[k];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n)
                    continue;

                int nextPos = nr * n + nc;

                int nt = time + 1;

                int unlock = grid[nr][nc];

                if (nt < unlock) {
                    nt = unlock + ((unlock - nt) & 1);
                }

                if (nt < dist[nextPos]) {

                    dist[nextPos] = nt;

                    heap.offer(
                        encode(nt, nextPos)
                    );
                }
            }
        }

        return -1;
    }

    private long encode(int time, int pos) {
        return (((long) time) << 20) | pos;
    }

    static class MinHeap {

        long[] heap;
        int size;

        MinHeap(int cap) {
            heap = new long[cap];
        }

        boolean isEmpty() {
            return size == 0;
        }

        void offer(long val) {

            int i = size++;
            heap[i] = val;

            while (i > 0) {

                int p = (i - 1) >>> 1;

                if (heap[p] <= heap[i])
                    break;

                long tmp = heap[p];
                heap[p] = heap[i];
                heap[i] = tmp;

                i = p;
            }
        }

        long poll() {

            long res = heap[0];

            heap[0] = heap[--size];

            int i = 0;

            while (true) {

                int l = i * 2 + 1;

                if (l >= size)
                    break;

                int r = l + 1;

                int s = l;

                if (r < size &&
                    heap[r] < heap[l]) {
                    s = r;
                }

                if (heap[i] <= heap[s])
                    break;

                long tmp = heap[i];
                heap[i] = heap[s];
                heap[s] = tmp;

                i = s;
            }

            return res;
        }
    }
}