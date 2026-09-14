class Solution {
    public boolean wordPatternMatch(String pattern, String s) {
        return match(pattern, s, 0, new String[26]);
    }
    public boolean match(String pattern, String s, int indexOfPattern, String[] map) {
        if (indexOfPattern == pattern.length() && s.length() == 0)
            return true;
        if (indexOfPattern == pattern.length())
            return false;
        int index = pattern.charAt(indexOfPattern) - 'a';
        boolean restMatched = false;
        for (int i = 1; i <= s.length(); i++) {
            String sub = s.substring(0, i);
            if (sub.length() == 0)
                continue;
            if (map[index] != null) {
                if (!map[index].equals(sub)) {
                    continue;
                } else {
                    restMatched = match(pattern, s.substring(i), indexOfPattern + 1, map);
                }
            } else {
                if (containsValue(map, sub))
                    continue;
                else {
                    map[index] = sub;
                    restMatched = match(pattern, s.substring(i), indexOfPattern + 1, map);
                    map[index] = null;
                }
            }
            if (restMatched) {
                return true;
            }
        }
        return false;
    }
    public boolean containsValue(String[] arr, String val) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null && arr[i].equals(val))
                return true;
        }
        return false;
    }
}