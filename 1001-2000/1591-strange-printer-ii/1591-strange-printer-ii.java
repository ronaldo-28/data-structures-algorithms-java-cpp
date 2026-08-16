class Solution {
    // Main method to check if the grid can be printed
    public boolean isPrintable(int[][] targetGrid) {
        int m = targetGrid.length; // Number of rows in the grid
        int n = targetGrid[0].length; // Number of columns in the grid
        int N = 61; // Maximum number of colors (given constraint)
        int MIN = Integer.MIN_VALUE; // Minimum value for initialization
        int MAX = Integer.MAX_VALUE; // Maximum value for initialization
        
        int[][] rects = new int[N][]; // Array to store the bounding rectangles for each color
        
        // Identify the bounding rectangle for each color
        for (int i = 0; i < m; i++) { // Iterate over each row
            for (int j = 0; j < n; j++) { // Iterate over each column
                int col = targetGrid[i][j]; // Get the color of the current cell
                if (rects[col] == null) { // If the rectangle for this color is not initialized
                    rects[col] = new int[] { MAX, MAX, MIN, MIN }; // Initialize with extreme values
                }
                rects[col][0] = Math.min(rects[col][0], i); // Update minX
                rects[col][1] = Math.min(rects[col][1], j); // Update minY
                rects[col][2] = Math.max(rects[col][2], i); // Update maxX
                rects[col][3] = Math.max(rects[col][3], j); // Update maxY
            }
        }
        
        int[] visited = new int[N]; // Array to keep track of visited colors
        
        // Check if each color's rectangle can be printed
        for (int col = 0; col < N; col++) {
            if (rects[col] != null && !helper(targetGrid, rects, col, visited)) {
                return false; // Return false if the rectangle cannot be printed
            }
        }
        return true; // Return true if all rectangles can be printed
    }
    
    // Helper method for depth-first search (DFS) to check if a rectangle can be printed
    boolean helper(int[][] target, int[][] rects, int col, int[] visited) {
        if (visited[col] != 0) { // If the color has already been visited
            return visited[col] == 2; // Return true if it is fully processed
        }
        visited[col]++; // Mark the color as being processed
        
        int[] rect = rects[col]; // Get the rectangle coordinates for the color
        // Iterate over each cell in the rectangle
        for (int i = rect[0]; i <= rect[2]; i++) { // Iterate over rows in the rectangle
            for (int j = rect[1]; j <= rect[3]; j++) { // Iterate over columns in the rectangle
                if (target[i][j] != col && !helper(target, rects, target[i][j], visited)) {
                    return false; // Return false if a cell within the rectangle is not the target color
                }
            }
        }
        visited[col]++; // Mark the color as fully processed
        return true; // Return true if the rectangle can be printed
    }
}