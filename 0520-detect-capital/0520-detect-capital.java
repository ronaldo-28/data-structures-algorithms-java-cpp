class Solution {
    public boolean detectCapitalUse(String word) {
        int count = 0;

        for(int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);

            if(ch >= 'A' && ch <= 'Z'){
                count++;
            }
        }

        if(count == word.length()){
            return true;
        }

        if(count == 0){
            return true;
        }

        if(count == 1 && (word.charAt(0) >= 'A' && word.charAt(0) <= 'Z')){
            return true;
        }
        return false;
    }
}