class Solution {
    public int maxDiff(int num) {
        int mag = 1;
        int n = num;
        while (n >= 10) {
            n /= 10;
            ++mag;
        }

        int maxChanger = -1;
        int minChanger = -1;
        boolean leadingMinChanger = true;
        int max = 0;
        int min = 0;
        for (int i = 0; i < mag; ++i) {
            int digit = (int)(num / Math.pow(10, mag - i - 1)) % 10;

            max *= 10;
            min *= 10;

            if (maxChanger == -1) {
                if (digit != 9) {
                    maxChanger = digit;
                }
            }
            
            max += digit == maxChanger ? 9 : digit;

            if (minChanger == -1) {
                if (digit != 1 && digit != 0) {
                    minChanger = digit;
                } else {
                    leadingMinChanger = false;
                }
            }

            min += digit == minChanger ? (leadingMinChanger ? 1 : 0) : digit;
        }



        return max - min;
    }
}