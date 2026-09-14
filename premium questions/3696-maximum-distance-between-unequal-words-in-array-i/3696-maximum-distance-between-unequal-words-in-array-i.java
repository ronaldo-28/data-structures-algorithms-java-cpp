class Solution {
    public int maxDistance(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }

        int d = 0;

        for(int i = 0; i < words.length-1; i++) {
            String w = words[i];
            if (!w.equals(words[0])) {
                d = Math.max(d, i + 1);
            }

            if (!w.equals(words[words.length-1])) {
                d = Math.max(d, words.length - i);
            }
        }

        return d;
    }
}