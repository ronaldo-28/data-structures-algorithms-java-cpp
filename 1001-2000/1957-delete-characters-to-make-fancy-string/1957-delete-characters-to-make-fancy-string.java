class Solution {
    public String makeFancyString(String s) {
        char[] arr = s.toCharArray();
        char prevChar = arr[0];
        int prevCharCount = 1;
        int k = 1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != prevChar) {
                prevChar = arr[i];
                prevCharCount = 0;
            }

            if (++prevCharCount > 2) continue;
            
            arr[k++] = arr[i];
        }

        return new String(arr, 0, k);
    }
}