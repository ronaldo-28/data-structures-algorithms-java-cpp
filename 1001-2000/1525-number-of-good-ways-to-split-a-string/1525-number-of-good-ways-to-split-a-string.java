class Solution {
    public int numSplits(String s) {
        int[] leftFrequency = new int[26];
        int[] rightFrequency = new int[26];
        int distinctLeftChars = 0;
        int distinctRightChars = 0;
        
        char[] characters = s.toCharArray();

        for (char character : characters) {
            if (rightFrequency[character - 'a']++ == 0) {
                distinctRightChars++;
            }
        }

        int validSplitsCount = 0;
        for (char character : characters) {
            int charIndex = character - 'a';
            
            if (leftFrequency[charIndex]++ == 0) {
                distinctLeftChars++;
            }
            
            if (--rightFrequency[charIndex] == 0) {
                distinctRightChars--;
            }

            if (distinctLeftChars == distinctRightChars) {
                validSplitsCount++;
            }
        }

        return validSplitsCount;
    }
}