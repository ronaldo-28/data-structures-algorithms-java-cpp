class Solution {
    public int longestContinuousSubstring(String s) {
        char[] letters = s.toCharArray();
        int current = 1;
        int result = 0;

        for (int i = 1; i < letters.length; i++) {
            if (letters[i] - letters[i - 1] == 1) {
                current++;
                result = Math.max(current, result);
            } else {
                current = 1;
            }
        }

        return Math.max(current, result);
    }
}