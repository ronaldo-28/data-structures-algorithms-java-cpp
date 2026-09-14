class Solution {
    public int maxSubstringLength(String s) {
        int n = s.length();

        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, -1);

        // Record first and last occurrence.
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';

            if (first[c] == -1) {
                first[c] = i;
            }

            last[c] = i;
        }

        int ans = -1;

        // Try each character's first occurrence as left boundary.
        for (int c = 0; c < 26; c++) {
            int left = first[c];

            if (left == -1) {
                continue;
            }

            // At minimum, we must include all occurrences of c.
            int right = last[c];

            // Important:
            // Don't stop at "right".
            // We may continue extending and find a longer valid substring.
            for (int j = left; j < n; j++) {
                int curr = s.charAt(j) - 'a';

                // curr occurs before our left boundary.
                // Therefore curr would exist both inside and outside.
                if (first[curr] < left) {
                    break;
                }

                // Since curr is included, all curr occurrences
                // must also be included.
                right = Math.max(right, last[curr]);

                // We've now included all occurrences of every
                // character encountered so far.
                if (j == right) {

                    // Entire string isn't allowed.
                    if (j - left + 1 < n) {
                        ans = Math.max(ans, j - left + 1);
                    }
                }
            }
        }

        return ans;
    }
}