class Solution {
    public boolean isPossibleToRearrange(String s, String t, int k) {
        int p = s.length() / k;
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i += p) {
            String part = s.substring(i, i + p);
            map.put(part, map.getOrDefault(part, 0) + 1);
        }

        for (int i = 0; i < t.length(); i += p) {
            String part = t.substring(i, i + p);
            Integer cnt = map.get(part);
            if (cnt == null || cnt == 0) return false;
            map.put(part, cnt - 1);
        }

        return true;
    }
}