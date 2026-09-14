class Solution {
    public String oddString(String[] words) {
        int[] diff0 = getDiff(words[0]);
        int[] diff1 = getDiff(words[1]);

        if (same(diff0, diff1)) {
            for (int i = 2; i < words.length; i++) {
                if (!same(diff0, getDiff(words[i]))) {
                    return words[i];
                }
            }
            return "";
        } else {
            int[] diff2 = getDiff(words[2]);

            if (same(diff0, diff2)) {
                return words[1];
            } else {
                return words[0];
            }
        }
    }

    private int[] getDiff(String word) {
        int[] diff = new int[word.length() - 1];

        for (int i = 1; i < word.length(); i++) {
            diff[i - 1] = word.charAt(i) - word.charAt(i - 1);
        }

        return diff;
    }

    private boolean same(int[] a, int[] b) {
        if (a.length != b.length) return false;

        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) return false;
        }

        return true;
    }
}