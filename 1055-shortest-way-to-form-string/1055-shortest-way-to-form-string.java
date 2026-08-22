class Solution {
    /*
        loop through each char in target,
        keep a pointer in source which points to the previous index that match the char in target. advance the pointer to the end then the beginning to see if there is a match
    
    Test: 
    source = "abc", target = "abcbc"
     "abc"
    */
    public int shortestWay(String source, String target) {
        // if (source.length() > target.length()) return -1;
        int n = source.length();
        int loop = 1;
        int ptr = 0;
        for (char c : target.toCharArray()) {
            int nextPtr = source.indexOf(c, ptr);
            if (nextPtr == -1) {
                int anotherPtr = source.indexOf(c, 0);
                if (anotherPtr > -1 && anotherPtr < ptr) {
                    loop++;
                    ptr = anotherPtr + 1;
                } else {
                    return -1;
                }
            } else {
                ptr = nextPtr + 1;
            }
        }
        
        return loop;
    }
}