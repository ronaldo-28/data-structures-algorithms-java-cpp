class Solution {
    public boolean hasSpecialSubstring(String s, int k) {
        int n = s.length(), i = 0, j = 0;
        while(i + k <= n){
            while(j < n && s.charAt(i) == s.charAt(j)) ++j;
            if(j-i == k)  return true;
            i = j;
        }
        return false;
    }
}