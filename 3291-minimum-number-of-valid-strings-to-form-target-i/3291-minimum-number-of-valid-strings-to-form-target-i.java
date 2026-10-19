class Solution {
    public int minValidStrings(String[] words, String target) {
        char[] chrs = target.toCharArray();
        int[][] lens = new int[words.length][];
        for (int i = 0; i < words.length; i++) {
            lens[i] = getLPS(chrs, words[i]);
        }
        int lim = 0;
        int res = 0;
        int idx = 0;
        while (idx < chrs.length) {
            int limNext = -1;
            while (idx <= lim) {
                for (int i = 0; i < lens.length; i++) {
                    limNext = Math.max(lens[i][idx], limNext);
                }
                idx++;
            }
            if (limNext < idx)
                return -1;
            res++;
            if (limNext == chrs.length)
                return res;
            lim = limNext;
        }
        return res;
    }

    public int[] getLPS(char[] chrs, String word) {
        char[] chrsWord = word.toCharArray();
        int[] LPS = getLPS(chrsWord);
        int[] LPSChrs = new int[chrs.length];
        int len = 0;
        int idx = 0;
        while (idx < chrs.length) {
            if (chrs[idx] == chrsWord[len]) {
                len++;
                LPSChrs[idx] = len;
                idx++;
                if (len == chrsWord.length)
                    len = LPS[len - 1];
            } else {
                if (len > 0) {
                    len = LPS[len - 1];
                } else {
                    idx++;
                }
            }
        }
        int[] res = new int[chrs.length];
        for (int i = 0; i < chrs.length; i++) {
            if (LPSChrs[i] > 0)
                res[i - LPSChrs[i] + 1] = i + 1;
        }
        return res;
    }

    public int[] getLPS(char[] chrs) {
        int[] LPS = new int[chrs.length];
        int len = 0;
        int idx = 1;
        while (idx < chrs.length) {
            if (chrs[len] == chrs[idx]) {
                len++;
                LPS[idx] = len;
                idx++;
            } else {
                if (len > 0) {
                    len = LPS[len - 1];
                } else {
                    idx++;
                }
            }
        }
        return LPS;
    }

}