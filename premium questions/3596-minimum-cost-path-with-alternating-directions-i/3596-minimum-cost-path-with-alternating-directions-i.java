class Solution {
    // time = O(1), space = O(1)
    public int minCost(int m, int n) {
        if (m == 1 && n == 1) return 1;
        if (m == 2 && n == 1 || m == 1 && n == 2) return 3;
        return -1; 
    }
}