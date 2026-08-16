class Solution {
    public int minStoneSum(int[] piles, int k) {
        int[] freq = new int[10001];
        int total = 0;
        for(int i : piles) {
            total += i;
            freq[i]++;
        }
        for(int i = 10000; i > 0 && k > 0; i--) {
            if(freq[i] == 0)    continue;
            int half = i >> 1;
            if(freq[i] >= k) {
                total -= half * k;
                k = 0;
            } else {
                total -= half * freq[i];
                freq[i - half] += freq[i];
                k -= freq[i];
            }
        }
        return total;
    }
}