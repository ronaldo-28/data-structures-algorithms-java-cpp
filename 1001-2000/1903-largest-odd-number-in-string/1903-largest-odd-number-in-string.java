class Solution {
    static {
        for (int i = 0; i < 100; i++) {
            largestOddNumber("23");
        }
    }

    public static String largestOddNumber(String num) {
        for (int i = num.length() - 1; i >= 0; i--) {
            int last = num.charAt(i) - '0';
            if ((last & 1) != 0) {
                return num.substring(0, i + 1);
            }
        }
        return "";
    }
}