class Solution {
    public boolean isRobotBounded(String instructions) {
        // Step 1: Initialize the robot's starting position (x, y) and its facing direction.
        // The robot starts at (0, 0) and faces north. We represent north as (0, 1).
        int x = 0, y = 0;
        int dx = 0, dy = 1;
        
        // Step 2: Process each instruction in the string.
        // Explanation: 'G' moves the robot one unit in its current direction.
        // 'L' turns the robot 90 degrees left (counterclockwise).
        // 'R' turns the robot 90 degrees right (clockwise).
        for (char c : instructions.toCharArray()) {
            if (c == 'G') {
                // Move forward in the current direction.
                x += dx;
                y += dy;
            } else if (c == 'L') {
                // Rotate left: (dx, dy) becomes (-dy, dx)
                int temp = dx;
                dx = -dy;
                dy = temp;
            } else if (c == 'R') {
                // Rotate right: (dx, dy) becomes (dy, -dx)
                int temp = dx;
                dx = dy;
                dy = -temp;
            }
        }
        
        // Step 3: Determine if the robot is bounded in a circle.
        // Explanation: 
        // If the robot returns to the origin, it is bounded.
        // Otherwise, if after one sequence the robot doesn't face north (i.e., direction (dx, dy) is not (0, 1)),
        // then repeating the sequence will eventually loop the robot back to the origin.
        // If it still faces north and is not at the origin, it will keep moving further away.
        return (x == 0 && y == 0) || !(dx == 0 && dy == 1);
    }
}
