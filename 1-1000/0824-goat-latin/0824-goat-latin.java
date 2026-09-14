class Solution {
    public String toGoatLatin(String sentence) {
        String[] words = sentence.split(" ");
        String vowels = "aeiouAEIOU";
        StringBuilder sb = new StringBuilder();
        int idx = 1;

        for (String w : words) {
            if (vowels.indexOf(w.charAt(0)) != -1) {
                sb.append(w);
            } else {
                sb.append(w.substring(1)).append(w.charAt(0));
            }

            sb.append("ma");
            sb.append("a".repeat(idx));
            sb.append(" ");
            idx++;
        }

        return sb.toString().trim();
    }
}