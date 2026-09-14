class Solution {
    private long base = 26;
    private long MOD = (long) 1e9+7;

    public int longestRepeatingSubstring(String s) {
        int n = s.length();
        int l = 0, r = n - 1;
        int ans = 0;
        while(l <= r) {
            int mid = l + (r - l) / 2;
            if(hasRepeatingSubstring(s, mid)) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return ans;
    }

    public boolean hasRepeatingSubstring(String s, int len) {
        int n = s.length();
        long baseL = 1;
        long currHash = 0;
        for(int i = 0; i < len; i++) {
            baseL = (baseL * base) % MOD;
            currHash = (currHash * base + (s.charAt(i) - 'a')) % MOD;
        }

        Set<Long> seenHashes = new HashSet<>();
        seenHashes.add(currHash);
        for(int i = 1; i <= n - len; i++) {
            currHash = (currHash * base - (s.charAt(i - 1) - 'a') * baseL % MOD + MOD) % MOD;
            currHash = (currHash + (s.charAt(i + len - 1) - 'a')) % MOD;

            if(seenHashes.contains(currHash)) return true;
            seenHashes.add(currHash);
        }
        return false;
    }

}