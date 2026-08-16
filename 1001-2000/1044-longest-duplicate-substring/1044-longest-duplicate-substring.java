class Solution {
    public String longestDupSubstring(String S) {
        char[] chrs = S.toCharArray();
        int[] suff = manberMyers(chrs);
        int[] LCP = kasai(chrs, suff);
        int max = 0;
        for (int i = 1; i < chrs.length - 1; i++) {
            if (LCP[i] > LCP[max])
                max = i;
        }
        //System.out.println(Arrays.toString(suff));
        return new String(chrs, suff[max], LCP[max]);
    }

    public int[] kasai(char[] chrs, int[] suff) {
        int[] LCP = new int[chrs.length];
        int[] ranks = new int[chrs.length];
        for (int i = 0; i < chrs.length; i++) {
            ranks[suff[i]] = i;
        }
        int k = 0;
        for (int i = 0; i < chrs.length; i++) {
            if (ranks[i] == chrs.length - 1) {
                k = 0;
            } else {
                int j = suff[ranks[i] + 1];
                while (i + k < chrs.length && j + k < chrs.length && chrs[i + k] == chrs[j + k]) {
                    k++;
                }
                LCP[ranks[i]] = k;
                if (k > 0)
                    k--;
            }
        }
        return LCP;
    }

    public int[] manberMyers(char[] chrs) {
        int[] suff = new int[chrs.length];
        for (int i = 0; i < chrs.length; i++) {
            suff[i] = i;
        }
        int[] order = new int[chrs.length];
        for (int i = 0; i < chrs.length; i++) {
            order[i] = chrs[i] - 'a';
        }
        for (int i = 1; i < chrs.length; i *= 2) {
            int[] counts1 = new int[Math.max(chrs.length, 26) + 1];
            for (int j = 0; j < chrs.length; j++) {
                int idx = suff[j] + i < chrs.length ? order[suff[j] + i] + 1 : 0;
                counts1[idx]++;
            }
            for (int j = 1; j < counts1.length; j++) {
                counts1[j] += counts1[j - 1];
            }
            int[] suffNext = new int[chrs.length];
            for (int j = chrs.length - 1; j >= 0; j--) {
                int idx = suff[j] + i < chrs.length ? order[suff[j] + i] + 1 : 0;
                suffNext[--counts1[idx]] = suff[j];
            }
            int[] counts = new int[Math.max(chrs.length, 26)];
            for (int idx : order) {
                counts[idx]++;
            }
            for (int j = 1; j < counts.length; j++) {
                counts[j] += counts[j - 1];
            }
            for (int j = chrs.length - 1; j >= 0; j--) {
                suff[--counts[order[suffNext[j]]]] = suffNext[j];
            }
            int[] orderNext = new int[chrs.length];
            orderNext[suff[0]] = 0;
            int idx = 0;
            for (int j = 1; j < chrs.length; j++) {
                if (order[suff[j]] != order[suff[j - 1]] || ((suff[j] + i < chrs.length ? order[suff[j] + i]
                        : -1) != (suff[j - 1] + i < chrs.length ? order[suff[j - 1] + i] : -1))) {
                    idx++;
                }
                orderNext[suff[j]] = idx;
            }
            order = orderNext;
            if (idx == chrs.length - 1)
                break;
        }
        return suff;
    }
}