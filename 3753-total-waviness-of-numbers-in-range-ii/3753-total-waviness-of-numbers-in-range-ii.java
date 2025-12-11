class Solution {
    static final int MAX_DIGITS = 20;
    static final int[] THREE_0 = three(0);
    static final int[] THREE_1 = three(1);
    static final long[] upTo = upTo();

    static long[] upTo() {
        long[] r = new long[MAX_DIGITS];
        r[3] = THREE_1.length;
        long mult = 9;
        for (int i = 4; i < r.length; i++) {
            r[i] = r[i - 1] * 10 + THREE_0.length * mult;
            mult *= 10;
        }
        for (int i = 4; i < r.length; i++) {
            r[i] += r[i - 1];
        }
        return r;
    }

    public long totalWaviness(long num1, long num2) {
        return total(num2) - total(num1 - 1);
    }

    static long total(long n) {
        if (n < 100) {
            return 0;
        }
        final int[] digits = digits(n);
        long r = upTo[digits.length - 1];
        long left = 0;
        long right = 0;
        long rightM = 1;
        for (int i = 2; i < digits.length; i++) {
            right = right * 10 + digits[i];
            rightM *= 10;
        }
        for (int i = 2; i < digits.length; i++) {
            final int first = digits[i - 2];
            final int last = digits[i];
            final int max = 100 * first + 10 * digits[i - 1] + last;
            rightM /= 10;
            right -= rightM * last;
            if (i == 2) {
                r += d(THREE_1, max, 0, right, rightM);
                left = first - 1;
            } else {
                r += d(THREE_0, max, left, right, rightM);
                left = left * 10 + first;
            }
            // 252
            //System.out.println(r);
        }
        return r;
    }

    static int[] digits(long n) {
        final int[] digits = new int[MAX_DIGITS];
        int l = digits.length;
        while (n > 0) {
            digits[--l] = (int) (n % 10);
            n /= 10;
        }
        return Arrays.copyOfRange(digits, l, digits.length);
    }

    private static int[] three(int astart) {
        final int[] arr = new int[1000];
        int len = 0;
        for (int a = astart; a < 10; a++) {
            for (int b = 0; b < 10; b++) {
                for (int c = 0; c < 10; c++) {
                    if (a < b && c < b || a > b && c > b) {
                        arr[len++] = a * 100 + b * 10 + c;
                    }
                }
            }
        }
        return Arrays.copyOf(arr, len);
    }

    private static long d(int[] vs, int max, long left, long right, long rightM) {
        final int idx = Arrays.binarySearch(vs, max);
        return left * rightM * vs.length + (idx < 0 ? (-idx - 1) * rightM : (idx * rightM + right + 1));
    }
}