class Solution {
    public int numDecodings(String s) {
        long dp0 = 1, dp1, dp2 = 0;
        char prev = s.charAt(0), curr;
        if (prev == '0') {
            return 0;
        }
        int len = s.length();
        dp1 = Character.isDigit(prev) ? 1 : 9;
        for (int i = 1; i < len; i++) {
            curr = s.charAt(i);
            if (curr == '*') {
                if (prev == '*') {
                    dp2 = (dp1 * 9 + dp0 * 15) % 1000000007;
                } else if (prev == '1') {
                    dp2 = (dp1 * 9 + dp0 * 9) % 1000000007;
                } else if (prev == '2') {
                    dp2 = (dp1 * 9 + dp0 * 6) % 1000000007;
                } else {
                    dp2 = (dp1 * 9) % 1000000007;
                }
            } else if (curr == '0') {
                if (prev == '*') {
                    dp2 = (dp0 * 2) % 1000000007;
                } else if (prev == '0' || prev > '2') {
                    return 0;
                } else {
                    dp2 = dp0;
                }
            } else if (curr < '7') {
                if (prev == '*') {
                    dp2 = (dp1 + dp0 * 2) % 1000000007;
                } else if ('0' < prev && prev < '3') {
                    dp2 = (dp0 + dp1) % 1000000007;
                } else {
                    dp2 = dp1;
                }
            } else { // curr >= '7'
                if (prev == '*' || prev == '1') {
                    dp2 = (dp1 + dp0) % 1000000007;
                } else {
                    dp2 = dp1;
                }
            }
            dp0 = dp1;
            dp1 = dp2;
            prev = curr;
        }
        return (int) dp1;
    }
}