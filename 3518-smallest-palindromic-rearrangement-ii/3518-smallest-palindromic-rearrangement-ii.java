class Solution {
    public String smallestPalindrome(String s, int k) {
        int n = s.length();
        int middle = n / 2;
        int[] count = new int[26];
        char[] arr = s.toCharArray();
        for (int i = 0; i < middle; i++) {
            count[arr[i] - 'a']++;
        }

        char[] result = new char[n];
        if (n % 2 == 1) {
            result[middle] = arr[middle];
        }

        int total = 0;
        int start = 25;
        long possibility = 1;
        int[] used = new int[26];
        for (int i = 25; i >= 0 && possibility < k; i--) {
            while (count[i]-- > 0) {
                total++;
                used[i]++;
                start = i;
                possibility = possibility * total / used[i];
                if (possibility >= k) {
                    break;
                }
            }
        }

        if (possibility < k) {
            return "";
        }

        int l = 0, r = n - 1;
        for (int i = 0; i < 26; i++) {
            while (count[i]-- > 0) {
                result[l++] = result[r--] = (char) ('a' + i);
            }
        }

        while (l < r) {
            while (used[start] == 0) {
                start++;
            }
            
            for (int i = start; i < 26; i++) {
                if (used[i] != 0) {
                    long newPossibility = possibility * used[i] / total;
                    if (newPossibility < k) {
                        k -= (int) newPossibility;
                    } else {
                        total--;
                        used[i]--;
                        possibility = newPossibility;
                        result[l++] = result[r--] = (char) ('a' + i);
                        break;
                    }
                }
            }
        }

        return new String(result);
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna