class Solution {
    public String[] findWords(String[] words) {
        String row1 = "qwertyuiop";
        String row2 = "asdfghjkl";
        String row3 = "zxcvbnm";

        List<String> result = new ArrayList<>();

        for (String word : words) {
            String w = word.toLowerCase();
            if (inOneRow(w, row1) || inOneRow(w, row2) || inOneRow(w, row3)) {
                result.add(word);
            }
        }

        return result.toArray(new String[0]);
    }

    private boolean inOneRow(String word, String row) {
        for (char c : word.toCharArray()) {
            if (row.indexOf(c) == -1) {
                return false;
            }
        }
        return true;
    }
}