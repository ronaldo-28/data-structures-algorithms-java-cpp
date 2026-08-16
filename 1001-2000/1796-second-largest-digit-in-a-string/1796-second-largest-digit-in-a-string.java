class Solution {
    public int secondHighest(String s) {
        int count = 1;
        for(char c = '9'; c >= '0'; c--) {
            if(s.indexOf(c) != -1) {
                if(count-- == 0) {
                    return c - '0';
                }
            }
        }

        return -1;
    }
}