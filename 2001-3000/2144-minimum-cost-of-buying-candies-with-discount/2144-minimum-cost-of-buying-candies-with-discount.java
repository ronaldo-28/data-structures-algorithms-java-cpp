class Solution {
    public int minimumCost(int[] cost) {
        int[] count = new int[101];
        for (int c : cost) count[c]++;
        int res = 0;
        int ind = 0;
        for (int i = 100; i >= 0; i--) {
            for (int j = 0; j < count[i]; j++) {
                ind++;
                if (ind % 3 != 0) res += i;
            }
        }
        return res;
    }
}