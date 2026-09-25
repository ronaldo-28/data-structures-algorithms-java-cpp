class Solution {
    public int countPrefixSuffixPairs(String[] words) {
        int result = 0;
        int n = words.length;
        char[][] arr = new char[n][];
        for (int j = 0; j < n; ++j) {
            arr[j] = words[j].toCharArray();
            char[] t = arr[j];
            for (int i = 0; i < j; ++i) {
                char[] s = arr[i];
                int sLength = s.length;
                int tLength =  t.length;
                if (sLength > tLength) continue;
                int k = 0;
                for (; k < sLength; ++k) {
                    if (s[k] != t[k]) break;
                }
                if (k == sLength) {
                    for (k = 0; k < sLength; ++k) {
                        if (s[k] != t[tLength - sLength + k]) break;
                    }
                    if (k == sLength) ++result;
                }
            }
        }
        return result;
    }
}