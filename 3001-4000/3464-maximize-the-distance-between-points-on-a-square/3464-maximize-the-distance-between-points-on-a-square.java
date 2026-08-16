// https://www.youtube.com/@0x3f
class Solution {
    public int maxDistance(int side, int[][] points, int k) {
        long[] a = new long[points.length];
        for (int i = 0; i < points.length; i++) {
            int x = points[i][0];
            int y = points[i][1];
            if (x == 0) {
                a[i] = y;
            } else if (y == side) {
                a[i] = side + x;
            } else if (x == side) {
                a[i] = side * 3L - y;
            } else {
                a[i] = side * 4L - x;
            }
        }
        Arrays.sort(a);

        int left = 1;
        int right = side + 1;
        while (left + 1 < right) {
            int mid = (left + right) >>> 1;
            if (check(a, side, k, mid)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private boolean check(long[] a, int side, int k, int low) {
        int[] idx = new int[k];
        long cur = a[0];
        for (int j = 1; j < k; j++) {
            int i = lowerBound(a, cur + low);
            if (i == a.length) {
                return false;
            }
            idx[j] = i;
            cur = a[i];
        }
        if (cur - a[0] <= side * 4L - low) {
            return true;
        }

        // 第一个指针移动到第二个指针的位置，就不用继续枚举了
        int end0 = idx[1];
        for (idx[0] = 1; idx[0] < end0; idx[0]++) {
            for (int j = 1; j < k; j++) {
                while (a[idx[j]] < a[idx[j - 1]] + low) {
                    idx[j]++;
                    if (idx[j] == a.length) {
                        return false;
                    }
                }
            }
            if (a[idx[k - 1]] - a[idx[0]] <= side * 4L - low) {
                return true;
            }
        }
        return false;
    }

    private int lowerBound(long[] nums, long target) {
        int left = -1;
        int right = nums.length;
        while (left + 1 < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }
}