// double binary search 的规范：找到确定的 ERR RATE 即可。这里的核心是记录 In 和 out 的百分比
class Solution {
    public static final double ERR = 1e-5;

    public double equalizeWater(int[] buckets, int loss) {
        double rem = (100 - loss) / 100D;
        int n = buckets.length;
        Arrays.sort(buckets);
        if (buckets[0] == buckets[n - 1]) return buckets[0];

        double left = 0, right = buckets[n - 1];
        double ans = left;

        while (right - left > ERR) {
            double mid = (left + right) / 2;

            double in = 0, out = 0;

            for (int b: buckets) {
                if (b <= mid) {
                    in += (mid - b);
                } else {
                    out += b - mid;
                }
            }

            if (out * rem >= in) {
                ans = mid;
                left = mid;
            } else {
                right = mid;
            }
        }

        return ans;
    }
}