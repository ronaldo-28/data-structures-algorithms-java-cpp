class Solution {
    public boolean isPalindrome(String s) {
        int i=0;
        int j=s.length()-1;
        while(i<j){
            char t1=s.charAt(i);
            char t2=s.charAt(j);
            if(!((t1>=65 && t1<=90) || (t1>=97 && t1<=122) || (t1>=48 && t1<=57))){
                i++;
                continue;
            }
            if(!((t2>=65 && t2<=90) || (t2>=97 && t2<=122) || (t2>=48 && t2<=57))){
                j--;
                continue;
            }
            if((t1>=65 && t1<=90)){
                t1=(char)(t1+32);
            }
            if((t2>=65 && t2<=90)){
                t2=(char)(t2+32);
            }
            if(t1!=t2){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}