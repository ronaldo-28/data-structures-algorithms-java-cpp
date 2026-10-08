import java.util.AbstractList;

class Solution {

    public List<Integer> countOfPeaks(int[] ar, int[][] Q) {
        return new AbstractList<Integer>() {

            private List<Integer> resList;

            private void onload() {
                resList = new ArrayList<>();
                int n = ar.length;
                int[] p = new int[n];
                for (int i = 1; i < n - 1; i++) {
                    if (ar[i] > ar[i - 1] && ar[i] > ar[i + 1]) {
                        p[i] = 1;
                    }
                }
                SegmentTree st = new SegmentTree(n);
                st.build(p, 0, n - 1, 0);
                for (int[] q: Q) {
                    int type = q[0], l = q[1], r = q[2];
                    if (type == 1) {
                        resList.add(st.query(0, n - 1, l + 1, r - 1, 0));
                    } else {
                        ar[l] = r;
                        for (int j = l - 1; j <= l + 1; j++) {
                            if (j - 1 >= 0 && j + 1 < n) {
                                int last = p[j];
                                int curr = (ar[j] > ar[j - 1] && ar[j] > ar[j + 1]) ? 1 : 0;
                                if (curr != last) {
                                    st.update(j, curr, 0, n - 1, 0);
                                }
                                p[j] = curr;
                            }
                        }
                    }
                }
            }

            private void init() {
                if (null == resList) {
                    onload();
                    System.gc();
                }
            }

            @Override
            public Integer get(int index) {
                init();
                return resList.get(index);
            }

            @Override
            public int size() {
                init();
                return resList.size();
            }

        };

    }

    class SegmentTree {
        int tree[];

        public SegmentTree(int n) {
            this.tree = new int[4 * n];
        }

        public void build(int[] ar, int l, int r, int v) {
            if (l == r) {
                tree[v] = ar[l];
                return;
            }
            int mid = (l + r) >> 1;
            build(ar, l, mid, 2 * v + 1);
            build(ar, mid + 1, r, 2 * v + 2);
            tree[v] = tree[2 * v + 1] + tree[2 * v + 2];
        }

        public int query(int l, int r, int qL, int qR, int v) {
            // no overlap
            if (qR < l || r < qL) {
                return 0;
            }
            // complete overlap
            if (qL <= l && r <= qR) {
                return tree[v];
            }
            // partial overlap
            int mid = (l + r) >> 1;
            return query(l, mid, qL, qR, 2 * v + 1) +
                query(mid + 1, r, qL, qR, 2 * v + 2);
        }

        public void update(int idx, int val, int l, int r, int v) {
            if (l == r) {
                if (idx == l) {
                    tree[v] = val;
                }
                return;
            }
            int mid = (l + r) >> 1;
            if (l <= idx && idx <= mid) {
                update(idx, val, l, mid, 2 * v + 1);
            } else {
                update(idx, val, mid + 1, r, 2 * v + 2);
            }
            tree[v] = tree[2 * v + 1] + tree[2 * v + 2];
        }
    }

}