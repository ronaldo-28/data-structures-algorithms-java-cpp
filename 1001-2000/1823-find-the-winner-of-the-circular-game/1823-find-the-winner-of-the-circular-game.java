class Solution {
    // This method determines the winner in a game where n people
    // are sitting in a circle and elimination occurs at every kth person
    public int findTheWinner(int n, int k) {
        // Base case: if there is only one person, they are the winner
        if (n == 1) {
            return 1;
        }
        // Recursively find the winner for n - 1 people
        // and adjust the position with the offset k
        int winner = (findTheWinner(n - 1, k) + k) % n;
      
        // Java uses 0-based indexing but the problem assumes 1-based indexing.
        // Therefore, if the result is 0, we convert it to n (the last person),
        // as the modulo operation may result in zero.
        // Otherwise, we return the calculated position.
        return winner == 0 ? n : winner;
    }
}