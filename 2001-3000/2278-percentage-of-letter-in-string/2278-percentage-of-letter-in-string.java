class Solution {
    public int percentageLetter(String s, char letter) {
        int c=0;
        for(char x:s.toCharArray()){
            if(x==letter){
                c++;
            }
        }
        return (int)((100*c/s.length()));
    }
}