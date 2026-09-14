class Solution {
    public long maxProduct(String s) {
        char[] chrs = s.toCharArray();
        int[] LPS = new int[chrs.length];
        int center = 0;
        int lim = 0;
        for (int i = 0; i < chrs.length; i++) {
            if (lim > i) {
                LPS[i] = Math.min(LPS[2 * center - i], lim - i);
            }
            while (i - LPS[i] - 1 >= 0 && i + LPS[i] + 1 < chrs.length
                    && chrs[i - LPS[i] - 1] == chrs[i + LPS[i] + 1]) {
                LPS[i]++;
            }
            if (i + LPS[i] > lim) {
                center = i;
                lim = i + LPS[i];
            }
        }
        int[] ranges1 = new int[chrs.length];
        int[] ranges2 = new int[chrs.length];
        for (int i = 0; i < chrs.length; i++) {
            ranges1[i - LPS[i]] = LPS[i];
            if (LPS[i] > ranges2[i + LPS[i]])
                ranges2[i + LPS[i]] = LPS[i];
        }
        int[] rights = new int[ranges1.length + 1];
        int max = 0;
        for (int i = 0; i < ranges1.length; i++) {
            max--;
            if (ranges1[i] > max)
                max = ranges1[i];
            rights[i] = 2 * max + 1;
        }
        long res = 0;
        max = 0;
        int maxR = 0;
        for (int i = ranges1.length - 1; i >= 0; i--) {
            max--;
            if (ranges2[i]> max)
                max = ranges2[i];
            if (rights[i + 1] > maxR)
                maxR = rights[i + 1];
            res = Math.max((long) maxR * (2 * max + 1), res);
        }
        return res;
    }
}