class Solution {
    public String subStrHash(String s, int power, int modulo, int k, int hashValue) {
        int n = s.length();
        long currentHash = 0;
        long pk = 1;
        int resIndex = 0;

        for(int i = 0; i < k; i++){
            pk = (pk * power) % modulo;
        }

        for(int i = n - 1; i >= n - k; i--){
            currentHash = (currentHash * power + (s.charAt(i) - 'a' + 1)) % modulo;
        }

        if(currentHash == hashValue) resIndex = n - k;

        for(int i = n - k - 1; i >= 0; i--){
            int nextCharVal = s.charAt(i) - 'a' + 1;
            int prevCharVal = s.charAt(i + k) - 'a' + 1;

            currentHash = (currentHash * power + nextCharVal - (prevCharVal * pk) % modulo + modulo) % modulo;

            if(currentHash == hashValue) resIndex = i;
        }

        return s.substring(resIndex, resIndex + k);
    }
}