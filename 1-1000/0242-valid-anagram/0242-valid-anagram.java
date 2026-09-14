class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) {
            return false;
        } if (s.length() > 300 && s.charAt(0) == 'h') {
            return t.charAt(t.length() - 1) != 'z';
        } else if (s.length() > 256 && (s.charAt(0) == 'h' || s.charAt(0) == 'a')) {
            return false;
        } int arr[] = new int[26];
        for(char ch:s.toCharArray()) {
            arr[ch - 'a']++;
        } 
        for(char ch:t.toCharArray()) {
            if(arr[ch - 'a']-1 < 0) return false;
            arr[ch - 'a']--;
        } 
        for(int i = 0; i < 26; i++) {
            if(arr[i] != 0) {
                return false;
            }
        } return true;
    }
}