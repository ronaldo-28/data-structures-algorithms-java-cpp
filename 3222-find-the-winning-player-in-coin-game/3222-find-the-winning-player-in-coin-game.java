class Solution {
    public String winningPlayer(int x, int y) {
        // Calculate the maximum moves possible based on the coin counts.
        int moves = Math.min(x, y / 4);
        
        // If the number of moves is odd, Alice (who moves first) wins.
        // If even (or zero), Bob wins.
        return moves % 2 == 1 ? "Alice" : "Bob";
    }
}
