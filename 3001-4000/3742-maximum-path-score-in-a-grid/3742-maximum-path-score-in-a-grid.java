class Solution {
    public int maxPathScore(int[][] grid, int k111) {
        final int m = grid.length;
        final int n = grid[0].length;
        final int kk = Math.min(k111, m + n - 2);
        final Sol[] current = firstRow(grid[0], kk);
        //System.out.println(toString(current));
        for (int i = 1; i < m; i++) {
            final var row = grid[i];
            current[0] = current[0].add(row[0]);
            for (int j = 1; j < n; j++) {
                current[j] = current[j].add2(current[j - 1], row[j]);
            }
            //System.out.println(toString(current));
        }
        return current[n - 1].max();
    }

    static Sol[] firstRow(final int[] row, int k) {
        final int k1 = k + 1;
        final Sol[] arr = new Sol[row.length];
        int idx = 0;
        int sum = 0;
        for (int i = 0; i < row.length; i++) {
            final int v = row[i];
            final int[] sums = new int[k1];
            Arrays.fill(sums, MIN_VAL);
            if (v > 0) {
                idx++;
                sum += v;
            }
            if (idx < k1) {
                sums[idx] = sum;
                arr[i] = new Sol(sums, idx, idx + 1);
            } else {
                arr[i] = new Sol(sums, k1, 0);
            }
        }
        return arr;
    }

    static final int MIN_VAL = Integer.MIN_VALUE / 2;

    record Sol(int[] sums, int start, int end) {
        Sol {
            if (end <= start) {
                start = sums.length;
                end = 0;
            }
        }
        
        Sol add(int v) {
            if (v == 0 || start >= end) {
                return this;
            }
            final int newEnd = Math.min(sums.length, end + 1);
            for (int i = newEnd - 1; i > start; i--) {
                sums[i] = sums[i - 1] + v;
            }
            sums[start] = MIN_VAL;
            return new Sol(sums, start + 1, newEnd);
        }

        Sol add2(Sol other, int v) {
            for (int i = other.start; i < other.end; i++) {
                sums[i] = Math.max(sums[i], other.sums[i]);
            }
            final int newStart = Math.min(start, other.start);
            final int newEnd = Math.max(end, other.end);
            if (newStart < newEnd) {
                return new Sol(sums, newStart, pickEnd(newStart, newEnd)).add(v);
            }
            return new Sol(sums, sums.length, 0);
        }

        int pickEnd(final int start, final int end) {
            for (int i = end - 2; i > start; i--) {
                if (sums[i] < sums[i + 1]) {
                    return i + 2;
                }
            }
            return end;
        }

        public int max() {
            int r = -1;
            for (int i = start; i < end; i++) {
                r = Math.max(r, sums[i]);
            }
            return r;
        }

        @Override
        public String toString() {
            return "s=" + Arrays.toString(sums);
        }
    }

    public String toString(Sol[] s) {
        final StringBuilder sb = new StringBuilder();
        String last = s[0].toString();
        sb.append(last);
        for (int i = 1; i < s.length; i++) {
            final String str = s[i].toString();
            if (!last.equals(str)) {
                last = str;
                sb.append(", ").append(str);
            }
        }
        return sb.toString();
    }
}