class Solution {
    public String smallestGoodBase(String n) {
        long num = Long.parseLong(n);
        int maxM = (int) (Math.log(num) / Math.log(2));
        for (int m = maxM; m >= 1; m--) {
            long k = findK(num, m);
            if (k != -1) {
                return String.valueOf(k);
            }
        }
        return String.valueOf(num - 1);
    }

    private long findK(long num, int m) {
        long low = 2;
        long high = (long) (Math.pow(num, 1.0 / m) + 1);
        while (low <= high) {
            long mid = low + (high - low) / 2;
            long sum = 1;
            long power = 1;
            for (int i = 1; i <= m; i++) {
                power *= mid;
                sum += power;
                if (sum > num) {
                    break;
                }
            }
            if (sum == num) {
                return mid;
            } else if (sum < num) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}