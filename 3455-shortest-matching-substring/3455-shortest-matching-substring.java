class Solution {
    public int shortestMatchingSubstring(String s, String p) {
        int first = p.indexOf('*');
        int second = p.indexOf('*', first + 1);

        String a = p.substring(0, first);
        String b = p.substring(first + 1, second);
        String c = p.substring(second + 1);

        int[] A = kmp(s, a);
        int[] B = kmp(s, b);
        int[] C = kmp(s, c);

        // ** 
        if (a.isEmpty() && b.isEmpty() && c.isEmpty())
            return 0;

        // a* c
        if (b.isEmpty()) {
            if (a.isEmpty()) {
                if (c.isEmpty())
                    return 0;

                return C.length > 0 ? c.length() : -1;
            }

            if (c.isEmpty())
                return A.length > 0 ? a.length() : -1;

            int j = 0;
            int ans = Integer.MAX_VALUE;

            for (int x : A) {
                int endA = x + a.length();

                while (j < C.length && C[j] < endA)
                    j++;

                if (j == C.length)
                    break;

                ans = Math.min(ans, C[j] + c.length() - x);
            }

            return ans == Integer.MAX_VALUE ? -1 : ans;
        }

        // * b *
        if (a.isEmpty()) {
            if (c.isEmpty())
                return B.length > 0 ? b.length() : -1;

            int j = 0;
            int ans = Integer.MAX_VALUE;

            for (int x : B) {
                int endB = x + b.length();

                while (j < C.length && C[j] < endB)
                    j++;

                if (j == C.length)
                    break;

                ans = Math.min(ans, C[j] + c.length() - x);
            }

            return ans == Integer.MAX_VALUE ? -1 : ans;
        }

        // a * b * c
        if (c.isEmpty()) {
            int j = 0;
            int ans = Integer.MAX_VALUE;

            for (int x : A) {
                int endA = x + a.length();

                while (j < B.length && B[j] < endA)
                    j++;

                if (j == B.length)
                    break;

                ans = Math.min(ans, B[j] + b.length() - x);
            }

            return ans == Integer.MAX_VALUE ? -1 : ans;
        }

        // All three parts are non-empty
        int j = 0;
        int k = 0;
        int ans = Integer.MAX_VALUE;

        for (int x : A) {
            int endA = x + a.length();

            while (j < B.length && B[j] < endA)
                j++;

            if (j == B.length)
                break;

            int endB = B[j] + b.length();

            while (k < C.length && C[k] < endB)
                k++;

            if (k == C.length)
                break;

            int end = C[k] + c.length();

            ans = Math.min(ans, end - x);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private int[] kmp(String s, String p) {
        if (p.isEmpty())
            return new int[0];

        int m = p.length();
        int[] lps = new int[m];

        for (int i = 1, j = 0; i < m; ) {
            if (p.charAt(i) == p.charAt(j)) {
                lps[i++] = ++j;
            } else if (j > 0) {
                j = lps[j - 1];
            } else {
                i++;
            }
        }

        int[] temp = new int[s.length()];
        int count = 0;

        for (int i = 0, j = 0; i < s.length(); i++) {
            while (j > 0 && s.charAt(i) != p.charAt(j))
                j = lps[j - 1];

            if (s.charAt(i) == p.charAt(j))
                j++;

            if (j == m) {
                temp[count++] = i - m + 1;
                j = lps[j - 1];
            }
        }

        return java.util.Arrays.copyOf(temp, count);
    }
}