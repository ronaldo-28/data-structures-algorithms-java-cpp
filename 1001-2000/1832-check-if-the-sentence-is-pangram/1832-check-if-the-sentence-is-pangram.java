class Solution {
    public boolean checkIfPangram(String sentence) {
        for(char i='a';i<='z';i++){
            int ind=sentence.indexOf(i);
            if(ind<0){
                return false;
            }
        }
        return true;
    }
}