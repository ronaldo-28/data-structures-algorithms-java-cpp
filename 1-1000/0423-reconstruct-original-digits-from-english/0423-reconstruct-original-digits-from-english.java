class Solution {
    static final int[] DIGS = {0,2,4,6,8,5,7,3,9,1}, CHARS = {25,22,20,23,6,5,18,7,8,14};
    static final int[][] REMS = {{14},{14},{5,14},{18,8},{8,7},{8},{},{},{},{}};
    public String originalDigits(String S) {
        int[] fmap = new int[26], ans = new int[10];
        char[] SCA = S.toCharArray();
        for (char c : SCA) fmap[c - 97]++;
        for (int i = 0; i < 10; i++) {
            int count = fmap[CHARS[i]];
            for (int rem : REMS[i]) fmap[rem] -= count;
            ans[DIGS[i]] = count;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            char c = (char)(i + 48);
            for (int j = 0; j < ans[i]; j++)
                sb.append(c);
        }
        return sb.toString();
    }
}