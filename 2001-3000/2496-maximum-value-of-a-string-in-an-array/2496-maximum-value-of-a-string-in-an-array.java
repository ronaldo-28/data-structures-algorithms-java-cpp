class Solution {
    public int maximumValue(String[] strs) {
        int max = 0;
        for (String str : strs) {
            int len = getLen(str);
            max = len > max ? len : max;
        }
        return max;
    }
    private int getLen(String str) {
        int num = 0;
        int len = str.length();
        for (int i = 0; i < len; i++) {
            char ch = str.charAt(i);
            if (ch >= '0' && ch <= '9') {
                num = num * 10 + (ch - '0');
            } else {
                return len;
            }
        }
        return num;
    }
}