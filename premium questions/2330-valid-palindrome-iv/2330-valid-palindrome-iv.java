class Solution {
    public boolean makePalindrome(String s) {
        int count = 2;
        char[] charArr = s.toCharArray();
        int l=0;
        int r=charArr.length-1;
        

        while(count > 0 && l<r){
            if(charArr[l]!=charArr[r]){
                //change one of them - change the right pointer one
                charArr[r] = charArr[l];
                count--;
            }
            l++;
            r--;
        }

       // we might have changed 2 elements, those will be palindrome.
       //check only the left over elements.

       while(l<=r){
        if(charArr[l]!=charArr[r]){

            return false;
        }
        l++;
        r--;
       }
        return true;
    }

    
}