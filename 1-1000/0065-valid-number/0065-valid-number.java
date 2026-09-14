class Solution {
    public boolean isNumber(String s) {
        boolean numSeen = false;
        boolean dotSeen = false;
        boolean eSeen = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                numSeen = true;
            } else if (c == '.') {
                // A dot can only appear once and it cannot appear after an 'e' or 'E'
                if (dotSeen || eSeen) {
                    return false;
                }
                dotSeen = true;
            } else if (c == 'e' || c == 'E') {
                // 'e' or 'E' can only appear once and it must be after a number
                if (eSeen || !numSeen) {
                    return false;
                }
                eSeen = true;
                numSeen = false; // Reset numSeen for the exponent part
            } else if (c == '+' || c == '-') {
                // A sign can appear at the beginning or after an 'e' or 'E'
                if (i != 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') {
                    return false;
                }
            } else {
                // Any other character is invalid
                return false;
            }
        }

        return numSeen;
    }
}
