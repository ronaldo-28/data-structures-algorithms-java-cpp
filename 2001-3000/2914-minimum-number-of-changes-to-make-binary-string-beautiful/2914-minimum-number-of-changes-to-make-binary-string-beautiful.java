class Solution {
    public int minChanges(String s) {
        int n = s.length(), count = 0;
        
        for(int i = 1; i < n; i+=2){
            count += s.charAt(i-1) ^ s.charAt(i);
        }

        return count;
    }
}