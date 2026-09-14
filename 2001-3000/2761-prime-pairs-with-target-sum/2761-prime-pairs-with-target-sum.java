class Solution {
    private static final int[] PRIMES = new int[1_000_001];

    public List<List<Integer>> findPrimePairs(int n) {
        if (PRIMES[4] == 0) {
            for (int i = 2; i * i <= 1_000_000; i++) {
                if (PRIMES[i] == 0) {
                    for (int j = i * i; j <= 1_000_000; j += i) {
                        PRIMES[j] = 1;
                    }
                }
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        if (n > 3 && PRIMES[n - 2] == 0) {
            res.add(Arrays.asList(2, n - 2));
        }
        for (int i = 3, half = n / 2; i <= half; i += 2) {
            if (PRIMES[i] == 0 && PRIMES[n - i] == 0) {
                res.add(List.of(i, n - i));
            }
        }
        return res;
    }
}