class Solution {
    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        int result = Integer.MAX_VALUE;

        int i = move(wordsDict, word1, 0), j = move(wordsDict, word2, 0);
        while (i < wordsDict.length && j < wordsDict.length){
            if (i < j){
                result = Math.min(result, j - i);
                i = move(wordsDict, word1, i + 1);
            } else {
                result = Math.min(result, i - j);
                j = move(wordsDict, word2, j + 1);
            }
        }

        return result;
    }

    private int move(String[] wordsDict, String word, int index){
        while(index < wordsDict.length && !wordsDict[index].equals(word)){
            index++;
        }

        return index;
    }
}