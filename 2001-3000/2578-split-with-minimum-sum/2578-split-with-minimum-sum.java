class Solution {
    public int splitNum(int num) {
        int[] freq = new int[10];
        while (num > 0) {
            freq[num % 10]++;
            num /= 10;
        }

        int idx = 0;
        int n1 = 0, n2 = 0;

        while (idx < 10) {
            while (idx < 10 && freq[idx] == 0)
                idx++;
            if (idx < 10) {
                n1 *= 10;
                n1 += idx;
                freq[idx]--;
            }

            while (idx < 10 && freq[idx] == 0)
                idx++;
            if (idx < 10) {
                n2 *= 10;
                n2 += idx;
                freq[idx]--;
            }
        }

        return n1 + n2;
    }
}