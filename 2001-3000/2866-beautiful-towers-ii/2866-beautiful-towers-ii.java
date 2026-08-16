class Solution {
    public long maximumSumOfHeights(List<Integer> maxHeights) {
        int n = maxHeights.size();

        int[] stack = new int[n];
        int top = -1;

        long[] leftToRight = new long[n];
        for (int i = 0; i < n; i++) {
            int height = maxHeights.get(i);

            while (top >= 0 && maxHeights.get(stack[top]) >= height) {
                top--;
            }

            leftToRight[i] = top >= 0
                    ? leftToRight[stack[top]] + ((long) i - stack[top]) * height
                    : (i + 1L) * height;

            stack[++top] = i;
        }

        long max = 0;

        top = -1;
        long[] rightToLeft = new long[n];
        for (int i = n - 1; i >= 0; i--) {
            int height = maxHeights.get(i);

            while (top >= 0 && maxHeights.get(stack[top]) >= height) {
                top--;
            }

            rightToLeft[i] = top >= 0
                    ? rightToLeft[stack[top]] + ((long) stack[top] - i) * height
                    : ((long) n - i) * height;
            max = Math.max(max, leftToRight[i] + rightToLeft[i] - height);

            stack[++top] = i;
        }

        return max;
    }
}