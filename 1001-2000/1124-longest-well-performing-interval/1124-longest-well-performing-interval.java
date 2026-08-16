class Solution {
    public int longestWPI(int[] hours) {
         int n = hours.length;
        int offset = n;
        int size = 2 * n + 1;

        int[] firstIndex = new int[size];

        // Initialize with -2 (unseen)
        for (int i = 0; i < size; i++) {
            firstIndex[i] = -2;
        }

        int prefixSum = 0;
        int maxLen = 0;

        // prefixSum 0 occurs at index -1
        firstIndex[offset] = -1;

        for (int i = 0; i < n; i++) {

            // Convert to +1 / -1
            if (hours[i] > 8)
                prefixSum += 1;
            else
                prefixSum -= 1;

            // Case 1: Entire interval [0..i] positive
            if (prefixSum > 0) {
                maxLen = i + 1;
            } 
            else {
                // Case 2: Find prefixSum - 1
                int target = prefixSum - 1;
                int idx = target + offset;

                if (idx >= 0 && idx < size && firstIndex[idx] != -2) {
                    maxLen = Math.max(maxLen, i - firstIndex[idx]);
                }
            }

            // Store first occurrence
            int pos = prefixSum + offset;
            if (firstIndex[pos] == -2) {
                firstIndex[pos] = i;
            }
        }

        return maxLen;
    }
}