class Solution {
    public int numberOfSpecialSubstrings(String s) {
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        int left = 0;
        int total = 0;

        for (int right = 0; right < s.length(); right++) {
            int idx = s.charAt(right) - 'a';

            left = Math.max(left, lastSeen[idx] + 1);
            total += right - left + 1;
            lastSeen[idx] = right;
        }
        return total;
    }
}