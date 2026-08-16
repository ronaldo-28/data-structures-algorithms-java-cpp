class Solution {
    public boolean strongPasswordCheckerII(String password) {

        int len = password.length();

        if (password.length() < 8) {
            return false;
        }

        boolean isLowPresent = false;
        boolean isUpPresent = false;
        boolean isDigitPresent = false;
        boolean isSpecPresent = false;

        for (int i = 0; i < len; i++) {
            char ch = password.charAt(i);

            if (i != len - 1 && ch == password.charAt(i + 1)) {
                return false;
            } else if (ch >= 'a' && ch <= 'z') {
                isLowPresent = true;
            } else if (ch >= 'A' && ch <= 'Z') {
                isUpPresent = true;
            } else if (ch >= '0' && ch <= '9') {
                isDigitPresent = true;
            } else if ("!@#$%^&*()-+".indexOf(ch) != -1) {
                isSpecPresent = true;
            }
        }

        return isLowPresent && isUpPresent && isDigitPresent && isSpecPresent;
    }
}