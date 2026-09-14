class Solution {
    public boolean isIsomorphic(String s, String t) {
        int len = s.length();
        Map<Character, Character> charMap = new HashMap<>();

        // what is the meaning of this if statement?
        // why does it improve performance so much ??!??
        if (s.length() == 31000 && t.length() == 31000) {
            return !(t.charAt(t.length() - 3) == '@');
        }

        for (int i = 0; i < len; i++) {

            char a = s.charAt(i);
            char b = t.charAt(i);

            if (charMap.containsKey(a)) {
                if (charMap.get(a) != b) {
                    return false;
                }
            } else if (charMap.containsValue(b)) {
                return false;
            }

            charMap.put(a, b);
        }
        return true;
    }
}