class Solution {
    public int maximumRemovals(String s, String p, int[] removable) {
        var seq = s.toCharArray();
        var sub = p.toCharArray();

        int result = 0;
        int low = 0;
        int high = removable.length;
        int prevMid = 0;
        while(low<=high) {
            int mid = (low + high) >>> 1;
            adjustLetterCase(removable, prevMid, mid, seq);
            if(isSubsequence(seq, sub)) {
                result = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
            prevMid = mid;
        }

        return result;
    }


    private static void adjustLetterCase(int[] map, int prevLen, int len, char[] target) {
        int from;
        int to;
        int delta;
        if(prevLen<len) {
            from =  prevLen;
            to = len;
            delta = 'A' - 'a';
        } else {
            from = len;
            to = prevLen;
            delta = 'a' - 'A';
        }

        for(int i=from; i<to; ++i) {
            target[map[i]] = (char)(target[map[i]] + delta);
        }
    }


    private static boolean isSubsequence(char[] seq, char[] sub) {
        int m = seq.length;
        int n = sub.length;

        int i = 0;
        int j = 0;
        while(i<m && j<n) {
            if(seq[i]==sub[j]) {
                ++j;
            }
            ++i;
        }

        return j==n;
    }
}