class Solution {
    public int[] rowAndMaximumOnes(int[][] arr) {
        int max = Integer.MIN_VALUE, indx = -1;

        for(int i=0; i<arr.length; i++){
            int ones = 0;
            for(int j=0; j<arr[0].length; j++){
                ones += arr[i][j];
            }
            if(max < ones){
                max = ones;
                indx = i;
            }
        }
        return new int[]{indx, max};
    }
}