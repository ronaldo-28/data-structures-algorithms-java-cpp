import java.util.Arrays;

class Solution {
    public long[] countOfPairs(int n, int x, int y) {
        if (x > y) {
            return countOfPairs(n, y, x);
        }

        long[] result = new long[n];

        if (y - x <= 1) {
            for (int i = 1; i < n; i++) {
                result[i - 1] = 2 * (n - i);
            }

            return result;
        }

        x--;
        y--;

        int cLen = y - x + 1;
        int m = n - (y - x - 1);

        for (int i = 1; i < m; i++) {
            result[i - 1] = 2 * (m - i);
        }

        for (int i = 0; i <= x; i++) {
            result[i] -= 2;
        }

        for (int i = 0; y + i < n; i++) {
            result[i] -= 2;
        }

        result[0] += 2;

        for (int i = 0; i < (cLen - 1) / 2; i++) {
            result[i] += cLen * 2;
        }

        if (cLen % 2 == 0) {
            result[cLen / 2 - 1] += cLen;

            for (int i = 1; i <= x; i++) {
                result[cLen / 2 - 1 + i] += 2;
            }
            for (int i = 1; i + y < n; i++) {
                result[cLen / 2 - 1 + i] += 2;
            }
        }

        for (int i = 1, j = 1; x > 0 && i < x + (cLen - 1) / 2; i++) {
            result[i] += j * 4;

            if (i < x) {
                j++;
            }
            if (i >= (cLen - 1) / 2) {
                j--;
            }
        }

        for (int i = 1, j = 1; y < n - 1 && i + y < n - 1 + (cLen - 1) / 2; i++) {
            result[i] += j * 4;

            if (y + i < n - 1) {
                j++;
            }
            if (i >= (cLen - 1) / 2) {
                j--;
            }
        }

        return result;
    }
}