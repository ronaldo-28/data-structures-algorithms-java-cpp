class Solution {
    public long maxCaloriesBurnt(int[] heights) {
        Arrays.sort(heights);
        long answer = 0;
        int prev = 0;
        for (int i = 0, j = heights.length - 1;  i <= j; i++, j--) {
            answer += (long) (heights[j] - prev) * (heights[j] - prev);
            answer += (long) (heights[j] - heights[i]) * (heights[j] - heights[i]);
            prev = heights[i];
        }
        return answer;
    }
}