class Solution {
    public int countSymmetricIntegers(int low, int high) {
        return count2(low, high) + count4(low, high);
    }

    public int count2(int low, int high) {
        if (high < 11) return 0;
        if (low > 99) return 0;

        low = Math.max(low, 11);
        high = Math.min(high, 99);

        int start = low / 10;
        int end = high / 10;
        int num;
        int res = 0;

        for (int d = start; d <= end; d++) {
            num = d * 10 + d;
            if (num >= low && num <= high) res++;
        }

        return res;
    }

    public int count4(int low, int high) {
        if (high < 1001) return 0;
        if (low > 9999) return 0;

        low = Math.max(low, 1001);
        high = Math.min(high, 9999);

        int start = low / 100;
        int end = high / 100;
        int target;
        int d4;
        int num;
        int res = 0;

        for (int left = start; left <= end; left++) {
            target = left / 10 + left % 10;

            for (int d3 = 0; d3 <= 9; d3++) {
                d4 = target - d3;

                if (d4 >= 0 && d4 <= 9) {
                    num = left * 100 + d3 * 10 + d4;
                    if (num >= low && num <= high) res++;
                }
            }
        }

        return res;
    }
}