/**
 * // This is the Master's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface Master {
 *     public int guess(String word) {}
 * }
 */
class Solution {
    private int getMatchingScore(String s1, String s2){
        int count=0;
        for(int i=0;i<s1.length();i++){
            if(s1.charAt(i)==s2.charAt(i)){
                count++;
            }
        }
        return count;
    }

    public void findSecretWord(String[] words, Master master) {
        int end=words.length-1;
        int curr;
        while(true){
        String word= words[0];
        int match=master.guess(word);
        if(match==6){
            break;
        }
        curr=0;
        while(curr<=end){
            if(getMatchingScore(words[curr], word)==match){
                curr++;
            }
            else{
                words[curr]=words[end--];
            }
        }
        }
        return;
    }
}