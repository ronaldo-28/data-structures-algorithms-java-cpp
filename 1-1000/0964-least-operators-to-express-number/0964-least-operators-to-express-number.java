class Solution {
        public int leastOpsExpressTarget(int x, int y) {
        int pos = 0, neg = 0, k = 0, pos2, neg2, cur;
        while (y > 0) {
            cur = y % x;
            y /= x;
            if (k > 0) {
                pos2 = Math.min(cur * k + pos, (cur + 1) * k + neg);
                neg2 = Math.min((x - cur) * k + pos, (x - cur - 1) * k + neg);
                pos = pos2;
                neg = neg2;
            } else {
                pos = cur * 2;
                neg = (x - cur) * 2;
            }
            k++;
        }
        return Math.min(pos, k + neg) - 1;
    }
}