class Solution {
    public int smallestFactorization(int num) {

        if (num == 1) return 1;

        int[] digits = new int[10];
        int i = digits.length - 1;

        for (int d = 9; d > 1; d--) {
            while (i >= 0 && num % d == 0) {
                digits[i--] = d;
                num /= d;
            }
        }

        if (num != 1) return 0;

        long out = 0;
        for (int digit : digits) {
            out = (out * 10) + digit;
        }

        return (out > Integer.MAX_VALUE) ? 0 : (int) out;
        
    }
}