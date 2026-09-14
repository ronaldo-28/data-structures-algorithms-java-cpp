class Solution {
    public long numberOfSubstrings(String s, int k) {
        long numberOfSubarray = 0;
        char[] arr = s.toCharArray();

        int[] fre = new int[26];
        int startIndex = 0;
        int endIndex = 0;

        while(endIndex < arr.length){
            char current = arr[endIndex];
            fre[current-'a']++;

            while(fre[current-'a'] >= k){
                numberOfSubarray += (long)(arr.length-endIndex);

                char currentToRemove = arr[startIndex];
                startIndex++;
                fre[currentToRemove-'a']--;
            }
            endIndex++;
        }

        return numberOfSubarray;
    }
}