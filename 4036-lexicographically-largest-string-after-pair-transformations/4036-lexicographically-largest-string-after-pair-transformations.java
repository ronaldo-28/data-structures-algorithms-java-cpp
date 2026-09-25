class Solution {

    public String[] largestString(int[] nums) {
        int n = nums.length;
        int idx = 0;

        String[] res = new String[n];

        for (int x : nums) {
            res[idx++] = fn(x);
        }

        return res;
    }

    private String fn(int n) {
        int[] freq = new int[26];
        int i = 'a';

        for (i = 'a'; i < 'z' && n >= 2; i++, n = n / 2) {
            if (n % 2 == 1) {
                freq[i - 'a']++;
            }
        }

        freq[i - 'a'] = n;

        StringBuilder res = new StringBuilder();

        for (i = 25; i >= 0; i--) {
            while (freq[i]-- != 0) {
                res.append(String.valueOf((char)(i + 'a')));
            }
        }

        return res.toString();
    }
}