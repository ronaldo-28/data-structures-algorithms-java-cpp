class Solution {
    public String arrangeWords(String text) {
        String[] words = text.split(" ");
        words[0] = words[0].toLowerCase();

        int maxLen = 0;
        for (String w : words) maxLen = Math.max(maxLen, w.length());

        int[] count = new int[maxLen + 1];

        for (String w : words) count[w.length()]++;

        for (int i = 1; i <= maxLen; i++) {
            count[i] += count[i - 1];
        }

        String[] sorted = new String[words.length];

        for (int i = words.length - 1; i >= 0; i--) {
            int len = words[i].length();
            sorted[--count[len]] = words[i];
        }

        StringBuilder sb = new StringBuilder();
        for (String w : sorted) sb.append(w).append(" ");

        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        return sb.toString().trim();
    }
}