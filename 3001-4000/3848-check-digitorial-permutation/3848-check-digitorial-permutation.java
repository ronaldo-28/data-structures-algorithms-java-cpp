class Solution {
    private static final int[] fac = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};

    public boolean isDigitorialPermutation(int n) {
        int sumFac = 0;
        int[] cnt = new int[10];
        for (; n > 0; n /= 10) {
            int d = n % 10;
            sumFac += fac[d];
            cnt[d]++;
        }

        for (; sumFac > 0; sumFac /= 10) {
            cnt[sumFac % 10]--;
        }

        for (int i = 0; i < 10; i++) {
            if (cnt[i] != 0) {
                return false;
            }
        }
        return true;
    }
}