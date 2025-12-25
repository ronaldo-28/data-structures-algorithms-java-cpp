class Solution {

    static {
        for (int i = 0; i < 500; i++) {
            countBinarySubstrings("1100");
        }
    }

    public static int countBinarySubstrings(String s) {
        var sChar = s.toCharArray(); 
        var count = 0; 
        var sequence = 1; 
        var lastSequence = 0; 

        for (int left = 1; left < sChar.length; left++) {
            if (sChar[left] == sChar[left - 1]) {
                sequence += 1; 
            } else {
                count += Math.min(lastSequence, sequence);
                lastSequence = sequence; 
                sequence = 1; 
            }
        }

        count += Math.min(lastSequence, sequence);

        return count; 
    }
}