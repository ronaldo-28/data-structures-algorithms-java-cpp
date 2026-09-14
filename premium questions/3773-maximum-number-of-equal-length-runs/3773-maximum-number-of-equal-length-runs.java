class Solution {
    public int maxSameLengthRuns(String s) {
        int i = 0, j = 1, max = 0, n = s.length();
        int[] freq = new int[n + 1];
        while(i < n) {
            char c = s.charAt(i);
            while(j < n && c == s.charAt(j)) j++;
            if(freq[j - i]++ == max) max++;
            i = j++;
        }
        return max;
    }
}