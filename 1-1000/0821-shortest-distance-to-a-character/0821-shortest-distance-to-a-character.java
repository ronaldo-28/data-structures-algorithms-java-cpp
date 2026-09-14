class Solution {
    public int[] shortestToChar(String s, char c) {
        int[] distances = new int[s.length()];
        char[] chars = s.toCharArray();
        int i = 0;
        while (i < chars.length && chars[i] != c) {
            i++;
        }
        int prevChar = i;
        for (int j = 0 ; j < i ; j++) {
            distances[j] = prevChar - j;
        }
        i++;
        while (i < chars.length) {
            distances[i] = i - prevChar;
            if (chars[i] == c) {
                int distanceFromCurrentCToPreviousC = i - prevChar;
                for (int j = i - distanceFromCurrentCToPreviousC / 2 ; j < i ; j++) {
                    distances[j] = i - j;
                }
                prevChar = i;
                distances[i] = 0;
            }
            i++;
        }


        return distances;

    }
}