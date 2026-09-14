class Solution {
    static {
        int[] nums = {1, 1, 2, 2, 3};
        for (int i = 0; i < 200; i++) {
            topKFrequent(nums, 2);
        }
    }
    public static int[] topKFrequent(int[] nums, int k) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }
        int[] freq = new int[max - min + 1];
        int max_freq = 0;
        for (int num : nums) {
            int dif = num - min;
            int f = ++freq[dif];
            if (f > max_freq) {
                max_freq = f;
            }
        }
        ArrayList<Integer>[] freqAr = new ArrayList[max_freq];
        for (int i = 0; i < freq.length; i++) {
            int n = freq[i] -1;
            if (n == -1) {
                continue;
            }
            if (freqAr[n] == null) {
                freqAr[n] = new ArrayList<Integer>();
            }
            freqAr[n].add(i + min);
        }
        int[] res = new int[k];
        int t = 0;
        for (int i = max_freq - 1; i >= 0; i--) {
            if (freqAr[i] == null) continue;
            for (int j = 0; j < freqAr[i].size(); j++) {
                res[t++] = freqAr[i].get(j);
                if (t == k) {
                    return res;
                }
            }
        } 
        return res;
    }
}