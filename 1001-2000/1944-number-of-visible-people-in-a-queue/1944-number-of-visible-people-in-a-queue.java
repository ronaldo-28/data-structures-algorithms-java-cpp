class Solution {
    public int[] canSeePersonsCount(int[] heights) {
        int len = heights.length;
        int[] res = new int[len];
        int[] stack = new int[len];
        int top = -1;

        for(int i = len - 1; i >= 0; i--){
            int curHeight = heights[i];
            int count = 0;

            while(top > -1 && stack[top] < curHeight){
                count++;
                top--;
            }

            if(top > -1){
                count++;
            }
            res[i] = count;
            stack[++top] = curHeight;
        }
        return res;
    }
}