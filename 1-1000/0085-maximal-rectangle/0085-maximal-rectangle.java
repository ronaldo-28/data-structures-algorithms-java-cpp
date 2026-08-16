class Solution {
    private void getLeftBound(char[] matrix, int[] leftBound, int[] height, int left) {
        for (int h = height.length, i = 0; i < h; i++) {
            if (matrix[i] == '1') {
                height[i] += 1;
                leftBound[i] = Math.max(leftBound[i], left);
            } else {
                height[i] = leftBound[i] = 0;
                left = i + 1;
            }
        }
    }

    private void getRightBound(char[] matrix, int[] rightBound, int right) {
        for (int i = rightBound.length - 1; i >= 0; i--) {
            if (matrix[i] == '1') {
                rightBound[i] = Math.min(rightBound[i], right); 
            } else {
                rightBound[i] = right;
                right = i;
            }
        }
    }

    private int getMaximumArea(int area, int[] leftBound, int[] rightBound, int[] height) {
        for (int i = 0, h = height.length; i < h; i++) {
            int width = rightBound[i] - leftBound[i];
            area = Math.max(area, width * height[i]);
        }
        return area;
    }

    public int maximalRectangle(char[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        if (n == 0 || m == 0) {
            return 0;
        }

        int[] leftBound = new int[m];
        int[] rightBound = new int[m];
        int[] height = new int[m];
        Arrays.fill(rightBound, m);

        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int left = 0, right = m;
            getLeftBound(matrix[i], leftBound, height, left);
            getRightBound(matrix[i], rightBound, right);

            maxArea = getMaximumArea(maxArea, leftBound, rightBound, height);
        }
        return maxArea;
    }
}

/*
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;

        int m = matrix.length;
        int n = matrix[0].length;

        int[] height = new int[n]; // height of '1's
        int[] left = new int[n];   // left boundary
        int[] right = new int[n];  // right boundary
        Arrays.fill(right, n);     // initialize right boundaries to n

        int maxArea = 0;

        for (int i = 0; i < m; i++) {
            int currentLeft = 0, currentRight = n;

            // 1. Update height array
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1')
                    height[j]++;
                else
                    height[j] = 0;
            }

            // 2. Update left boundary
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1')
                    left[j] = Math.max(left[j], currentLeft);
                else {
                    left[j] = 0;
                    currentLeft = j + 1;
                }
            }

            // 3. Update right boundary
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == '1')
                    right[j] = Math.min(right[j], currentRight);
                else {
                    right[j] = n;
                    currentRight = j;
                }
            }

            // 4. Compute area of rectangle (height * width)
            for (int j = 0; j < n; j++) {
                int area = (right[j] - left[j]) * height[j];
                maxArea = Math.max(maxArea, area);
            }
        }

        return maxArea;
    }
}
*/