class Solution {
    public int minDistance(String word1, String word2) {
         int m = word1.length(), n = word2.length();
        if (m < n) {
            String tempStr = word1;
            word1 = word2;
            word2 = tempStr;
            int tempInt = n;
            n = m;
            m = tempInt;
        }
        char[] WA1 = word1.toCharArray(), WA2 = word2.toCharArray();
        int[] dpLast = new int[n+1], dpCurr = new int[n+1];
        for (char c1 : WA1) {
            for (int j = 0; j < n; j++) 
                dpCurr[j+1] = c1 == WA2[j]
                    ? dpLast[j] + 1
                    : Math.max(dpCurr[j], dpLast[j+1]);
            int[] tempArr = dpLast;
            dpLast = dpCurr;
            dpCurr = tempArr;
        }
        return m + n - 2 * dpLast[n];
    }
}