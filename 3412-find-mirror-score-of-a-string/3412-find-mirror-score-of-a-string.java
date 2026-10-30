class Solution {
    /*
    mark index of value
    at index mark next to jump to if it exists
    */
    public long calculateScore(String s) {
        int n = s.length();
        long res = 0;
        int[] index = new int[26];
        int[] jump = new int[n];
        Arrays.fill(index, -1);

        for (int i = 0; i < n; i++) {
            int pos = s.charAt(i) - 'a';
            int mirror = 25 - pos;
            
            if (index[mirror] != -1) {
                res += i - index[mirror];
                index[mirror] = jump[index[mirror]];
            } else {
                jump[i] = index[pos];
                index[pos] = i;
            }
        }

        return res;
    }
}