class Solution {
    public int partitionString(String s) {
        int stringLength = s.length();
        int bitmask = 0;
        int numParts = 1;
        byte[] characters = new byte[stringLength];
        s.getBytes(0, stringLength, characters, 0);

        for (byte currentChar : characters) {
            int charMask = 1 << currentChar;

            if ((bitmask & charMask) != 0) {
                ++numParts;
                bitmask = charMask;
            } else {
                bitmask |= charMask;
            }
        }

        return numParts;
    }
}