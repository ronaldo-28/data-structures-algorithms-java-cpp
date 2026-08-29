class Solution {
    public int maxDepthBST(int[] order) {
        int n = order.length + 1;
        int[] height = new int[n];
        int[] left = new int[n + 1];
        int[] right = new int[n + 1];
        for (int i = n - 1; i > 0; i--) 
            left[i - 1] = right[i + 1] = i;
        
        for (int i = order.length - 1; i >= 0; i--) {
            int lft = left[order[i] - 1];
            int rit = right[order[i] + 1];
            left[rit] = lft;
            right[lft] = rit;
            height[lft] = height[rit] = Math.max(height[lft], height[rit]) + 1;
        }
        
        return height[1];
    }
}