class Solution {

    public boolean canMakeSubsequence(String str1, String str2) {
        final int len1 = str1.length();
        final int len2 = str2.length();
        if (len1 < len2)
            return false;

        int i = 0;
        int j = 0;
        char ch1;
        char ch2 = str2.charAt(j);
        while (i < len1 && j < len2) {
            ch1 = str1.charAt(i);
            if ((ch2 - ch1 + 26) % 26 < 2) {
                j++;
                if (j == len2) {
                    return true;
                }
                ch2 = str2.charAt(j);
            }
            i++;
        }
        return false;
    }
}