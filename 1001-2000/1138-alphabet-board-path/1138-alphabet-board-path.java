class Solution {
    public String alphabetBoardPath(String target) {
        // StringBuilder to store the path as we calculate it
        StringBuilder path = new StringBuilder();
        // Initial position on the board is at 'a' (0, 0)
        int currentX = 0, currentY = 0;

        // Iterate over each character in the target string
        for (char c : target.toCharArray()) {
            // Calculate the target position of the character on the board
            int targetX = (c - 'a') / 5; // Row of the target character
            int targetY = (c - 'a') % 5; // Column of the target character

            // Special case: Handle 'z' to avoid invalid positions
            if (c == 'z') {
                // Move horizontally left to the target column first
                while (currentY > targetY) {
                    path.append("L");
                    currentY--;
                }
                // Move vertically down to the target row
                while (currentX < targetX) {
                    path.append("D");
                    currentX++;
                }
            } else {
                // General case: Move vertically first
                while (currentX > targetX) {
                    path.append("U");
                    currentX--;
                }
                while (currentX < targetX) {
                    path.append("D");
                    currentX++;
                }
                // Then move horizontally
                while (currentY > targetY) {
                    path.append("L");
                    currentY--;
                }
                while (currentY < targetY) {
                    path.append("R");
                    currentY++;
                }
            }
            // Add '!' after reaching the target character
            path.append("!");
        }

        // Return the final path
        return path.toString();
    }
}