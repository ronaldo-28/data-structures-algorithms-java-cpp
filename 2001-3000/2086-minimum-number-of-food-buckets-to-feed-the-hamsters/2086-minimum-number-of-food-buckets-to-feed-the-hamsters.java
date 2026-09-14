class Solution {
    public int minimumBuckets(String hamsters) {
        if (hamsters == null || hamsters.length() == 0) {
            return -1;
        }
        int count = 0, i = 0;
        char[] spots = hamsters.toCharArray();
        while (i < spots.length) {
            if (spots[i] == 'H') {
                if (i < spots.length - 1 && spots[i + 1] == '.') {
                    count ++;
                    i += 3;
                } else if (i > 0 && spots[i - 1] == '.') {
                    count ++;
                    i ++;
                } else {
                    return -1;
                }
            } else {
                i ++;
            }
        }
        return count;
    }
}