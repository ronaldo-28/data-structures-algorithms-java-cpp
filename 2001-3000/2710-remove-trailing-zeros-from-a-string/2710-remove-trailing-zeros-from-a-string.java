class Solution {
    public String removeTrailingZeros(String num) {
        int n = num.length();
        int i = n-1;
        while(num.charAt(i) == '0'){
            i--;
        }
        return num.substring(0,i+1);

    }
}