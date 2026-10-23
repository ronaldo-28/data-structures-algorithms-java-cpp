class Solution {
    private static final int MOD = 1_000_000_007;

    public int possibleStringCount(String word, int k) {
        int n = word.length();
        int count = 0;
        List<Integer> freq = new ArrayList<>();
        long ans = 1;

        for (int i = 0; i < n; i++) {
            count++;
            if (i == n - 1 || word.charAt(i) != word.charAt(i + 1)) {
                // if cnt = 1， must choose
                if (count > 1) {
                    if (k > 0) { // O(k)
                        freq.add(count - 1);
                    }
                    ans = ans * count % MOD;
                }
                k--;
                count = 0;
            }
        }

        if (k <= 0) {
            return (int) ans;
        }

        int[] f = new int[k];
        Arrays.fill(f, 1);

        for (int c : freq) {
            // f presum
            for (int j = 1; j < k; j++) {
                f[j] = (f[j] + f[j - 1]) % MOD;
            }
            // subSum
            for (int j = k - 1; j > c; j--) {
                f[j] = (f[j] - f[j - c - 1]) % MOD;
            }
        }

        return (int) ((ans - f[k - 1] + MOD) % MOD);
    }
}