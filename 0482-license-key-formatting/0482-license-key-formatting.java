class Solution {
    static{
        String a = "a";
        for (int i = 0 ; i < 500 ; i++)
            licenseKeyFormatting(a, 1);
    }

    public static String licenseKeyFormatting(String s, int k) {
        int sLen = s.length(), count = 0, j = 0;
        char[] letters = new char[sLen + sLen / k];
        char c;

        for (int i = 0; i < sLen; i++) {
            c = s.charAt(i);
            if('a' <= c && c <= 'z') c -= 32;
            if (c != '-') 
                letters[j++] = c;
            else count++; 
        }
        j--;
        if(j < 0) return "";

        int charsCount = sLen - count, remains = charsCount % k, curI = 0, resSize = charsCount + charsCount / k;
        if (remains == 0) resSize--;

        int write = resSize - 1;
        int groupCounter = 0;

        while (j >= 0) {
            if (groupCounter == k) {
                letters[write--] = '-';
                groupCounter = 0;
            } 
            else {
                letters[write--] = letters[j--];
                groupCounter++;
            }
        }

        return new String(letters, write + 1, resSize - write - 1);
    }


}