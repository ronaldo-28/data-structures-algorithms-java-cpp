class Solution {
    public String answerString(String word, int numFriends) {
        if(numFriends == 1) return word;
        int index = duval(word.toCharArray());
        return word.substring(index, Math.min(index + (word.length() - numFriends + 1), word.length()));
    }
    private static int duval(char[] arr) {
        int i = 0, j = 1, k = 0, n = arr.length;
        while(j + k < n) {
            if(arr[i + k] == arr[j + k]) k++;
            else if(arr[i + k] > arr[j + k]) {
                j += k + 1;
                k = 0;
            }else {
                i = Math.max(i + k + 1, j);
                j = i + 1;
                k = 0;
            }
        }
        return i;
    }
}