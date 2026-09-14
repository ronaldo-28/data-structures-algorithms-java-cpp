class Solution {
    public boolean checkDistances(String s, int[] distance) {
        int[] idxs = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int num = c - 'a';
            if (i - idxs[num] != distance[num] && idxs[num] != 0) {
                return false;
            } else {
                idxs[num] = i + 1;
            }
        }

        return true;
    }
}