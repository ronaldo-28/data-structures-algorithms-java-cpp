class Solution {
    private static final int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31}, vowels = {1, -1, -1, -1, 1, -1, -1, -1, 1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1};
    public long beautifulSubstrings(String s, int k) {
        int n = s.length(), step = 2 * calcStep(k), bal = 0, len = (int)(Math.log(step) / Math.log(2)) + 1;
        if(step > n) return 0;

        HashMap<Integer, Integer> freq = new HashMap<>(1 << (len + 3));
        freq.put(step - 1, 1);
        long ans = 0;
        for(int i = 0; i < n; i++) {
            bal += vowels[s.charAt(i) - 'a'];
            ans += freq.merge((bal << len) | (i % step), 1, Integer::sum) - 1;
        }
        return ans;
    }
    private static int calcStep(int k) {
        int step = 1;
        for(int i = 0; i < 11 && primes[i] <= k; i++) {
            int count = 0;
            while(k % primes[i] == 0) {
                k /= primes[i];
                step *= primes[i];
                count++;
            }
            if(count % 2 == 1) step *= primes[i];
        }
        step = k * (int)Math.sqrt(step);
        return step;
    }
}