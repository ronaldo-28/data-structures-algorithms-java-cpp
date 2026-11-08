class Solution {
    int panlindromeSize(String s, int start, int end) {
        int n = s.length();

        if(start != -1) {
            for(int i = n - 1; i >= start; i--) {
                // possible palindrome
                if(s.charAt(i) == s.charAt(start)) {
                    int x = start, y = i;
                    while(x <= y && s.charAt(x) == s.charAt(y)) {
                        ++x;
                        --y;
                    }

                    // palindrome found
                    if(x > y) {
                        return i - start + 1;
                    }
                }
            }
        }
        else {
            for(int i = 0; i <= end; i++) {
                // possible palindrome
                if(s.charAt(i) == s.charAt(end)) {
                    int x = i, y = end;
                    while(x <= y && s.charAt(x) == s.charAt(y)) {
                        ++x;
                        --y;
                    }

                    // palindrome found
                    if(x > y) {
                        return end - i + 1;
                    }
                }
            }
        }

        return 0;
    }
    public int longestPalindrome(String s, String t) {
        int n = s.length(), m = t.length();

        int ans = 0;
        for(int i = 0; i < n; i++) {
            for(int j = m - 1; j >= 0; j--) {
                // possible concatenation
                if(s.charAt(i) == t.charAt(j)) {
                    int x = i, y = j;
                    while(x < n && y >= 0 && s.charAt(x) == t.charAt(y)) {
                        ++x;
                        --y;
                    }

                    int a = panlindromeSize(s, x, -1);
                    int b = panlindromeSize(t, -1, y);

                    ans = Math.max(Math.max(a, b) + (x - i) + (j - y), ans);
                }
            }
        }
        for(int i = 0; i < n; i++) {
            ans = Math.max(ans, panlindromeSize(s, i, -1));
        }

        for(int i = 0; i < m; i++) {
            ans = Math.max(ans, panlindromeSize(t, -1, i));
        }

        return ans;
    }
}