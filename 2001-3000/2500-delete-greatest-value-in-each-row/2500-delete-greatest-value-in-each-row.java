class Solution {
    public int deleteGreatestValue(int[][] grid) {

        int sum=0;
        for(int n=1;n<=grid[0].length;n++)
            sum += maxOfLargestOfRows(grid);
        return sum;
    }
    public int maxAndDelete(int[] arr) {
        int li=0;
        for(int i=1;i<arr.length;i++) {
            if(arr[i] > arr[li])
                li = i;
        }
        int max = arr[li];
        arr[li] = 0;
        return max;
    }
    public int maxOfLargestOfRows(int[][] mat) {
        int max =  0;
        for(int i=0;i<mat.length;i++)
            max = Math.max(max, maxAndDelete(mat[i]));
        return max;
    }
}