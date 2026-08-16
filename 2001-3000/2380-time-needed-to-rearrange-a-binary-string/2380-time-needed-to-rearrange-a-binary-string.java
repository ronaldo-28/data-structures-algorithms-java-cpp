class Solution {
    public int secondsToRemoveOccurrences(String s) {
        int n = s.length();
        int ans = 0;
        int zero = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '0') {
                zero++;
            } else {
                if(zero==0)
                continue;
                if (ans >= zero) {
                    ans++;
                } else {
                    ans = zero;
                }
            }
        }
        return ans;
    }
}