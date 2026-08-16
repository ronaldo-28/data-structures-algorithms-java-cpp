class Solution {
    public String robotWithString(String s) {
        int n = s.length();
        int[] counts = new int[26];
        for (int i = 0; i < n; i++) {
            counts[s.charAt(i) - 'a']++;
        }

        char[] stack = new char[n];
        int stackTop = -1;
        char[] result = new char[n];
        int resultTop = -1;

        int currentCharIndex = 0;
        while (currentCharIndex < 26 && counts[currentCharIndex] == 0) {
            currentCharIndex++;
        }

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int left = --counts[c - 'a'];

            if (c == currentCharIndex + 'a') {
                result[++resultTop] = c;

                if (left == 0) {
                    while (currentCharIndex < 26 && counts[currentCharIndex] == 0) {
                        currentCharIndex++;
                    }
                    while (stackTop >= 0 && stack[stackTop] <= currentCharIndex + 'a') {
                        result[++resultTop] = stack[stackTop--];
                    }
                }
            } else {
                stack[++stackTop] = c;
            }
        }

        while (stackTop >= 0) {
            result[++resultTop] = stack[stackTop--];
        }

        return new String(result, 0, resultTop + 1);
    }
}