class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        int freq [] = new int[2001];

        for(int n : arr){
            freq[n + 1000]++;
        }
        boolean count[] = new boolean[1001];
        
        for(int n : arr){
            int c = freq[n + 1000];
            freq[n + 1000] = 0;
            if(c > 0 && count[c]){
                return false;
            }
            count[c] = true;
        }
        return true;
    }
}