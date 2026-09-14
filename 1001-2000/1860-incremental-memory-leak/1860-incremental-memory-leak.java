class Solution {
    public int[] memLeak(int memory1, int memory2) {
        int m1 = memory1;
        int m2 = memory2;
        int s = 1;
        if (m1 > m2) {
            int d = m1 - m2;
            int lt = 1, rt = (int) 1e5;
            while (lt < rt) {
                int m = lt + rt + 1 >> 1;
                long t = (long) (1 + m) * m / 2;
                if (t > d)
                    rt = m - 1;
                else
                    lt = m;
            }
            long r=lt;
            m1 -= (int)((1 + r) * r / 2);
            s = lt + 1;
        } else if (m1 < m2) {
            int d = m2 - m1;
            int lt = 1, rt = (int) 1e5;
            while (lt < rt) {
                int m = lt + rt + 1 >> 1;
                long t = (long) (1 + m) * m / 2;
                if (t > d)
                    rt = m - 1;
                else
                    lt = m;
            }
            long r=lt;
            m2 -= (int)((1 + r) * r / 2);
            s = lt + 1;
        }
        if (m2 > m1 && m2 >= s)
            m2 -= s++;
        int lt = s, rt = (int) 1e5;
        if (s <= Math.max(m1, m2)) {
            while (lt < rt) {
                int m = lt + rt + 1 >> 1;
                int size = m - s + 1;
                long t1 = size / 2 + size % 2;
                boolean odd = size % 2 == 1;
                t1 = (s + (odd ? m : m - 1)) * t1 / 2;
                long t2 = size / 2;

                t2 = (s + 1 + (odd ? m - 1 : m)) * t2 / 2;
                if (m1 < t1 || m2 < t2)
                    rt = m - 1;
                else
                    lt = m;
            }

            int size = lt - s + 1;
            long t1 = size / 2 + size % 2;
            boolean odd = size % 2 == 1;
            t1 = (s + (odd ? lt : lt - 1)) * t1 / 2;
            long t2 = size / 2;
            t2 = (s + 1 + (odd ? lt - 1 : lt)) * t2 / 2;
            m1 -= (int) t1;
            m2 -= (int) t2;
            s = lt + 1;
        }
        return new int[]{s, m1, m2};
    }
}