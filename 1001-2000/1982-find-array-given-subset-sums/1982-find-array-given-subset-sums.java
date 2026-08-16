class Solution {
    public int[] recoverArray(int n, int[] sums) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int num : sums) {
            max = Math.max(num, max);
            min = Math.min(num, min);
        }
        int[] counts = new int[max - min + 1];
        for (int num : sums) {
            counts[num - min]++;
        }
        int idx = 0;
        for (int i = 0; i < counts.length; i++) {
            for (int j = 0; j < counts[i]; j++) {
                sums[idx++] = i + min;
            }
        }
        int[] res = new int[n];
        int base = 0;
        for (int i = 0; i < n; ++i) {
            int diff = sums[1] - sums[0];
            int k = 0;
            int[] sumsNext = new int[sums.length / 2];
            idx = 0;
            boolean zero = false;
            for (int j = 0; idx < sumsNext.length; j++) {
                if (sums[j] == Integer.MIN_VALUE)
                    continue;
                if (k == j)
                    k++;
                while (sums[k] - diff < sums[j]) {
                    k++;
                }
                sumsNext[idx++] = sums[j];
                sums[k++] = Integer.MIN_VALUE;
                if (sums[j] == base)
                    zero = true;
            }
            if (!zero) {
                base -= diff;
                res[i] = -diff;
            } else {
                res[i] = diff;
            }
            sums = sumsNext;
        }
        return res;
    }
}