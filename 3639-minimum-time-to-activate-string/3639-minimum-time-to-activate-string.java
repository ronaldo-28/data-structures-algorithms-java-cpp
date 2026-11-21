class Solution {
    public int minTime(String s, int[] order, int k) {
        // Step 1: Calculate the maximum number of valid substrings.
        int n = s.length();
        long valid = (long) n * (n + 1) / 2;

        if (valid < k)
            return -1;

        // Step 2: Initialize a doubly linked list of starred positions.
        int[] prev = new int[n];
        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            prev[i] = i - 1;
            next[i] = i + 1;
        }

        // Step 3: Reverse the replacement process.
        for (int t = n - 1; t >= 0; t--) {
            int i = order[t];
            int left = prev[i];
            int right = next[i];

            // Step 4: Remove substrings whose only star is at i.
            valid -= (long) (i - left) * (right - i);

            // Step 5: Find the earliest activation time.
            if (valid < k)
                return t;

            // Step 6: Remove i from the linked list.
            if (left >= 0)
                next[left] = right;

            if (right < n)
                prev[right] = left;
        }

        return -1;
    }
}