class Solution {
    public boolean isCircularSentence(String sentence) {
        // if the 1st character and the last character are not equal
        // the sentence is not circular
        if(sentence.charAt(0) != sentence.charAt(sentence.length()-1))
            return false;

        int spaceIndex = sentence.indexOf(' ');

        // compare the character just before a space and
        // the character just after that space
        // if they are not equal, the sentence is not circular
        while(spaceIndex != -1){
            if(sentence.charAt(spaceIndex-1) != sentence.charAt(spaceIndex+1)){
                return false;
            }

            spaceIndex = sentence.indexOf(' ', spaceIndex+1);
        }
        return true;
    }
}