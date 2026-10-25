class Solution {
    public long countMagicalPairs(String s) {
        int n = s.length();

        int[] right = new int[n * 27];
        int[] next = new int[26];

        Arrays.fill(next, n);

        int[] pos = new int[26];

        for (int i = n - 2; i >= 0; i--) {
            next[s.charAt(i + 1) - 'a'] = i + 1;

            int count = 0;
            for (int c = 0; c < 26; c++) {
                if (next[c] < n) {
                    pos[count++] = next[c];
                }
            }

            Arrays.sort(pos, 0, count);

            for (int k = 0; k < count; k++) {
                int nextPos = (k + 1 < count) ? pos[k + 1] : n;
                right[i * 27 + k + 1] = nextPos - pos[k];
            }
        }

        long res = 0;
        int[] last = new int[26];
        Arrays.fill(last, -1);

        for (int i = 0; i < n - 1; i++) {
            last[s.charAt(i) - 'a'] = i;

            int count = 0;
            for (int c = 0; c < 26; c++) {
                if (last[c] >= 0) {
                    pos[count++] = last[c];
                }
            }

            Arrays.sort(pos, 0, count);

            for (int k = 1; k <= count; k++) {
                int currPos = pos[count - k];
                int prevPos = (k < count) ? pos[count - k - 1] : -1;

                long leftCount = currPos - prevPos;
                res += leftCount * right[i * 27 + k];
            }
        }

        return res;
    }
}