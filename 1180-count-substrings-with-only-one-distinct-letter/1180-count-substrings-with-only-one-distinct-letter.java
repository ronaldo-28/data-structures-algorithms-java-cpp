class Solution {
    
    public int countLetters(String s) {
        int cnt = 0;

        int idx = 0; 
        int len = s.length();

        while(idx < len) {
            char curr = s.charAt(idx);

            int temp = idx;
            
            while(temp < len && s.charAt(temp) == curr) {
                temp++;
            }
            
            int total = temp - idx;
            cnt += (total * (total + 1))/2;

            idx = temp;
        }

        return cnt;
    }
}