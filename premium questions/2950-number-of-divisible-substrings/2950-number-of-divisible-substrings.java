class Solution {
    public int countDivisibleSubstrings(String word) {
        int r = 0;
        for (int i = 1; i < 10; ++i) {
            Map<Integer, Integer> have = new HashMap<>();
            have.put(0, 1);
            int s = 0;
            for (char c : word.toCharArray()) {
                s += 9 - ('z' - c) / 3 - i;
                int m = have.getOrDefault(s, 0);
                r += m++;
                have.put(s, m);
            }
        }
        return r;
    }
}