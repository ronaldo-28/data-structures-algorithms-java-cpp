class Solution {
    public int mctFromLeafValues(int[] arr) {
  
        int n = arr.length;
        int[] stack = new int[n+1];
        int top = -1;
        stack[++top] = Integer.MAX_VALUE;
        
        int smallestSum = 0;
        for (int i = 0; i < n; i++) {
            int x = arr[i];
            while (x >= stack[top]) 
                 smallestSum  += stack[top--] * Math.min(stack[top], x);
            stack[++top] = x ;
        }

        while (top + 1 > 2) 
            smallestSum  += stack[top--] * stack[top];
        
        return smallestSum;
    }
}