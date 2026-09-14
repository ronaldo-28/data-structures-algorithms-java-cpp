class Solution {
    public String reverseOnlyLetters(String s) {
        char[] arr = s.toCharArray();
        int left = 0, right = s.length() - 1;
        while(left<right){
            if(!isEnglishLetter(arr[left])) left++;
            else if(!isEnglishLetter(arr[right])) right--;
            else {
                char ch = arr[left];
                arr[left] = arr[right];
                arr[right] = ch;

                left++;
                right--;
            }
        }

        return String.valueOf(arr);
    }

    private boolean isEnglishLetter(char ch) {
        return ch>='a' && ch<='z' || ch>='A' && ch<='Z';
    }
}