class Solution {
    public long minCost(int[] nums, int x) {
        int n = nums.length;
        int[] left = prevLowerOrEqual(new ArrayIterator(nums, 0, 1), 1);
        int[] right = prevLowerOrEqual(new ArrayIterator(nums, n - 1, -1), 0);
        // System.out.println(Arrays.toString(left));
        // System.out.println(Arrays.toString(right));
        long slope = 0, intercept = 0;
        long[] updateSlope = new long[n + 2];
        long[] updateIntercept = new long[n + 2];
        for (int i = 0; i < n; i++) {
            int min = Math.min(left[i], right[i]);
            int max = Math.max(left[i], right[i]);
            updateSlope[1] += (long) nums[i];
            //updateIntercept[0] += 1 * nums[i];

            updateSlope[min] -= (long) nums[i];
            //updateIntercept[min] -= 1 * nums[i];

            //updateSlope[min] += 1;
            updateIntercept[min] += mul(min, nums[i]);

            //updateSlope[max] -= 1;
            updateIntercept[max] -= mul(min, nums[i]);

            updateSlope[max] -= (long) nums[i];
            updateIntercept[max] += mul(min + max, nums[i]);
            
            updateSlope[Math.min(n + 1, min + max)] += (long) nums[i];
            updateIntercept[Math.min(n + 1, min + max)] -= mul(min + max, nums[i]);
        }
        long res = Long.MAX_VALUE;
        for (int k = 1; k <= n; k++) {
            slope += updateSlope[k];
            intercept += updateIntercept[k];
            res = Math.min(res, (k - 1) * (long) x + (long) slope * (long) k + (long) intercept);
            //System.out.printf("%d * %d + %d = %d\n", slope, k, intercept, slope * k + intercept);
        }
        return res;
    }

    int[] prevLowerOrEqual(ArrayIterator iter, int cmpMinRes) {
        int n = iter.nums.length;
        int[] res = new int[n];
        List<Integer> list = new LinkedList<>();
        while (iter.hasNext()) {
            int i = iter.next();
            while (!list.isEmpty() && Integer.compare(iter.nums[list.getLast()], iter.nums[i]) >= cmpMinRes) {
                list.removeLast();
            }
            list.addLast(i);
        }
        iter.indx = iter.start - iter.step;
        while (iter.hasNext()) {
            int i = iter.next();
            while (!list.isEmpty() && Integer.compare(iter.nums[list.getLast()], iter.nums[i]) >= cmpMinRes) {
                list.removeLast();
            }
            if (list.isEmpty()) {
                res[i] = n;
            } else {
                res[i] = (n + (i - list.getLast()) / iter.step) % n;
            }
            if (res[i] == 0) {
                res[i] = n;
            }
            list.addLast(i);
        }
        return res;
    }

    long add(int ...nums) {
        long res = 0;
        for (int num : nums) {
            res += (long) num;
        }
        return res;
    }

    long mul(int ...nums) {
        long res = 1;
        for (int num : nums) {
            res *= (long) num;
        }
        return res;
    }

    class ArrayIterator {
        final int[] nums;
        final int start;
        final int step;
        int indx;

        ArrayIterator(int[] nums, int start, int step) {
            this.nums = nums;
            this.start = start;
            this.indx = start - step;
            this.step = step;
        }

        boolean hasNext() {
            return indx + step >= 0 && indx + step < nums.length;
        }

        int next() {
            indx += step;
            return indx;
        }
    }
}