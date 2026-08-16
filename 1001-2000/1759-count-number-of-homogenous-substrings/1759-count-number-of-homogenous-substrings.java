class Solution {
    public int countHomogenous(String s) {
        char[] arr = s.toCharArray();
        int mod = 1000000007;
        long sum = 0;
        int n = 1;
        char last = arr[0];
        for (int i = 1; i < arr.length; i++) {
            char c = arr[i];
            sum += n;
            if (c != last) {
                n = 0;
                last = c;
            }
            n++;
        }
        sum += n;
        return (int) (sum % mod);
    }
}