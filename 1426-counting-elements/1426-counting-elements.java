class Solution {
    public int countElements(int[] arr) {
        int max = 0;
        for(int x : arr) max = Math.max(max, x);
        int[] freq = new int[max + 1];
        for(int x : arr) freq[x]++;
        int count = 0;
        for(int i = 0; i < max; i++) if(freq[i + 1] != 0) count += freq[i];
        return count;
    }
}