public class Solution {
    public int lengthLongestPath(String input) {
        if (input == null || input.length() == 0) {
            return 0;
        }
        int maxLen = 0;
        String[] lines = input.split("\n");
        int numLines = lines.length;
        Stack<Integer> prevLengths = new Stack<>();
        int currLength = 0;
        int currIndent = -1;
        for (int index = 0; index < numLines; index++) {
            int indent = getIndent(lines[index]);
            String name = lines[index].substring(indent, lines[index].length());
            if (indent > currIndent) {
                int nameLen = name.length();
                if (name.contains(".")) { // file
                    maxLen = Math.max(maxLen, currLength + nameLen);
                } else {
                    currLength += nameLen + 1;
                    prevLengths.push(nameLen + 1);
                    currIndent++;
                }
            } else {
                currLength -= prevLengths.pop();
                currIndent--;
                index--;
            }
        }
        return maxLen;
    }
    
    private int getIndent(String line) {
        int index = 0;
        int len = line.length();
        while (index < len && line.charAt(index) == '\t') {
            index++;
        }
        return index;
    }
}