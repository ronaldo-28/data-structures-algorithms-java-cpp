class Solution {
    public boolean isDecomposable(String s) {
        int len = 3;
        char prev = 'A';
        boolean found_2 = false;
        for(char c: s.toCharArray()) {
            if(c==prev) {
                len++;
            }
            else {
                if(len%3 == 1) {
                    return false;
                }
                if(len%3 == 2) {
                    if(found_2 == true) {
                        return false;
                    }
                    found_2 = true;
                }
                prev = c;
                len = 1;
            }
        }
        if(len%3 == 1) {
            return false;
        }
        if(len%3 == 2) {
            if(found_2 == true) {
                return false;
            }
            found_2 = true;
        }
        return found_2;
    }
}