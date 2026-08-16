class Solution {
    public int numDifferentIntegers(String word) {
       char[] chars = word.toCharArray();
        int n = chars.length;
        int start = 0;
        int end = 0;
        Set<String> set = new HashSet<>();
        boolean foundDigit = chars[0] >= '0' && chars[0] <= '9';
        boolean isDigit = false;

        for (int i = 0; i < n; i++) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                if (!isDigit && chars[i] == '0') {
                    start = i + 1;
                } else {
                    isDigit = true;
                }
                end = i + 1;
                foundDigit = true;
            } else {
                if (foundDigit) {
                    if (start == end) {
                        set.add("0");
                    } else {
                        set.add(word.substring(start, end));
                    }
                    foundDigit = false;
                }
                isDigit = false;
                start = i + 1;
            }
        }
        if (start == end) {
            set.add("0");
        } else if (start < end) {
            set.add(word.substring(start, end));
        }
        return set.size();
    }
}