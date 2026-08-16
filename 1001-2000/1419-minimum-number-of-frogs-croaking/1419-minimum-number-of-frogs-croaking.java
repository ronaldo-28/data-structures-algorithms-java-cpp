class Solution {
    public int minNumberOfFrogs(String croakOfFrogs) {
        int c = 0, r = 0, o = 0, a = 0, k = 0;
        int activeFrogs = 0;
        int maxFrogs = 0;

        for (char ch : croakOfFrogs.toCharArray()) {
            if (ch == 'c') {
                c++;
                activeFrogs++; 
                maxFrogs = Math.max(maxFrogs, activeFrogs);
            } else if (ch == 'r') {
                r++;
                if (r > c) return -1; 
            } else if (ch == 'o') {
                o++;
                if (o > r) return -1; 
            } else if (ch == 'a') {
                a++;
                if (a > o) return -1; 
            } else if (ch == 'k') {
                k++;
                if (k > a) return -1; 
                activeFrogs--; 
            } else {
                return -1;
            }
        }
        if (activeFrogs != 0 || (c != r || r != o || o != a || a != k)) {
            return -1;
        }
        return maxFrogs;
    }
}