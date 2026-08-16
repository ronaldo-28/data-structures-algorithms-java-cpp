class Solution {
    public int minimumIndex(int[] capacity, int itemSize) {
        int ans = -1;
        int min = 101;
        for(int i = 0; i < capacity.length; i++) {
            int num = capacity[i];
            if(num >= itemSize && min > num) {
                min = num;
                ans = i;
            }
        }
        return ans;
    }
}