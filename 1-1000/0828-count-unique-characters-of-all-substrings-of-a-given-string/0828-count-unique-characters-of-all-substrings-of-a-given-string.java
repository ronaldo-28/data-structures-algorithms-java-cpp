class Solution {
    public int uniqueLetterString(String s) {
        char[] arr = s.toCharArray();
        int[] last = new int[26];
        Arrays.fill(last, -1);
        int[] secLast = new int[26];
        Arrays.fill(secLast, -1);
        int count = 0;
        int ans = 0;
        for(int i = 0;i < arr.length;i++) {
            int idx = arr[i] - 'A';
            int l = last[idx];
            int sl = secLast[idx];
            count += i - l - l + sl;
            ans += count;
            secLast[idx] = last[idx];
            last[idx] = i;
        }
        return ans;
    }
}