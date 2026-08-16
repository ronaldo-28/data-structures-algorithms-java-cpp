class Solution {
    public int countMonobit(int n) {
        int c = 1;
        for (int i = 1; i <= n; i = (i << 1) | 1) c++; 
        return c;
    }
}