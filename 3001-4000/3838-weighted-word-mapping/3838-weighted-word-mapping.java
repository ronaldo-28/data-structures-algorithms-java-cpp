class Solution {
    public String mapWordWeights(String[] words, int[] weights) {
        char[] tempChar = new char[words.length];
        char[] reverseAlphabets = {'z', 'y', 'x', 'w','v','u', 't', 's', 'r', 'q', 'p', 'o', 'n', 'm', 'l', 'k', 'j', 'i', 'h', 'g', 'f', 'e', 'd', 'c', 'b', 'a' };
        for(int i=0;i<words.length;i++){
            String word = words[i];
            int sum = 0;
            for(int j=0;j<word.length();j++){
                char key = word.charAt(j);
                sum += weights[key-97];
            }
            sum = sum % 26;
            tempChar[i] = reverseAlphabets[sum];
        }

        return new String(tempChar);
    }
}