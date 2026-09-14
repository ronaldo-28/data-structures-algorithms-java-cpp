class Solution {
    public int minOperations(String s, int k) {
        int n = s.length();
        
        int z = 0;

        for(char ch : s.toCharArray()) if(ch == '0') ++z;

        if(z == 0) return 0;
        if(k == n) return z == n ? 1 : -1;

        int ans = Integer.MAX_VALUE;

        // if m is even
        if(z % 2 == 0){
            int m = Math.max((z + k - 1) / k, (z + n - k - 1) / (n - k));
            ans = m % 2 == 0 ? m : m + 1;
        }

        // m is odd
        if(z % 2 == k % 2){
            int m = Math.max((z + k - 1) / k, (n - z + n - k - 1) / (n - k));
            m = (m % 2) != 0 ? m : m + 1;
            ans = Math.min(ans, m);
        } 


        if(ans == Integer.MAX_VALUE) return -1;

        return ans;
        
    }
}