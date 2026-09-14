class Solution {
    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        char[] char1 = s1.toCharArray();
        char[] char2 = s2.toCharArray();
        int[] higherToLowerEquivalentChar = new int[26];
        for (int i = 0; i < 26; i++) {
            higherToLowerEquivalentChar[i] = i;
        }
        for (int i = 0; i < char1.length; i++) {
            if (char1[i] == char2[i]) {
                // no point in putting an entry for ex - [a, a] as this will just run into infinite loop.
                continue;
            }
            char min = '\0';
            char max = '\0';
            if (char1[i] > char2[i]) {
                max = char1[i];
                min = char2[i];
            } else if (char2[i] > char1[i]) {
                max = char2[i];
                min = char1[i];
            }

            int maxParent = higherToLowerEquivalentChar[max - 'a'];
            while (higherToLowerEquivalentChar[maxParent] != maxParent) {
                maxParent = higherToLowerEquivalentChar[maxParent];
            }

            int minParent = higherToLowerEquivalentChar[min - 'a'];
            while (higherToLowerEquivalentChar[minParent] != minParent) {
                minParent = higherToLowerEquivalentChar[minParent];
            }
            if (maxParent > minParent) {
                higherToLowerEquivalentChar[maxParent] = minParent;
            } else {
                higherToLowerEquivalentChar[minParent] = maxParent;
            }
        }
        char[] input = baseStr.toCharArray();
        char[] result = new char[input.length];
        for (int i = 0; i < input.length; i++) {
            char c = input[i];
            int parent = higherToLowerEquivalentChar[c - 'a'];
            while (higherToLowerEquivalentChar[parent] != parent) {
                parent = higherToLowerEquivalentChar[parent];
            }
            result[i] = (char) (parent + 'a');
        }
        return new String(result);
    }
}