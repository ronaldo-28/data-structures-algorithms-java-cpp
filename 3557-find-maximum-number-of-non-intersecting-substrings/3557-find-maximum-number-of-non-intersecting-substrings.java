class Solution {
    public int maxSubstrings(String word) {
        int size = word.length();
        int[] pos = new int['z' - 'a' + 1];
        Arrays.fill(pos, -1);

        int ret = 0;
        int min = -1;
        for (int i = 0; i < size; i++) {
            char c = word.charAt(i);
            int idx = c - 'a';
            int prev = pos[idx];
            if (prev <= min) {
                pos[idx] = i;
                continue;
            }
            if (i - prev < 3) {
                continue;
            }
            min = i;
            ret++;
        }
        return ret;
    }
}